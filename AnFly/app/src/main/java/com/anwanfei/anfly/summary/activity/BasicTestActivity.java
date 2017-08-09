package com.anwanfei.anfly.summary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;
import com.anwanfei.anfly.utils.phoneUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class BasicTestActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_call)
    Button btnCall;
    @BindView(R.id.btn_called)
    Button btnCalled;
    @BindView(R.id.btn_contact)
    Button btnContact;
    @BindView(R.id.btn_setting)
    Button btnSetting;
    @BindView(R.id.btn_carema)
    Button btnCarema;
    @BindView(R.id.btn_photo)
    Button btnPhoto;
    @BindView(R.id.frame_root)
    LinearLayout frameRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.basic_operation));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_basic_test;
    }

    @OnClick({R.id.iv_back,     R.id.btn_call, R.id.btn_called, R.id.btn_contact, R.id.btn_setting, R.id.btn_carema, R.id.btn_photo, R.id.frame_root})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_call:
                phoneUtils.toCallPhoneActivity(this,"10086");
                break;
            case R.id.btn_called:
                phoneUtils.callPhone(this,"10086");
                break;
            case R.id.btn_contact:
                phoneUtils.toChooseContactsList(this,99);
                break;
            case R.id.btn_setting:
                phoneUtils.toSettingActivity(this);
                break;
            case R.id.btn_carema:
                phoneUtils.toCameraActivity(this,88);
                break;
            case R.id.btn_photo:
                phoneUtils.toImagePickerActivity(this, 77);
                break;
        }
    }
}
