package com.junhangxintong.chuanzhangtong.mine.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageVerifyLoginActivity extends BaseActivity {

    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.et_input_verify_code)
    EditText etInputVerifyCode;
    @BindView(R.id.tv_send_verify_code)
    TextView tvSendVerifyCode;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_pwd_login)
    TextView tvPwdLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //設置下劃綫
        tvPwdLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_message_verify_login;
    }

    @OnClick({R.id.tv_send_verify_code, R.id.tv_login, R.id.tv_pwd_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_verify_code:
                break;
            case R.id.tv_login:
                break;
            case R.id.tv_pwd_login:
                finish();
                break;
        }
    }
}
