package com.junhangxintong.chuanzhangtong.dynamic.activity;

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
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicCrewDetails;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class CrewCertificateActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_duty)
    TextView tvDuty;
    @BindView(R.id.tv_certificate_name)
    TextView tvCertificateName;
    @BindView(R.id.tv_certificate_number)
    TextView tvCertificateNumber;
    @BindView(R.id.tv_issue_date)
    TextView tvIssueDate;
    @BindView(R.id.tv_certificate_end_time)
    TextView tvCertificateEndTime;
    @BindView(R.id.tv_certificate_issue_postion)
    TextView tvCertificateIssuePostion;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_sign_no_read)
    TextView tvSignNoRead;
    @BindView(R.id.tv_place_top)
    TextView tvPlaceTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.crew_certificate));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra(Constants.ID);
        NetUtils.postWithHeader(this, ConstantsUrls.DYNAMIC_DETAILS)
                .addParams(Constants.ID, id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(CrewCertificateActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(CrewCertificateActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                DynamicCrewDetails shipCertificateExpireBean = new Gson().fromJson(response, DynamicCrewDetails.class);
                                DynamicCrewDetails.DataBean.ObjectBean shipCertificateExpireDetatls = shipCertificateExpireBean.getData().getObject();
                                tvName.setText(shipCertificateExpireDetatls.getPersonName());
                                tvCertificateName.setText(shipCertificateExpireDetatls.getName());
                                tvCertificateNumber.setText(shipCertificateExpireDetatls.getCertifNo());
                                int certifType = shipCertificateExpireDetatls.getCertifType();
                                if (certifType == 1) {
                                    tvIssueDate.setText(getResources().getString(R.string.certificate));
                                }
                                tvCertificateEndTime.setText(shipCertificateExpireDetatls.getValidDate());
                                tvCertificateIssuePostion.setText(shipCertificateExpireDetatls.getIssueOrganization());

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(CrewCertificateActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(CrewCertificateActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else {
                                Toast.makeText(CrewCertificateActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crew_certificate;
    }

    @OnClick({R.id.iv_back, R.id.tv_share, R.id.tv_sign_no_read, R.id.tv_place_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_sign_no_read:
                break;
            case R.id.tv_place_top:
                break;
        }
    }
}
