package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.mine.activity.ChooseCertificateTypeActivity.arrCertificates;
import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class ShipCrewInfoActivity extends BaseActivity {
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
        NetUtils.postWithHeader(this, ConstantsUrls.SHIP_CREW_DETAILS)
                .addParams(Constants.ID, id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ShipCrewInfoActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ShipCrewInfoActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
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

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ShipCrewInfoActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ShipCrewInfoActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ShipCrewInfoActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_crew_info;
    }

    @OnClick({R.id.iv_back, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_setting:
                Intent intent = new Intent(ShipCrewInfoActivity.this, ModifyCrewInfoActivity.class);
                intent.putExtra(Constants.CREW_BEAN, crewDetailsBean);
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
