package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.utils.Constants;

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
    @BindView(R.id.rl_choose_duty)
    RelativeLayout rlChooseDuty;

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

    @OnClick({R.id.iv_back, R.id.et_nationality, R.id.et_duty, R.id.tv_crew_info_complete, R.id.rl_choose_duty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.et_nationality:
                break;
            case R.id.tv_crew_info_complete:
                break;
            case R.id.rl_choose_duty:
                startActivityForResult(new Intent(CrewInfoInputActivity.this, ChooseDutyActivity.class), Constants.REQUEST_CODE4);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case Constants.REQUEST_CODE4:
                    String dutyName = data.getStringExtra(Constants.DUTY);
                    etDuty.setText(dutyName);
                    break;
            }
        }
    }
}
