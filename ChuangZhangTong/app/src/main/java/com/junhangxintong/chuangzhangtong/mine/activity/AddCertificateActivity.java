package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class AddCertificateActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_certificate_name)
    EditText etInputCertificateName;
    @BindView(R.id.et_certificate_number)
    EditText etCertificateNumber;
    @BindView(R.id.et_certificate_type)
    TextView etCertificateType;
    @BindView(R.id.rl_certificate_type)
    RelativeLayout rlCertificateType;
    @BindView(R.id.tv_issuing_authority)
    TextView tvIssuingAuthority;
    @BindView(R.id.rl_issuing_authority)
    RelativeLayout rlIssuingAuthority;
    @BindView(R.id.et_end_date)
    EditText etEndDate;
    @BindView(R.id.cb_permanet_effective)
    CheckBox cbPermanetEffective;
    @BindView(R.id.rb_time_30_days)
    RadioButton rbTime30Days;
    @BindView(R.id.rb_time_90_days)
    RadioButton rbTime90Days;
    @BindView(R.id.rb_commoned)
    RadioButton rbCommoned;
    @BindView(R.id.rb_no_commoned)
    RadioButton rbNoCommoned;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.tv_crew_info_complete)
    TextView tvCrewInfoComplete;
    private String certificateName;
    private String certificateNum;
    private String certificateType;
    private String issuingAuthory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.add_certificate));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_certificate;
    }

    @OnClick({R.id.iv_back, R.id.et_certificate_type, R.id.rl_certificate_type, R.id.tv_issuing_authority, R.id.rl_issuing_authority, R.id.tv_add_certificate_photo, R.id.tv_crew_info_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_certificate_type:
                break;
            case R.id.rl_certificate_type:
                break;
            case R.id.tv_issuing_authority:
                break;
            case R.id.rl_issuing_authority:
                break;
            case R.id.tv_add_certificate_photo:
                break;
            case R.id.tv_crew_info_complete:
                addCertificateComplete();
                break;
        }
    }

    private void addCertificateComplete() {
        certificateName = etInputCertificateName.getText().toString();
        certificateNum = etCertificateNumber.getText().toString();
        certificateType = etCertificateType.getText().toString();
        issuingAuthory = tvIssuingAuthority.getText().toString();

        if(certificateName.equals("")) {
            Toast.makeText(AddCertificateActivity.this, getResources().getString(R.string.certificate_name_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if(certificateNum.equals("")) {
            Toast.makeText(AddCertificateActivity.this, getResources().getString(R.string.certificate_num_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if(certificateType.equals("")) {
            Toast.makeText(AddCertificateActivity.this, getResources().getString(R.string.certificate_type_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if(issuingAuthory.equals("")) {
            Toast.makeText(AddCertificateActivity.this, getResources().getString(R.string.certificate_issuing_authory_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = getIntent();
        intent.putExtra(Constants.CERTIFICATE_NAME,certificateName);
        intent.putExtra(Constants.CERTIFICATENUM,certificateNum);
        intent.putExtra(Constants.CERTIFICATETYPE,certificateType);
        intent.putExtra(Constants.ISSUINGAUTHORY,issuingAuthory);

        setResult(Constants.REQUEST_CODE0,intent);

        finish();
    }
}
