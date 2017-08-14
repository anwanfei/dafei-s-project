package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.utils.Constants;

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
    private String aisShipName;
    private String mmisNumber;
    private String chineseShipName;
    private String contactMailBox;

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

    @OnClick({R.id.iv_back, R.id.rl_choose_group_fleet, R.id.tv_add_ship_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_choose_group_fleet:
                break;
            case R.id.tv_add_ship_complete:
                addShipComplete();
                break;
        }
    }

    private void addShipComplete() {
        aisShipName = etAISShipName.getText().toString();
        mmisNumber = etMMISNumber.getText().toString();
        chineseShipName = etChineseShipName.getText().toString();
        contactMailBox = etContactMailBox.getText().toString();
        if (mmisNumber.equals("")) {
            Toast.makeText(AddShipActivity.this, getResources().getString(R.string.MMIS_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (chineseShipName.equals("")) {
            Toast.makeText(AddShipActivity.this, getResources().getString(R.string.chinese_name_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (contactMailBox.equals("")) {
            Toast.makeText(AddShipActivity.this, getResources().getString(R.string.contact_mail_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        //回调数据
        Intent intent = getIntent();
        intent.putExtra(Constants.SHIP_NAME, chineseShipName);
        setResult(Constants.REQUEST_CODE1, intent);

        //保存到服务器

        finish();


    }
}
