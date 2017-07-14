package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.et_input_verify_code)
    EditText etInputVerifyCode;
    @BindView(R.id.tv_send_verify_code)
    TextView tvSendVerifyCode;
    @BindView(R.id.et_input_pwd)
    EditText etInputPwd;
    @BindView(R.id.rb_chuanguan)
    RadioButton rbChuanguan;
    @BindView(R.id.rb_chuanyuan)
    RadioButton rbChuanyuan;
    @BindView(R.id.rb_other)
    RadioButton rbOther;
    @BindView(R.id.rg_identity)
    RadioGroup rgIdentity;
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
        tvPwdLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.tv_send_verify_code, R.id.tv_login, R.id.tv_pwd_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_verify_code:
                break;
            case R.id.tv_login:
                startActivity(new Intent(RegisterActivity.this, BindManageMailBoxActivity.class));
                break;
            case R.id.tv_pwd_login:
                finish();
                break;
        }
    }
}
