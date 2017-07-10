package com.junhangxintong.chuangzhangtong.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CrewInfoInputActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.et_ID)
    EditText etID;
    @BindView(R.id.et_nationality)
    TextView etNationality;
    @BindView(R.id.et_job_num)
    EditText etJobNum;
    @BindView(R.id.et_duty)
    TextView etDuty;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_call_sign)
    EditText etCallSign;
    @BindView(R.id.tv_crew_info_complete)
    TextView tvCrewInfoComplete;

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

    @OnClick({R.id.iv_back, R.id.et_nationality, R.id.et_duty, R.id.tv_crew_info_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_nationality:
                break;
            case R.id.et_duty:
                break;
            case R.id.tv_crew_info_complete:
                break;
        }
    }
}
