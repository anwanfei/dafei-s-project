package com.junhangxintong.chuanzhangtong.mine.activity;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.Utils.EventBusMessage;
import com.junhangxintong.chuanzhangtong.mine.adapter.PhotoAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.GlideImageLoader;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.utils.PictureUtils;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class AddShipCertificateActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.et_input_certificate_name)
    EditText etInputCertificateName;
    @BindView(R.id.et_certificate_number)
    EditText etCertificateNumber;
    @BindView(R.id.et_certificate_type)
    EditText etCertificateType;
    @BindView(R.id.rl_certificate_type)
    RelativeLayout rlCertificateType;
    @BindView(R.id.tv_issuing_authority)
    EditText tvIssuingAuthority;
    @BindView(R.id.rl_issuing_authority)
    RelativeLayout rlIssuingAuthority;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.tv_effective_date)
    TextView tvEffectiveDate;
    @BindView(R.id.rb_time_30_days)
    RadioButton rbTime30Days;
    @BindView(R.id.rb_time_90_days)
    RadioButton rbTime90Days;
    @BindView(R.id.rb_commoned)
    RadioButton rbCommoned;
    @BindView(R.id.rb_no_commoned)
    RadioButton rbNoCommoned;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.tv_crew_info_complete)
    TextView tvCrewInfoComplete;
    @BindView(R.id.rg_is_effective)
    RadioGroup rgIsEffective;
    @BindView(R.id.rg_warn_days)
    RadioGroup rgWarnDays;
    @BindView(R.id.rg_is_ofton_use)
    RadioGroup rgIsOftonUse;
    @BindView(R.id.rl_effective_date)
    RelativeLayout rlEffectiveDate;
    @BindView(R.id.rl_reWarning_days)
    RelativeLayout rlReWarningDays;
    @BindView(R.id.rvResultPhoto)
    RecyclerView rvResultPhoto;
    private String certificateName;
    private String certificateNum;
    private String certificateType;
    private String issuingAuthory;
    private String userId;
    private String id;
    private String isOftenUse = "1";
    private String isEffective = "2";
    private String reWarningDays = "";
    private String ettectiveDate = "";

    private String TAG = "junhang";
    private IHandlerCallBack iHandlerCallBack;
    //存储获得图片路径的集合
    private ArrayList<String> path = new ArrayList<>();
    private GalleryConfig gallrtyConfig;
    private PhotoAdapter photoAdapter;
    private PopupWindow popupWindow;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();

        initGallery();

        init();

        EventBus.getDefault().register(this);
    }

    private void init() {
        chooseAndShowPhotos();
    }

    private void chooseAndShowPhotos() {
        gallrtyConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.junhangxintong.chuanzhangtong.fileprovider")
                .pathList(path)
                .multiSelect(true)
                .multiSelect(true, 6)
                .maxSize(6)
                .isShowCamera(true)
                .filePath("/Gallery/Pictures")
                .build();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvResultPhoto.setLayoutManager(gridLayoutManager);

        photoAdapter = new PhotoAdapter(this, path);
        rvResultPhoto.setAdapter(photoAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCallback(EventBusMessage eventBusMessage) {
        path = eventBusMessage.getPhotos();

        chooseAndShowPhotos();
    }

    private void showChoosePhotesPop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.choose_gender_picker_popupwindow_layut, null);

        popupWindow = new PopupWindow(inflate, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(inflate);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        backgroundAlpha(0.5f);
        TextView tv_man = (TextView) inflate.findViewById(R.id.tv_man);
        tv_man.setText(getResources().getString(R.string.camera));
        TextView tv_woman = (TextView) inflate.findViewById(R.id.tv_woman);
        tv_woman.setText(getResources().getString(R.string.album));
        TextView tv_cancel_choose_gender = (TextView) inflate.findViewById(R.id.tv_cancel_choose_gender);

        tv_man.setOnClickListener(this);
        tv_woman.setOnClickListener(this);
        tv_cancel_choose_gender.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(AddShipCertificateActivity.this).inflate(R.layout.activity_personal_info, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

    }

    private void initGallery() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.i(TAG, "onSuccess: 返回数据");
                path.clear();
                for (String s : photoList) {
                    Log.i(TAG, s);
                    path.add(s);
                }
                photoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: 出错");
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    // 授权管理
    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(AddShipCertificateActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            GalleryPick.getInstance().setGalleryConfig(gallrtyConfig).open(this);
        }
    }

    private void initListener() {
        rgIsEffective.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbNo.getId()) {
                    isEffective = "2";
                    rlEffectiveDate.setVisibility(View.VISIBLE);
                    rlReWarningDays.setVisibility(View.VISIBLE);
                } else if (i == rbYes.getId()) {
                    isEffective = "1";
                    rlEffectiveDate.setVisibility(View.GONE);
                    rlReWarningDays.setVisibility(View.GONE);
                }
            }
        });

        rgIsOftonUse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbCommoned.getId()) {
                    isOftenUse = "1";
                } else if (i == rbNoCommoned.getId()) {
                    isOftenUse = "2";
                }
            }
        });

        rgWarnDays.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbTime30Days.getId()) {
                    reWarningDays = "30";
                } else if (i == rbTime90Days.getId()) {
                    reWarningDays = "90";
                }
            }
        });
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.add_certificate));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
        int certificate_from_type = intent.getIntExtra(Constants.CREW_CERTIFICATE, 0);
        if (certificate_from_type == 1) {
            rlCertificateType.setVisibility(View.GONE);
        } else {
            rlCertificateType.setVisibility(View.VISIBLE);
        }

        userId = CacheUtils.getString(this, Constants.ID);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_certificate;
    }

    @OnClick({R.id.iv_back, R.id.et_certificate_type, R.id.rl_certificate_type, R.id.tv_issuing_authority, R.id.rl_issuing_authority,
            R.id.tv_add_certificate_photo, R.id.tv_crew_info_complete, R.id.tv_effective_date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_certificate_type:
                break;
            case R.id.rl_certificate_type:
                break;
            case R.id.tv_issuing_authority:
                break;
            case R.id.rl_issuing_authority:
                break;
            case R.id.tv_add_certificate_photo:
                showChoosePhotesPop();
                break;
            case R.id.tv_crew_info_complete:
                addCertificateComplete();
                break;
            case R.id.tv_effective_date:
                DateUtil.showChooseTimeDialog(this, tvEffectiveDate);
                break;
        }
    }

    private void addCertificateComplete() {

        certificateName = etInputCertificateName.getText().toString();
        certificateNum = etCertificateNumber.getText().toString();
        issuingAuthory = tvIssuingAuthority.getText().toString();
        ettectiveDate = tvEffectiveDate.getText().toString();

        Map<String, File> stringFileHashMap = new HashMap<>();
        for (int i = 0; i < path.size(); i++) {
            try {
                String bitmapToPath = PictureUtils.bitmapToPath(path.get(i));
                String key = i + ".jpg";
                stringFileHashMap.put(key, new File(bitmapToPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (StringUtils.isEmpty(certificateName)) {
            Toast.makeText(AddShipCertificateActivity.this, getResources().getString(R.string.certificate_name_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(certificateNum)) {
            Toast.makeText(AddShipCertificateActivity.this, getResources().getString(com.junhangxintong.chuanzhangtong.R.string.certificate_num_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEffective.equals("2")) {
            if (StringUtils.isEmpty(ettectiveDate)) {
                Toast.makeText(AddShipCertificateActivity.this, getResources().getString(R.string.input_effective_date), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        final ProgressDialog progressDialog = getProgressDialog();
        NetUtils.postWithHeader(this, ConstantsUrls.ADD_SHIP_CERTIFICATE)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_ID, id)
                .addParams(Constants.CERTIF_TYPE, "1")
                .addParams(Constants.NAME, certificateName)
                .addParams(Constants.CERTIFNO, certificateNum)
                .addParams(Constants.ISSUE_ORGANIZATION, issuingAuthory)
                .addParams(Constants.ADVANCE_WARN_DAYS, reWarningDays)
                .addParams(Constants.IS_USE, isOftenUse)
                .addParams(Constants.IS_VALID, isEffective)
                .addParams(Constants.VALID_DATE, ettectiveDate)
                // TODO: 2017/9/8 picture是必填项 ，以后修改
                .files(Constants.PICTRUE, stringFileHashMap)
                .build()
                .connTimeOut(3000)
                .writeTimeOut(3000)
                .readTimeOut(3000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddShipCertificateActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        progressDialog.dismiss();
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(AddShipCertificateActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(AddShipCertificateActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(AddShipCertificateActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(AddShipCertificateActivity.this, LoginRegisterActivity.class));
                                finish();
                            }
                            if (code.equals("200")) {
                                finish();
                            }
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_choose_gender:
                popupWindow.dismiss();
                break;
            case R.id.tv_man:
                GalleryPick.getInstance().setGalleryConfig(gallrtyConfig).openCamera(AddShipCertificateActivity.this);
                popupWindow.dismiss();
                break;
            case R.id.tv_woman:
                gallrtyConfig.getBuilder().isOpenCamera(false).build();
                initPermissions();
                popupWindow.dismiss();
                break;
        }
    }
}
