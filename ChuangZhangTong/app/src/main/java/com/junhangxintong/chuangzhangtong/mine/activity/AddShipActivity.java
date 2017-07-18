package com.junhangxintong.chuangzhangtong.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddShipActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_MMIS_number)
    EditText etMMISNumber;
    @BindView(R.id.et_call_sign)
    EditText etCallSign;
    @BindView(R.id.et_AIS_ship_name)
    EditText etAISShipName;
    @BindView(R.id.et_chinese_ship_name)
    EditText etChineseShipName;
    @BindView(R.id.tv_fleet_group)
    TextView tvFleetGroup;
    @BindView(R.id.rl_choose_group_fleet)
    RelativeLayout rlChooseGroupFleet;
    @BindView(R.id.et_contact_mail_box)
    EditText etContactMailBox;
    @BindView(R.id.tv_add_ship_complete)
    TextView tvAddShipComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.add_ship));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_ship;
    }

    @OnClick({R.id.iv_back, R.id.tv_fleet_group, R.id.rl_choose_group_fleet, R.id.tv_add_ship_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_fleet_group:
                break;
            case R.id.rl_choose_group_fleet:
                break;
            case R.id.tv_add_ship_complete:
                finish();
                break;
        }
    }
}
