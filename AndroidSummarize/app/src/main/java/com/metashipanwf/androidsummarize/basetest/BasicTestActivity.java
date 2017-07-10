package com.metashipanwf.androidsummarize.basetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.utils.ToolPhone;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicTestActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.btn_opengps)
    Button btnOpengps;
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
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_share)
    TextView tvShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_test);
        ButterKnife.bind(this);

        initData();

        initListener();

    }

    private void initListener() {
        btnCall.setOnClickListener(this);
        btnCalled.setOnClickListener(this);
        btnCarema.setOnClickListener(this);
        btnContact.setOnClickListener(this);
        btnOpengps.setOnClickListener(this);
        btnPhoto.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        tvBack.setOnClickListener(this);
    }

    private void initData() {
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("常用基本操作");
        frameRoot = (LinearLayout)findViewById(R.id.frame_root);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_call:
                ToolPhone.toCallPhoneActivity(this,"10086");
                break;
            case R.id.btn_called:
                ToolPhone.callPhone(this,"10086");
                break;
            case R.id.btn_contact:
                ToolPhone.toChooseContactsList(this,99);
                break;
            case R.id.btn_setting:
                ToolPhone.toSettingActivity(this);
                break;
            case R.id.btn_carema:
                ToolPhone.toCameraActivity(this,88);
                break;
            case R.id.btn_photo:
                ToolPhone.toImagePickerActivity(this, 77);
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

}
