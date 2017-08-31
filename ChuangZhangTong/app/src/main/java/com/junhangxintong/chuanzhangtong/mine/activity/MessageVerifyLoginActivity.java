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
                netSendVerifyCode();
                break;
            case R.id.tv_login:
                netLoginByPhone();
                break;
            case R.id.tv_pwd_login:
                finish();
                break;
        }
    }

    private void netLoginByPhone() {
        String phone = etInputPhone.getText().toString();
        String verifyCode = etInputVerifyCode.getText().toString();
        /*OkHttpUtils
                .post()
                .url(ConstantsUrls.LOGIN_BY_PHNOE)
                .addParams(Cons.PHONE, phone)
                .addParams("vcode", verifyCode)
                .addParams("source", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MessageVerifyLoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LoginResultBean loginResult = new Gson().fromJson(response, LoginResultBean.class);
                        String message = loginResult.getMessage();
                        Toast.makeText(MessageVerifyLoginActivity.this, message, Toast.LENGTH_SHORT).show();

                        CacheUtils.putString(MessageVerifyLoginActivity.this, Cons.TOKEN, loginResult.getData().getToken());

                        //登录成功回到首页
                        startActivity(new Intent(MessageVerifyLoginActivity.this, MainActivity.class));
                        finish();
                    }
                });*/
//        OkHttpUtils.post().addParams()
    }

    private void netSendVerifyCode() {

        String phone = etInputPhone.getText().toString();
       /* OkHttpUtils
                .post()
                .url(ConstantsUrls.SEND_VERIFICATION_CODE)
                .addParams(Cons.PHONE, phone)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MessageVerifyLoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                        String message = sendVerifyCode.getMessage();
                        Toast.makeText(MessageVerifyLoginActivity.this, message, Toast.LENGTH_SHORT).show();

                        new CountDownTimer(Cons.COUNTDOWN_TIME, 1000) {
                            @Override
                            public void onTick(long l) {
                                tvSendVerifyCode.setEnabled(false);
                                tvSendVerifyCode.setText(Cons.HAS_SENDED + "(" + l / Cons.COUNTDOWN_TIME_SPEED + ")");
                            }

                            @Override
                            public void onFinish() {
                                tvSendVerifyCode.setEnabled(true);
                                tvSendVerifyCode.setText(Cons.GET_VERIFYCODE_AGAIN);
                            }
                        }.start();
                    }
                });*/
    }
}
