package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.junhangxintong.chuanzhangtong.mine.activity.ChooseCertificateTypeActivity.arrCertificates;

public class CrewDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_crew_name)
    TextView tvCrewName;
    @BindView(R.id.tv_crew_phone)
    TextView tvCrewPhone;
    @BindView(R.id.tv_crew_nationality)
    TextView tvCrewNationality;
    @BindView(R.id.tv_ceretificate_type)
    TextView tvCeretificateType;
    @BindView(R.id.tv_crwe_certificate_number)
    TextView tvCrweCertificateNumber;
    @BindView(R.id.tv_belong_ship)
    TextView tvBelongShip;
    @BindView(R.id.tv_crew_duty)
    TextView tvCrewDuty;
    @BindView(R.id.tv_crew_job_num)
    TextView tvCrewJobNum;
    @BindView(R.id.tv_crew_mail_box)
    TextView tvCrewMailBox;
    private String personName;
    private String mobilePhone;
    private String crewId;
    private String id;
    private CrewDetailsBean crewDetailsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.crew_details));
        tvSetting.setVisibility(View.VISIBLE);
        tvSetting.setText(getResources().getString(R.string.edit));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
        netGetCrewInfo();
    }

    private void netGetCrewInfo() {
        NetUtils.postWithHeader(this, ConstantsUrls.CREW_DETAILS)
                .addParams(Constants.ID, id)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        crewDetailsBean = new Gson().fromJson(response, CrewDetailsBean.class);
                        CrewDetailsBean.DataBean.ObjectBean crewObject = crewDetailsBean.getData().getObject();
                        mobilePhone = crewObject.getMobilePhone();
                        crewId = crewObject.getId();
                        personName = crewObject.getPersonName();
                        tvCrewName.setText(personName);
                        tvCrewPhone.setText(mobilePhone);
                        tvCrewNationality.setText(crewObject.getNation());
                        String cardType = crewObject.getCardType();
                        switch (cardType) {
                            case "1":
                                tvCeretificateType.setText(arrCertificates[0]);
                                break;
                            case "2":
                                tvCeretificateType.setText(arrCertificates[1]);
                                break;
                            case "3":
                                tvCeretificateType.setText(arrCertificates[2]);
                                break;
                            case "4":
                                tvCeretificateType.setText(arrCertificates[3]);
                                break;
                            case "5":
                                tvCeretificateType.setText(arrCertificates[4]);
                                break;
                        }

                        tvCrweCertificateNumber.setText(crewObject.getCardNo());
                        tvBelongShip.setText(crewObject.getShipName());
                        tvCrewDuty.setText(crewObject.getPostName());
                        tvCrewJobNum.setText(crewObject.getJobNo());
                        tvCrewMailBox.setText(crewObject.getEmail());
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crew_details;
    }

    @OnClick({R.id.iv_back, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_setting:
                Intent intent = new Intent(CrewDetailsActivity.this, ModifyCrewInfoActivity.class);
                intent.putExtra(Constants.CREW_BEAN,crewDetailsBean);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        netGetCrewInfo();
    }
}
