package com.junhangxintong.chuanzhangtong.mine.activity;

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

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;

import butterknife.BindView;
import butterknife.OnClick;

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
    private String isEffectiveForever = Constants.NO;
    private String isOftonUse = Constants.YES;
    private String reWarningDays = Constants.DAYS_30;

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
                    Toast.makeText(AddInsuranceActivity.this, rbNo.getText().toString(), Toast.LENGTH_SHORT).show();
                    isEffectiveForever = rbNo.getText().toString();
                    rlEffectiveDate.setVisibility(View.VISIBLE);
                    rlReWarningDays.setVisibility(View.VISIBLE);
                } else if (i == rbYes.getId()) {
                    Toast.makeText(AddInsuranceActivity.this, rbYes.getText().toString(), Toast.LENGTH_SHORT).show();
                    isEffectiveForever = rbYes.getText().toString();
                    rlEffectiveDate.setVisibility(View.GONE);
                    rlReWarningDays.setVisibility(View.GONE);
                }
            }
        });

        rgIsOftonUse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbIsOftonUse.getId()) {
                    Toast.makeText(AddInsuranceActivity.this, rbIsOftonUse.getText().toString(), Toast.LENGTH_SHORT).show();
                    isOftonUse = rbIsOftonUse.getText().toString();
                } else if (i == rbNoOftonUse.getId()) {
                    Toast.makeText(AddInsuranceActivity.this, rbNoOftonUse.getText().toString(), Toast.LENGTH_SHORT).show();
                    isOftonUse = rbNoOftonUse.getText().toString();
                }
            }
        });

        rgWarnDays.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbTime30Days.getId()) {
                    Toast.makeText(AddInsuranceActivity.this, rbTime30Days.getText().toString(), Toast.LENGTH_SHORT).show();
                    reWarningDays = rbTime30Days.getText().toString();
                } else if (i == rbTime90Days.getId()) {
                    Toast.makeText(AddInsuranceActivity.this, rbTime90Days.getText().toString(), Toast.LENGTH_SHORT).show();
                    reWarningDays = rbTime90Days.getText().toString();
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
                // TODO: 2017/8/30 上传网络参数
                Toast.makeText(AddInsuranceActivity.this, getResources().getString(R.string.add_success), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
