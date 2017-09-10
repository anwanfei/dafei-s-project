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

public class AddInsuranceActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
    @BindView(R.id.rb_no_ofton_use)
    RadioButton rbNoOftonUse;
    @BindView(R.id.rb_is_ofton_use)
    RadioButton rbIsOftonUse;
    @BindView(R.id.rg_is_ofton_use)
    RadioGroup rgIsOftonUse;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.iv_add_images)
    ImageView ivAddImages;
    @BindView(R.id.tv_add_insurance_complete)
    TextView tvAddInsuranceComplete;
    private String isEffectiveForever = "2";
    private String isOftonUse = "1";
    private String reWarningDays = "30";
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
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

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.add_insurance));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_insurance;
    }

    @OnClick({R.id.iv_back, R.id.rl_certificate_type, R.id.tv_issue_date, R.id.rl_issuing_authority, R.id.tv_effective_date, R.id.tv_add_certificate_photo, R.id.iv_add_images, R.id.tv_add_insurance_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_certificate_type:
                break;
            case R.id.tv_issue_date:
                DateUtil.showChooseTimeDialog(this, tvIssueDate);
                break;
            case R.id.rl_issuing_authority:
                break;
            case R.id.tv_effective_date:
                DateUtil.showChooseTimeDialog(this, tvEffectiveDate);
                break;
            case R.id.tv_add_certificate_photo:
                break;
            case R.id.iv_add_images:
                break;
            case R.id.tv_add_insurance_complete:
                addInsuranceComplete();
                break;
        }
    }

    private void addInsuranceComplete() {

        String userId = CacheUtils.getString(this, Constants.ID);

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


        if (insuranceName.equals("")) {
            Toast.makeText(AddInsuranceActivity.this, getResources().getString(R.string.input_insurance_name), Toast.LENGTH_SHORT).show();
            return;
        }

        if (shipName.equals("")) {
            Toast.makeText(AddInsuranceActivity.this, getResources().getString(R.string.input_ship_name), Toast.LENGTH_SHORT).show();
            return;
        }

        if (shipHuhaoBianhao.equals("")) {
            Toast.makeText(AddInsuranceActivity.this, getResources().getString(R.string.input_ship_bianhao), Toast.LENGTH_SHORT).show();
            return;
        }

        NetUtils.postWithHeader(this, ConstantsUrls.ADD_SHIP_INSURANCE)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_ID, id)
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
                // TODO: 2017/9/8 picture是必填项 ，以后修改
                .addParams(Constants.PICTRUE, "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(AddInsuranceActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(AddInsuranceActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(AddInsuranceActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(AddInsuranceActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(AddInsuranceActivity.this, LoginRegisterActivity.class));
                                finish();
                            }
                            if (code.equals("200")) {
                                finish();
                            }
                        }
                    }
                });
    }
}
