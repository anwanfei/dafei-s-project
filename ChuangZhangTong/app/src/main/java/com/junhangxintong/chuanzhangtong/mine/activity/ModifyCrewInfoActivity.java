package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class ModifyCrewInfoActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_input_name)
    TextView tvInputName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_nationality)
    TextView etNationality;
    @BindView(R.id.rl_nationality)
    RelativeLayout rlNationality;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.rl_choose_certificate_type)
    RelativeLayout rlChooseCertificateType;
    @BindView(R.id.et_certificate_number)
    EditText etCertificateNumber;
    @BindView(R.id.tv_belong_ship)
    TextView tvBelongShip;
    @BindView(R.id.et_duty)
    EditText etDuty;
    @BindView(R.id.rl_choose_duty)
    RelativeLayout rlChooseDuty;
    @BindView(R.id.et_job_num)
    EditText etJobNum;
    @BindView(R.id.et_call_sign)
    EditText etCallSign;
    @BindView(R.id.tv_crew_info_complete)
    TextView tvCrewInfoComplete;
    private String country = "中国";
    private String certificateTypeNum = "1";
    private String belongShip = "";
    private String userId;
    private String phone;
    private String personName;
    private String crewId;
    private String jobNum;
    private String duty;
    private String email;
    private String certificateNum;
    private String certificateType = "身份证";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.modify_crew_info));

    }

    @Override
    protected void initData() {

        userId = CacheUtils.getString(this, Constants.ID);

        Intent intent = getIntent();
        phone = intent.getStringExtra(Constants.PHONE);
        personName = intent.getStringExtra(Constants.PERSON_NAME);
        crewId = intent.getStringExtra(Constants.ID);

        tvInputName.setText(personName);
        tvPhone.setText(phone);

        jobNum = etJobNum.getText().toString();
        duty = etDuty.getText().toString();
        email = etCallSign.getText().toString();
        certificateNum = etCertificateNumber.getText().toString();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_crew_info;
    }

    @OnClick({R.id.iv_back, R.id.rl_nationality, R.id.rl_choose_certificate_type, R.id.tv_belong_ship, R.id.tv_crew_info_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_nationality:
                gotoChooseCountryActivity();
                break;
            case R.id.rl_choose_certificate_type:
                gotoChooseCertificateTypeActivity();
                break;
            case R.id.tv_belong_ship:
                gotoChooseShipActivity();
                break;
            case R.id.tv_crew_info_complete:
                netModifyCrewInfo();
                break;
        }
    }

    private void netModifyCrewInfo() {
        NetUtils.postWithHeader(this, ConstantsUrls.MODIFY_CRWE_INFO)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.ID, crewId)
                .addParams(Constants.PERSON_NAME, personName)
                .addParams(Constants.MOBILEPHONE, phone)
                .addParams(Constants.NATION, country)
                .addParams(Constants.CARDTYPE, certificateTypeNum)
                .addParams(Constants.CARDNO, certificateNum)
                .addParams(Constants.POSTNAME, duty)
                .addParams(Constants.JOB_NO, jobNum)
                .addParams(Constants.EMAIL, email)
                // TODO: 2017/9/7 传选择船id
                .addParams(Constants.SHIP_ID, "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ModifyCrewInfoActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ModifyCrewInfoActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(ModifyCrewInfoActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ModifyCrewInfoActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ModifyCrewInfoActivity.this, LoginRegisterActivity.class));
                                finish();
                            }
                            if (code.equals("200")) {
                                finish();
                            }
                        }
                    }
                });
    }

    private void gotoChooseCountryActivity() {
        startActivityForResult(new Intent(ModifyCrewInfoActivity.this, ChooseCountryActivity.class), Constants.REQUEST_CODE5);
    }

    private void gotoChooseShipActivity() {
        startActivityForResult(new Intent(ModifyCrewInfoActivity.this, ChooseBelongShipActivity.class), Constants.REQUEST_CODE4);
    }

    private void gotoChooseCertificateTypeActivity() {
        startActivityForResult(new Intent(ModifyCrewInfoActivity.this, ChooseCertificateTypeActivity.class), Constants.REQUEST_CODE6);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case Constants.REQUEST_CODE4:
                    String dutyName = data.getStringExtra(Constants.DUTY);
                    etDuty.setText(dutyName);
                    break;
                case Constants.REQUEST_CODE5:
                    country = data.getStringExtra(Constants.COUNTRY);
                    etNationality.setText(country);
                    break;
                case Constants.REQUEST_CODE6:
                    certificateType = data.getStringExtra(Constants.CERTIFICATE_TYPE);
                    certificateTypeNum = data.getStringExtra(Constants.CERTIFICATE_TYPE_NUM);
                    tvType.setText(certificateType);
                    break;
            }
        }
    }

}
