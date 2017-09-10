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
import com.junhangxintong.chuanzhangtong.mine.bean.CrewBean;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.MultiVerify;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class CrewInfoInputActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.et_nationality)
    TextView etNationality;
    @BindView(R.id.et_job_num)
    EditText etJobNum;
    @BindView(R.id.et_duty)
    EditText etDuty;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_call_sign)
    EditText etCallSign;
    @BindView(R.id.tv_crew_info_complete)
    TextView tvCrewInfoComplete;
    @BindView(R.id.rl_choose_duty)
    RelativeLayout rlChooseDuty;
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
    private String country = "中国";
    private String certificateTypeNum = "";
    private String belongShip = "";
    private String certificateType = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.crew_info_input));
    }

    @Override
    protected void initData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crew_info_input;
    }

    @OnClick({R.id.iv_back, R.id.et_nationality, R.id.tv_belong_ship, R.id.tv_crew_info_complete, R.id.rl_choose_duty, R.id.rl_nationality, R.id.rl_choose_certificate_type, R.id.tv_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_nationality:
                gotoChooseCountryActivity();
                break;
            case R.id.tv_crew_info_complete:
                addCrewComplete();
                break;
            case R.id.rl_choose_duty:
                gotoChooseShipActivity();
                break;
            case R.id.tv_belong_ship:
                gotoChooseShipActivity();
                break;
            case R.id.rl_nationality:
                gotoChooseCountryActivity();
                break;
            case R.id.rl_choose_certificate_type:
                gotoChooseCertificateTypeActivity();
                break;
            case R.id.tv_type:
                gotoChooseCertificateTypeActivity();
                break;
        }
    }

    private void gotoChooseShipActivity() {
        startActivityForResult(new Intent(CrewInfoInputActivity.this, ChooseBelongShipActivity.class), Constants.REQUEST_CODE4);
    }

    private void gotoChooseCertificateTypeActivity() {
        startActivityForResult(new Intent(CrewInfoInputActivity.this, ChooseCertificateTypeActivity.class), Constants.REQUEST_CODE6);
    }

    private void gotoChooseCountryActivity() {
        startActivityForResult(new Intent(CrewInfoInputActivity.this, ChooseCountryActivity.class), Constants.REQUEST_CODE5);
    }

    private void addCrewComplete() {

        String userId = CacheUtils.getString(this, Constants.ID);

        CrewBean crewBean = new CrewBean();

        String crewName = etInputName.getText().toString();
        String jobNum = etJobNum.getText().toString();
        String duty = etDuty.getText().toString();
        String crewPhone = etPhone.getText().toString();
        String email = etCallSign.getText().toString();
        String certificateNum = etCertificateNumber.getText().toString();

        boolean mobile = MultiVerify.isMobile(crewPhone);

        if (crewName.equals("")) {
            Toast.makeText(CrewInfoInputActivity.this, getResources().getString(R.string.crew_name_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mobile) {
            Toast.makeText(CrewInfoInputActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (jobNum.equals("")) {
            Toast.makeText(CrewInfoInputActivity.this, getResources().getString(R.string.input_job_num), Toast.LENGTH_SHORT).show();
            return;
        }

        if (duty.equals("")) {
            Toast.makeText(CrewInfoInputActivity.this, getResources().getString(R.string.input_duty), Toast.LENGTH_SHORT).show();
            return;
        }

        NetUtils.postWithHeader(this, ConstantsUrls.ADD_CREW)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.PERSON_NAME, crewName)
                .addParams(Constants.MOBILEPHONE, crewPhone)
                .addParams(Constants.NATION, country)
                .addParams(Constants.CARDTYPE, certificateType)
                .addParams(Constants.CARDNO, certificateTypeNum)
                .addParams(Constants.POSTNAME, duty)
                .addParams(Constants.JOB_NO, jobNum)
                .addParams(Constants.EMAIL, email)
                // TODO: 2017/9/7 传选择船id
                .addParams(Constants.SHIP_ID, "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(CrewInfoInputActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(CrewInfoInputActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(CrewInfoInputActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(CrewInfoInputActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(CrewInfoInputActivity.this, LoginRegisterActivity.class));
                                finish();
                            }
                            if (code.equals("200")) {
                                finish();
                            }
                        }
                    }
                });

     /*   crewBean.setCrewName(crewName);
        crewBean.setJobNum(jobNum);
        crewBean.setDuty(duty);
        crewBean.setPhoneNum(crewPhone);

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.CREW_BEAN, crewBean);
        intent.putExtras(bundle);

        setResult(Constants.REQUEST_CODE0, intent);

        finish();*/
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
                    tvType.setText(certificateTypeNum);
                    break;
            }
        }
    }
}
