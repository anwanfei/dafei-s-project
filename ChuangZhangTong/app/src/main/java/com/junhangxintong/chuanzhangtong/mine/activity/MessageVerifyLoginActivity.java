package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.MainActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.bean.LoginResultBean;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.MultiVerify;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

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
                netLoginByVerrufyCode();
                break;
            case R.id.tv_pwd_login:
                finish();
                break;
        }
    }

    private void netLoginByVerrufyCode() {

        String phone = etInputPhone.getText().toString();
        String verifyCode = etInputVerifyCode.getText().toString();

        boolean mobile = MultiVerify.isMobile(phone);
        /*OkHttpUtils.post()
                .url(ConstantsUrls.LOGIN_BY_VERIFICATION_CODE)
                .addParams(Constants.PHONE, phone)
                .addParams("vcode", verifyCode)
                .addParams("source", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MessageVerifyLoginActivity.this, com.metaship.wenjiang.utils.Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onServerError(int httpStatus, String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(MessageVerifyLoginActivity.this, com.metaship.wenjiang.utils.Constants.NETWORK_REQUEST_ERROR, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            Toast.makeText(MessageVerifyLoginActivity.this, netServiceErrort.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        NetServiceErrortBean netServiceErrortBean = new Gson().fromJson(response, NetServiceErrortBean.class);
                        String code = netServiceErrortBean.getCode();
                        if (code.equals("200")) {
                            Toast.makeText(MessageVerifyLoginActivity.this, netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            LoginResultBean loginResult = new Gson().fromJson(response, LoginResultBean.class);
                            String message = loginResult.getMessage();
                            Toast.makeText(MessageVerifyLoginActivity.this, message, Toast.LENGTH_SHORT).show();

                            CacheUtils.putString(MessageVerifyLoginActivity.this, Constants.TOKEN, loginResult.getData().getToken());

                            //登录成功回到首页
                            startActivity(new Intent(MessageVerifyLoginActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });*/
        if (mobile) {
            OkHttpUtils
                    .post()
                    .url(ConstantsUrls.LOGIN_BY_VERIFICATION_CODE)
                    .addParams(Constants.PHONE, phone)
                    .addParams(Constants.VCODE, verifyCode)
                    .addParams(Constants.SOURCE, Constants.VCODE_TWO)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(MessageVerifyLoginActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(MessageVerifyLoginActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceErrortBean netServiceErrortBean = new Gson().fromJson(response, NetServiceErrortBean.class);
                                if (!netServiceErrortBean.getCode().equals("200")) {
                                    Toast.makeText(MessageVerifyLoginActivity.this, netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    LoginResultBean loginResult = new Gson().fromJson(response, LoginResultBean.class);
                                    String message = loginResult.getMessage();
                                    Toast.makeText(MessageVerifyLoginActivity.this, message, Toast.LENGTH_SHORT).show();

                                    //保存token
                                    CacheUtils.putString(MessageVerifyLoginActivity.this, Constants.TOKEN, loginResult.getData().getToken());
                                    //保存id
                                    CacheUtils.putString(MessageVerifyLoginActivity.this, Constants.ID, loginResult.getData().getObject().getId());
                                    //保存角色id
                                    CacheUtils.putString(MessageVerifyLoginActivity.this, Constants.ROLEID, loginResult.getData().getObject().getRoleId());


                                    //登录成功回到首页
                                    startActivity(new Intent(MessageVerifyLoginActivity.this, MainActivity.class));
                                    finish();
                                }
                            }

                        }
                    });
        } else {
            Toast.makeText(MessageVerifyLoginActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
        }

    }

    private void netSendVerifyCode() {

        String phone = etInputPhone.getText().toString();
        boolean mobile = MultiVerify.isMobile(phone);
        if (mobile) {


            /*OkHttpUtils
                    .post()
                    .url(ConstantsUrls.SEND_VERIFICATION_CODE)
                    .addParams(Constants.PHONE, phone)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(MessageVerifyLoginActivity.this, com.metaship.wenjiang.utils.Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onServerError(int httpStatus, String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(MessageVerifyLoginActivity.this, com.metaship.wenjiang.utils.Constants.NETWORK_REQUEST_ERROR, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                                Toast.makeText(MessageVerifyLoginActivity.this, netServiceErrort.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            Toast.makeText(MessageVerifyLoginActivity.this, message, Toast.LENGTH_SHORT).show();

                            new CountDownTimer(Constants.COUNTDOWN_TIME, 1000) {
                                @Override
                                public void onTick(long l) {
                                    tvSendVerifyCode.setEnabled(false);
                                    tvSendVerifyCode.setText(Constants.HAS_SENDED + "(" + l / Constants.COUNTDOWN_TIME_SPEED + ")");
                                }

                                @Override
                                public void onFinish() {
                                    tvSendVerifyCode.setEnabled(true);
                                    tvSendVerifyCode.setText(Constants.GET_VERIFYCODE_AGAIN);
                                }
                            }.start();
                        }
                    });*/

            OkHttpUtils
                    .post()
                    .url(ConstantsUrls.SEND_VERIFICATION_CODE)
                    .addParams(Constants.PHONE, phone)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(MessageVerifyLoginActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(MessageVerifyLoginActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                                String message = sendVerifyCode.getMessage();
                                Toast.makeText(MessageVerifyLoginActivity.this, message, Toast.LENGTH_SHORT).show();

                                new CountDownTimer(Constants.COUNTDOWN_TIME, Constants.COUNTDOWN_TIME_SPEED) {
                                    @Override
                                    public void onTick(long l) {
                                        tvSendVerifyCode.setEnabled(false);
                                        tvSendVerifyCode.setText(Constants.HAS_SENDED + "(" + l / Constants.COUNTDOWN_TIME_SPEED + ")");
                                    }

                                    @Override
                                    public void onFinish() {
                                        tvSendVerifyCode.setEnabled(true);
                                        tvSendVerifyCode.setText(Constants.GET_VERIFYCODE_AGAIN);
                                    }
                                }.start();
                            }
                        }
                    });
        } else {
            Toast.makeText(MessageVerifyLoginActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
        }

    }
}
