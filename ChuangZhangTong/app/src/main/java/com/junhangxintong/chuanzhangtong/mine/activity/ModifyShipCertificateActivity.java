package com.junhangxintong.chuanzhangtong.mine.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.Utils.EventBusMessage;
import com.junhangxintong.chuanzhangtong.mine.adapter.CertificatePhotoAdapter;
import com.junhangxintong.chuanzhangtong.mine.adapter.PhotoAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipCertificateOrInsuranceInfoBean;
import com.junhangxintong.chuanzhangtong.mine.bean.UrlBean;
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
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.apache.commons.lang.StringUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class ModifyShipCertificateActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "junhang";
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
    @BindView(R.id.rg_is_effective)
    RadioGroup rgIsEffective;
    @BindView(R.id.tv_effective_date)
    TextView tvEffectiveDate;
    @BindView(R.id.rl_effective_date)
    RelativeLayout rlEffectiveDate;
    @BindView(R.id.rb_time_30_days)
    RadioButton rbTime30Days;
    @BindView(R.id.rb_time_90_days)
    RadioButton rbTime90Days;
    @BindView(R.id.rg_warn_days)
    RadioGroup rgWarnDays;
    @BindView(R.id.rl_reWarning_days)
    RelativeLayout rlReWarningDays;
    @BindView(R.id.rb_commoned)
    RadioButton rbCommoned;
    @BindView(R.id.rb_no_commoned)
    RadioButton rbNoCommoned;
    @BindView(R.id.rg_is_ofton_use)
    RadioGroup rgIsOftonUse;
    @BindView(R.id.rvResultPhoto)
    RecyclerView rvResultPhoto;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.tv_crew_info_complete)
    TextView tvCrewInfoComplete;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private String userId;
    private ShipCertificateOrInsuranceInfoBean shipCertificateOrInsuranceInfoBean;
    private ArrayList<UrlBean> urlLists;
    private ArrayList<String> localUrls;
    private String imagePath;
    private ArrayList<String> path = new ArrayList<>();
    private CertificatePhotoAdapter certificatePhotoAdapter;
    private PhotoAdapter photoAdapter;
    private String isOftenUse = "1";
    private String isEffective = "2";
    private String reWarningDays = "30";
    private String ettectiveDate = "";
    private GalleryConfig gallrtyConfig;
    private IHandlerCallBack iHandlerCallBack;
    private PopupWindow popupWindow;
    private String certificateName;
    private String certificateNum;
    private String issuingAuthory;
    private String id;
    private String shipId;
    private String certicateType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();

        initGallery();

        chooseAndShowPhotos();
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

    private void chooseAndShowPhotos() {
        gallrtyConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())
                .iHandlerCallBack(iHandlerCallBack)
                .provider("com.junhangxintong.chuanzhangtong.fileprovider")
                .pathList(path)
                .crop(true)
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

    // 授权管理
    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(ModifyShipCertificateActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            GalleryPick.getInstance().setGalleryConfig(gallrtyConfig).open(this);
        }
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.modify_ship_certificate_info));
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
        View rootview = LayoutInflater.from(ModifyShipCertificateActivity.this).inflate(R.layout.activity_personal_info, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCallback(EventBusMessage eventBusMessage) {
//        path.addAll(eventBusMessage.getPhotos());
        path = eventBusMessage.getPhotos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        photoAdapter.notifyDataSetChanged();
      /*  GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvResultPhoto.setLayoutManager(gridLayoutManager);*/
        if (path.size() > 0) {
            rvResultPhoto.setAdapter(certificatePhotoAdapter);
            photoAdapter = new PhotoAdapter(this, path);
            rvResultPhoto.setAdapter(this.photoAdapter);
        }
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);

        userId = CacheUtils.getString(this, Constants.ID);

        Intent intent = getIntent();
        shipCertificateOrInsuranceInfoBean = (ShipCertificateOrInsuranceInfoBean) intent.getSerializableExtra(Constants.SHIP_CERTIFICATE_DETAILS_BEAN);
        ShipCertificateOrInsuranceInfoBean.DataBean.ObjectBean shipCertificateOrInsuranceInfo = shipCertificateOrInsuranceInfoBean.getData().getObject();

        id = String.valueOf(shipCertificateOrInsuranceInfo.getId());
        shipId = String.valueOf(shipCertificateOrInsuranceInfo.getShipId());

        etInputCertificateName.setText(shipCertificateOrInsuranceInfo.getName());
        etCertificateNumber.setText(String.valueOf(shipCertificateOrInsuranceInfo.getCertifNo()));
        etCertificateType.setText(shipCertificateOrInsuranceInfo.getCertifCategory());
        tvIssuingAuthority.setText(shipCertificateOrInsuranceInfo.getIssueOrganization());

        int isValid = shipCertificateOrInsuranceInfo.getIsValid();
        if (isValid == 1) {
            rbYes.setChecked(true);
            rbNo.setChecked(false);
            rlEffectiveDate.setVisibility(View.GONE);
            rlReWarningDays.setVisibility(View.GONE);
            isEffective = "1";
        } else {
            rbNo.setChecked(true);
            rbYes.setChecked(false);
            rlEffectiveDate.setVisibility(View.VISIBLE);
            rlReWarningDays.setVisibility(View.VISIBLE);
            tvEffectiveDate.setText(shipCertificateOrInsuranceInfo.getValidDate());
            rgWarnDays.setVisibility(View.VISIBLE);
            isEffective = "2";
        }

        if (shipCertificateOrInsuranceInfo.getIsUse() == 1) {
            rbCommoned.setChecked(true);
        } else {
            rbNoCommoned.setChecked(true);
        }

        String imgUrl = shipCertificateOrInsuranceInfo.getImgUrl();
        if (StringUtils.isNotBlank(imgUrl)) {
            Type type = new TypeToken<ArrayList<UrlBean>>() {
            }.getType();
            urlLists = new Gson().fromJson(imgUrl, type);

            localUrls = new ArrayList<>();
            String basePath = Environment.getExternalStorageDirectory() + Constants.PHONE_PATH_NO_NAMA;

            for (int i = 0; i < urlLists.size(); i++) {
                String url = urlLists.get(i).getPicUrl();
                imagePath = url.substring(url.lastIndexOf("/") - 1).replace("/", "");
                localUrls.add(basePath + imagePath);
                OkHttpUtils
                        .get()
                        .url(shipCertificateOrInsuranceInfo.getDomain() + url)
                        .build()
                        .execute(new FileCallBack(basePath, imagePath) {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(File response, int id) {

                                Log.e("TAG", "response===============" + response.getAbsolutePath());
                            }
                        });
            }

            path.addAll(localUrls);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvResultPhoto.setLayoutManager(gridLayoutManager);
            if (urlLists.size() > 0) {
                certificatePhotoAdapter = new CertificatePhotoAdapter(this, urlLists, shipCertificateOrInsuranceInfo.getDomain(), localUrls);
                rvResultPhoto.setAdapter(certificatePhotoAdapter);
            }

            //scrollview数据处理
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo(0, 0);
                }
            });
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_ship_certificate;
    }

    @OnClick({R.id.iv_back, R.id.tv_effective_date, R.id.tv_add_certificate_photo, R.id.tv_crew_info_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_effective_date:
                DateUtil.showChooseTimeDialog(this, tvEffectiveDate);
                break;
            case R.id.tv_add_certificate_photo:
                showChoosePhotesPop();
                break;
            case R.id.tv_crew_info_complete:
                modifyCertificateComplete();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_choose_gender:
                popupWindow.dismiss();
                break;
            case R.id.tv_man:
                if (path.size() < 6) {
                    GalleryPick.getInstance().setGalleryConfig(gallrtyConfig).openCamera(ModifyShipCertificateActivity.this);
                } else {
                    Toast.makeText(ModifyShipCertificateActivity.this,getResources().getString(R.string.photo_num_inner_six), Toast.LENGTH_SHORT).show();
                }
                popupWindow.dismiss();
                break;
            case R.id.tv_woman:
                gallrtyConfig.getBuilder().isOpenCamera(false).pathList(path).build();
                initPermissions();
                popupWindow.dismiss();
                break;
        }
    }

    private void modifyCertificateComplete() {

        certificateName = etInputCertificateName.getText().toString();
        certificateNum = etCertificateNumber.getText().toString();
        issuingAuthory = tvIssuingAuthority.getText().toString();
        ettectiveDate = tvEffectiveDate.getText().toString();
        certicateType = etCertificateType.getText().toString();

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
            Toast.makeText(ModifyShipCertificateActivity.this, getResources().getString(R.string.certificate_name_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(certificateNum)) {
            Toast.makeText(ModifyShipCertificateActivity.this, getResources().getString(com.junhangxintong.chuanzhangtong.R.string.certificate_num_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEffective.equals("2")) {
            if (StringUtils.isEmpty(ettectiveDate)) {
                Toast.makeText(ModifyShipCertificateActivity.this, getResources().getString(R.string.input_effective_date), Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            ettectiveDate = "";
        }

        final ProgressDialog progressDialog = getProgressDialog();
        NetUtils.postWithHeader(this, ConstantsUrls.MODIFY_SHIP_CERTIFICATE)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.ID, id)
                .addParams(Constants.CERTIF_TYPE, "1")
                .addParams(Constants.CERTIFCATEGORY, certicateType)
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
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        Toast.makeText(ModifyShipCertificateActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
