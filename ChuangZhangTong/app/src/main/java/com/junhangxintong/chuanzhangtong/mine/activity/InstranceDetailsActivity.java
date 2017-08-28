package com.junhangxintong.chuanzhangtong.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class InstranceDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_insurance_type)
    TextView tvInsuranceType;
    @BindView(R.id.tv_ship_name)
    TextView tvShipName;
    @BindView(R.id.tv_ship_bianhao)
    TextView tvShipBianhao;
    @BindView(R.id.tv_ship_imo)
    TextView tvShipImo;
    @BindView(R.id.rl_certificate_type)
    RelativeLayout rlCertificateType;
    @BindView(R.id.tv_ship_nationality_harbor)
    TextView tvShipNationalityHarbor;
    @BindView(R.id.tv_name_address_of_ship)
    TextView tvNameAddressOfShip;
    @BindView(R.id.tv_gurantee_type)
    TextView tvGuranteeType;
    @BindView(R.id.tv_issue_date)
    TextView tvIssueDate;
    @BindView(R.id.tv_issuing_authority)
    TextView tvIssuingAuthority;
    @BindView(R.id.tv_issue_address)
    TextView tvIssueAddress;
    @BindView(R.id.rl_issuing_authority)
    RelativeLayout rlIssuingAuthority;
    @BindView(R.id.tv_is_permanent_effective)
    TextView tvIsPermanentEffective;
    @BindView(R.id.tv_effective_date)
    TextView tvEffectiveDate;
    @BindView(R.id.rl_effective_date)
    RelativeLayout rlEffectiveDate;
    @BindView(R.id.tv_warning_days)
    TextView tvWarningDays;
    @BindView(R.id.rl_re_warning_days)
    RelativeLayout rlReWarningDays;
    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.iv_certificate_phone)
    ImageView ivCertificatePhone;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_insturance));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_instrance_details;
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.tv_setting:
                break;
        }
    }
}
