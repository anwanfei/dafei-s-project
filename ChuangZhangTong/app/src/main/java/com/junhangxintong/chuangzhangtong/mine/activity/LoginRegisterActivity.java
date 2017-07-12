package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.common.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginRegisterActivity extends BaseActivity {

    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.et_input_pwd)
    EditText etInputPwd;
    @BindView(R.id.tv_verify_mesage_login)
    TextView tvVerifyMesageLogin;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.iv_wechat)
    ImageView ivWechat;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.iv_weibo)
    ImageView ivWeibo;
    @BindView(R.id.ll_login_regiter)
    LinearLayout llLoginRegiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_register;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                Intent intent = new Intent(LoginRegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.tv_verify_mesage_login, R.id.tv_forget_pwd, R.id.tv_login, R.id.tv_register, R.id.iv_wechat, R.id.iv_qq, R.id.iv_weibo, R.id.ll_login_regiter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verify_mesage_login:
                startActivity(new Intent(LoginRegisterActivity.this, MessageVerifyLoginActivity.class));
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(LoginRegisterActivity.this, ForgetPwdActivity.class));
                break;
            case R.id.tv_login:
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginRegisterActivity.this, RegisterActivity.class));
                break;
            case R.id.iv_wechat:
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weibo:
                break;
            case R.id.ll_login_regiter:
                break;
        }
    }
}
