package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.MultiVerify;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import org.apache.commons.lang.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

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
    private String certificateTypeNum = "1";
    private String belongShip = "";
    private String certificateType = "1";
    private String shipId = "";
    private boolean isFromShip;

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
        Intent intent = getIntent();
        isFromShip = intent.getBooleanExtra(Constants.FROM_SHIP, false);

        if (isFromShip) {
            rlChooseDuty.setVisibility(View.GONE);
            shipId = intent.getStringExtra(Constants.SHIP_ID);
        }
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

        boolean mail = MultiVerify.isMail(email);
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


        if (StringUtils.isNotBlank(email)) {
            if (!mail) {
                Toast.makeText(CrewInfoInputActivity.this, getResources().getString(R.string.email_error), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (!isFromShip) {
            if (StringUtils.isBlank(shipId)) {
                Toast.makeText(CrewInfoInputActivity.this, getResources().getString(R.string.choose_belong_ship), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        NetUtils.postWithHeader(this, ConstantsUrls.ADD_CREW)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.PERSON_NAME, crewName)
                .addParams(Constants.MOBILEPHONE, crewPhone)
                .addParams(Constants.NATION, country)
                .addParams(Constants.CARDTYPE, certificateTypeNum)
                .addParams(Constants.CARDNO, certificateNum)
                .addParams(Constants.POSTNAME, duty)
                .addParams(Constants.JOB_NO, jobNum)
                .addParams(Constants.EMAIL, email)
                // TODO: 2017/9/7 传选择船id
                .addParams(Constants.SHIP_ID, shipId)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case Constants.REQUEST_CODE4:
                    String shipName = data.getStringExtra(Constants.SHIP_NAME);
                    shipId = data.getStringExtra(Constants.SHIP_ID);
                    tvBelongShip.setText(shipName);
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
