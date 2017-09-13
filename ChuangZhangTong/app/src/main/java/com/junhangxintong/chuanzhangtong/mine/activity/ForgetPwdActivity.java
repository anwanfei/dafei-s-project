package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.bean.LoginResultBean;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.MultiVerify;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.et_input_verify_code)
    EditText etInputVerifyCode;
    @BindView(R.id.tv_send_verify_code)
    TextView tvSendVerifyCode;
    @BindView(R.id.tv_next)
    TextView tvNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        etInputPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvNext.setTextColor(getResources().getColor(R.color.white));
                tvNext.setBackgroundResource(R.drawable.tv_bg);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etInputPhone.getText().toString().isEmpty()) {
                    tvNext.setTextColor(getResources().getColor(R.color.black));
                    tvNext.setBackgroundResource(R.drawable.bg_identity_gray);
                }
            }
        });
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.forget_pwd));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @OnClick({R.id.iv_back, R.id.tv_send_verify_code, R.id.tv_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_send_verify_code:
                netSendVerifyCode();
                break;
            case R.id.tv_next:
                netModifyPwd();
                break;
        }
    }

    private void netModifyPwd() {
        final String phone = etInputPhone.getText().toString();
        final String verifyCode = etInputVerifyCode.getText().toString();

        boolean mobile = MultiVerify.isMobile(phone);

        if (mobile) {

            NetUtils.postWithNoHeader(this,ConstantsUrls.VERIFY_SMS_FORGET_PWD)
                    .addParams(Constants.PHONE, phone)
                    .addParams(Constants.VCODE, verifyCode)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ForgetPwdActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(ForgetPwdActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceErrortBean netServiceErrortBean = new Gson().fromJson(response, NetServiceErrortBean.class);
                                if (!netServiceErrortBean.getCode().equals("200")) {
                                    Toast.makeText(ForgetPwdActivity.this, netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    LoginResultBean loginResult = new Gson().fromJson(response, LoginResultBean.class);
                                    String message = loginResult.getMessage();
                                    Toast.makeText(ForgetPwdActivity.this, message, Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(ForgetPwdActivity.this, InputPwdActivity.class);
                                    intent.putExtra(Constants.PHONE, phone);
                                    intent.putExtra(Constants.VCODE, verifyCode);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                        }
                    });
        } else {
            Toast.makeText(ForgetPwdActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
        }
    }

    private void netSendVerifyCode() {

        String phone = etInputPhone.getText().toString();
        boolean mobile = MultiVerify.isMobile(phone);
        if (mobile) {
            NetUtils.postWithNoHeader(this, ConstantsUrls.SEND_VERIFICATION_CODE_FORGET_PWD)
                    .addParams(Constants.PHONE, phone)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ForgetPwdActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(ForgetPwdActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                                String message = sendVerifyCode.getMessage();
                                Toast.makeText(ForgetPwdActivity.this, message, Toast.LENGTH_SHORT).show();

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
            Toast.makeText(ForgetPwdActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
        }
    }
}
