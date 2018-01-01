package com.junhangxintong.chuanzhangtong.mine.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
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
import com.zhy.http.okhttp.callback.StringCallback;

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

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class ModifyShipInsuranceActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "junhang";
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_insurance_name)
    TextView tvInsuranceName;
    @BindView(R.id.et_insurance_name)
    EditText etInsuranceName;
    @BindView(R.id.et_ship_name)
    EditText etShipName;
    @BindView(R.id.et_ship_bianhao)
    EditText etShipBianhao;
    @BindView(R.id.et_ship_imo)
    EditText etShipImo;
    @BindView(R.id.rl_certificate_type)
    RelativeLayout rlCertificateType;
    @BindView(R.id.et_ship_nationality_harbor)
    EditText etShipNationalityHarbor;
    @BindView(R.id.tv_name_address_of_ship)
    EditText tvNameAddressOfShip;
    @BindView(R.id.et_gurantee_type)
    EditText etGuranteeType;
    @BindView(R.id.tv_issue_date)
    TextView tvIssueDate;
    @BindView(R.id.et_issuing_authority)
    EditText etIssuingAuthority;
    @BindView(R.id.et_issue_address)
    EditText etIssueAddress;
    @BindView(R.id.rl_issuing_authority)
    RelativeLayout rlIssuingAuthority;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rg_choose_is_no)
    RadioGroup rgChooseIsNo;
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
    @BindView(R.id.rl_re_warning_days)
    RelativeLayout rlReWarningDays;
    @BindView(R.id.rb_is_ofton_use)
    RadioButton rbIsOftonUse;
    @BindView(R.id.rb_no_ofton_use)
    RadioButton rbNoOftonUse;
    @BindView(R.id.rg_is_ofton_use)
    RadioGroup rgIsOftonUse;
    @BindView(R.id.rvResultPhoto)
    RecyclerView rvResultPhoto;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.iv_add_images)
    ImageView ivAddImages;
    @BindView(R.id.tv_add_insurance_complete)
    TextView tvAddInsuranceComplete;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private ShipCertificateOrInsuranceInfoBean shipCertificateOrInsuranceInfoBean;
    private String imagePath;
    private CertificatePhotoAdapter certificatePhotoAdapter;
    private ArrayList<String> path = new ArrayList<>();
    private PhotoAdapter photoAdapter;
    private GalleryConfig gallrtyConfig;
    private IHandlerCallBack iHandlerCallBack;
    private String isEffectiveForever = "2";
    private String isOftonUse = "1";
    private String reWarningDays = "30";
    private String userId;
    private PopupWindow popupWindow;
    private ArrayList<UrlBean> urlLists;
    private String id;
    private String shipId;
    private ArrayList<String> localUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initListener();

        initGallery();

        chooseAndShowPhotos();
    }

    private void initListener() {
        rgChooseIsNo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbNo.getId()) {
                    isEffectiveForever = "2";
                    rlEffectiveDate.setVisibility(View.VISIBLE);
                    rlReWarningDays.setVisibility(View.VISIBLE);
                } else if (i == rbYes.getId()) {
                    isEffectiveForever = "1";
                    rlEffectiveDate.setVisibility(View.GONE);
                    rlReWarningDays.setVisibility(View.GONE);
                }
            }
        });

        rgIsOftonUse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbIsOftonUse.getId()) {
                    isOftonUse = "1";
                } else if (i == rbNoOftonUse.getId()) {
                    isOftonUse = "2";
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
        if (ContextCompat.checkSelfPermission(ModifyShipInsuranceActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
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
        tvTitle.setText(getResources().getString(R.string.modify_ship_insurance_info));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
        View rootview = LayoutInflater.from(ModifyShipInsuranceActivity.this).inflate(R.layout.activity_personal_info, null);
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

    private void modifyShipInsuranceComplete() {

        String insuranceName = etInsuranceName.getText().toString();
        String shipName = etShipName.getText().toString();
        String shipHuhaoBianhao = etShipBianhao.getText().toString();
        String shipImo = etShipImo.getText().toString();
        String ShipNationalityHarbor = etShipNationalityHarbor.getText().toString();
        String nameAddressOfShip = tvNameAddressOfShip.getText().toString();
        String guranteeType = etGuranteeType.getText().toString();
        String issureDate = tvIssueDate.getText().toString();
        String issueAuthority = etIssuingAuthority.getText().toString();
        String issueAddress = etIssueAddress.getText().toString();
        String effectiveDate = tvEffectiveDate.getText().toString();


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

        if (StringUtils.isEmpty(insuranceName)) {
            Toast.makeText(ModifyShipInsuranceActivity.this, getResources().getString(R.string.input_insurance_name), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(shipName)) {
            Toast.makeText(ModifyShipInsuranceActivity.this, getResources().getString(R.string.input_ship_name), Toast.LENGTH_SHORT).show();
            return;
        }


        if (StringUtils.isEmpty(shipHuhaoBianhao)) {
            Toast.makeText(ModifyShipInsuranceActivity.this, getResources().getString(R.string.input_ship_bianhao), Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEffectiveForever.equals("2")) {
            if (StringUtils.isEmpty(effectiveDate)) {
                Toast.makeText(ModifyShipInsuranceActivity.this, getResources().getString(R.string.input_effective_date), Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            effectiveDate = "";
        }

        final ProgressDialog progressDialog = getProgressDialog();

        NetUtils.postWithHeader(this, ConstantsUrls.MODIFY_SHIP_INSERANCE)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.ID, id)
                .addParams(Constants.CERTIF_TYPE, "2")
                .addParams(Constants.NAME, insuranceName)
                .addParams(Constants.SHIP_NAME, shipName)
                .addParams(Constants.SHIP_CALL, shipHuhaoBianhao)
                .addParams(Constants.ASSURE_TYPE, guranteeType)
                .addParams(Constants.IMO_NO, shipImo)
                .addParams(Constants.SHIP_NATIONA_PORT, ShipNationalityHarbor)
                .addParams(Constants.SHIP_NAME_ADDRESS, nameAddressOfShip)
                .addParams(Constants.ISSUE_DATE, issureDate)
                .addParams(Constants.ISSUE_ORGANIZATION, issueAuthority)
                .addParams(Constants.ISSUE_ADDRESS, issueAddress)
                .addParams(Constants.ADVANCE_WARN_DAYS, reWarningDays)
                .addParams(Constants.IS_USE, isOftonUse)
                .addParams(Constants.IS_VALID, isEffectiveForever)
                .addParams(Constants.VALID_DATE, effectiveDate)
                .files(Constants.PICTRUE, stringFileHashMap)
                .build()
                .writeTimeOut(3000)
                .connTimeOut(3000)
                .readTimeOut(3000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ModifyShipInsuranceActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        progressDialog.dismiss();
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ModifyShipInsuranceActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(ModifyShipInsuranceActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ModifyShipInsuranceActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ModifyShipInsuranceActivity.this, LoginRegisterActivity.class));
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
    protected void initData() {

        EventBus.getDefault().register(this);

        userId = CacheUtils.getString(this, Constants.ID);

        Intent intent = getIntent();
        shipCertificateOrInsuranceInfoBean = (ShipCertificateOrInsuranceInfoBean) intent.getSerializableExtra(Constants.SHIP_INSURANCE_DETAILS_BEAN);
        ShipCertificateOrInsuranceInfoBean.DataBean.ObjectBean shipCertificateOrInsuranceInfo = shipCertificateOrInsuranceInfoBean.getData().getObject();

        id = String.valueOf(shipCertificateOrInsuranceInfo.getId());
        shipId = String.valueOf(shipCertificateOrInsuranceInfo.getShipId());

        etInsuranceName.setText(shipCertificateOrInsuranceInfo.getName());
        etShipName.setText(shipCertificateOrInsuranceInfo.getShipName());
        etShipBianhao.setText(String.valueOf(shipCertificateOrInsuranceInfo.getShipCall()));
        etShipImo.setText(shipCertificateOrInsuranceInfo.getImoNo());
        etShipNationalityHarbor.setText(shipCertificateOrInsuranceInfo.getShipNationaPort());
        tvNameAddressOfShip.setText(shipCertificateOrInsuranceInfo.getShipNameOrAddress());
        etGuranteeType.setText(shipCertificateOrInsuranceInfo.getAssureType());
        tvIssueDate.setText(shipCertificateOrInsuranceInfo.getIssueDate());
        etIssuingAuthority.setText(shipCertificateOrInsuranceInfo.getIssueOrganization());
        etIssueAddress.setText(shipCertificateOrInsuranceInfo.getIssueAddress());
        int isValid = shipCertificateOrInsuranceInfo.getIsValid();
        if (isValid == 1) {
            rbYes.setChecked(true);
            rlEffectiveDate.setVisibility(View.GONE);
            rlReWarningDays.setVisibility(View.GONE);
            isEffectiveForever = "1";
        } else {
            rbNo.setChecked(true);
            rlEffectiveDate.setVisibility(View.VISIBLE);
            rlReWarningDays.setVisibility(View.VISIBLE);
            tvEffectiveDate.setText(shipCertificateOrInsuranceInfo.getValidDate());
            isEffectiveForever = "2";
        }

        tvEffectiveDate.setText(shipCertificateOrInsuranceInfo.getValidDate());
        if (shipCertificateOrInsuranceInfo.getIsUse() == 1) {
            rbIsOftonUse.setChecked(true);
        } else {
            rbNoOftonUse.setChecked(true);
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

           /* ShowPhotoAdapter showPhotoAdapter = new ShowPhotoAdapter(ModifyShipInsuranceActivity.this, urlLists, crewCertificateDetails.getDomain());
            gvCertificatePhoto.setAdapter(showPhotoAdapter);*/

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
        return R.layout.activity_modify_ship_insurance;
    }

    @OnClick({R.id.iv_back, R.id.tv_issue_date, R.id.tv_effective_date, R.id.iv_add_images, R.id.tv_add_insurance_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_issue_date:
                DateUtil.showChooseTimeDialog(this, tvIssueDate);
                break;
            case R.id.tv_effective_date:
                DateUtil.showChooseTimeDialog(this, tvEffectiveDate);
                break;
            case R.id.iv_add_images:
                showChoosePhotesPop();
                break;
            case R.id.tv_add_insurance_complete:
                modifyShipInsuranceComplete();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        photoAdapter.notifyDataSetChanged();
       /* GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvResultPhoto.setLayoutManager(gridLayoutManager);*/
        if (path.size() > 0) {
            //certificatePhotoAdapter = new CertificatePhotoAdapter(this, urlLists, crewCertificateDetails.getDomain(), localUrls);
            rvResultPhoto.setAdapter(certificatePhotoAdapter);
            photoAdapter = new PhotoAdapter(this, path);
            rvResultPhoto.setAdapter(this.photoAdapter);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_choose_gender:
                popupWindow.dismiss();
                break;
            case R.id.tv_man:
                if (path.size() < 6) {
                    GalleryPick.getInstance().setGalleryConfig(gallrtyConfig).openCamera(ModifyShipInsuranceActivity.this);
                } else {
                    Toast.makeText(ModifyShipInsuranceActivity.this, getResources().getString(R.string.photo_num_inner_six), Toast.LENGTH_SHORT).show();
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
}
