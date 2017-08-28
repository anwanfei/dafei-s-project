package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.AddCrewActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
                startActivity(new Intent(CrewDetailsActivity.this, CrewInfoInputActivity.class));
                break;
        }
    }
}
