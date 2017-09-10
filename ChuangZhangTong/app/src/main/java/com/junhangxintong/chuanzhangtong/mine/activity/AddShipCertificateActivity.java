package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class AddShipCertificateActivity extends BaseActivity {

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
    TextView etCertificateType;
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
    private String certificateName;
    private String certificateNum;
    private String certificateType;
    private String issuingAuthory;
    private String userId;
    private String id;
    private String isOftenUse = "1";
    private String isEffective = "1";
    private String reWarningDays = "";
    private String ettectiveDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
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


        if (certificateName.equals("")) {
            Toast.makeText(AddShipCertificateActivity.this, getResources().getString(R.string.certificate_name_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if(certificateName.equals("")) {
            Toast.makeText(AddShipCertificateActivity.this, getResources().getString(R.string.certificate_num_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

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
                .addParams(Constants.PICTRUE, "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddShipCertificateActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
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


       /* Intent intent = getIntent();
        intent.putExtra(Constants.CERTIFICATE_NAME, certificateName);
        intent.putExtra(Constants.CERTIFICATENUM, certificateNum);
        intent.putExtra(Constants.ISSUINGAUTHORY, issuingAuthory);

        setResult(Constants.REQUEST_CODE0, intent);

        finish();*/
    }
}
