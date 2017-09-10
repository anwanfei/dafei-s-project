package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewCertificateDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class CertificateIndetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_input_certificate_name)
    TextView tvInputCertificateName;
    @BindView(R.id.tv_certificate_number)
    TextView tvCertificateNumber;
    @BindView(R.id.et_certificate_type)
    TextView etCertificateType;
    @BindView(R.id.rl_certificate_type)
    RelativeLayout rlCertificateType;
    @BindView(R.id.tv_issuing_authority)
    TextView tvIssuingAuthority;
    @BindView(R.id.rl_issuing_authority)
    RelativeLayout rlIssuingAuthority;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_warning_days)
    TextView tvWarningDays;
    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.iv_certificate_phone)
    ImageView ivCertificatePhone;
    @BindView(R.id.rl_warning_days)
    RelativeLayout rlWarningDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.certificate_details));
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        String id = intent.getStringExtra(Constants.ID);

        int certificate_from_type = intent.getIntExtra(Constants.CERTIFICATE_TYPE, 0);
        if (certificate_from_type == 1) {
            rlCertificateType.setVisibility(View.GONE);
        } else {
            rlCertificateType.setVisibility(View.VISIBLE);
        }
        NetUtils.postWithHeader(this, ConstantsUrls.CREW_CERTIFICATE_DETAILS)
                .addParams(Constants.ID, id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(CertificateIndetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(CertificateIndetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                CrewCertificateDetailsBean crewCertificateDetailsBean = new Gson().fromJson(response, CrewCertificateDetailsBean.class);
                                CrewCertificateDetailsBean.DataBean.ObjectBean crewCertificateDetails = crewCertificateDetailsBean.getData().getObject();
                                int isUse = crewCertificateDetails.getIsUse();
                                int isValid = crewCertificateDetails.getIsValid();
                                tvInputCertificateName.setText(crewCertificateDetails.getName());
                                tvCertificateNumber.setText(crewCertificateDetails.getCertifNo());
                                tvIssuingAuthority.setText(crewCertificateDetails.getIssueOrganization());

                                if (isValid == 1) {
                                    rlWarningDays.setVisibility(View.GONE);
                                    tvEndDate.setText(getResources().getString(R.string.permanent_effective));
                                } else if (isValid == 2) {
                                    rlWarningDays.setVisibility(View.VISIBLE);
                                    tvWarningDays.setText(String.valueOf(crewCertificateDetails.getAdvanceWarnDay()));
                                    tvEndDate.setText(crewCertificateDetails.getValidDate());
                                }

                                if (isUse == 1) {
                                    tvCommon.setText(getResources().getString(R.string.commoned));
                                } else if (isUse == 2) {
                                    tvCommon.setText(getResources().getString(R.string.no_commoned));
                                }

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(CertificateIndetailsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(CertificateIndetailsActivity.this, LoginRegisterActivity.class));
                            } else {
                                Toast.makeText(CertificateIndetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certificate_indetails;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
