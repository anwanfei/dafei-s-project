package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.MainActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.bean.LoginResultBean;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.MultiVerify;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

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
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_pwd_login)
    TextView tvPwdLogin;
    @BindView(R.id.rb_chuanzhang)
    RadioButton rbChuanzhang;
    @BindView(R.id.et_input_confirm_pwd)
    EditText etInputConfirmPwd;
    private String roleId = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    @Override
    protected void initView() {

    }

    private void initListener() {
        rgIdentity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbChuanguan.getId()) {
                    Toast.makeText(RegisterActivity.this, rbChuanguan.getText().toString(), Toast.LENGTH_SHORT).show();
                    roleId = "1";
                } else if (i == rbChuanzhang.getId()) {
                    Toast.makeText(RegisterActivity.this, rbChuanzhang.getText().toString(), Toast.LENGTH_SHORT).show();
                    roleId = "2";
                } else if (i == rbChuanyuan.getId()) {
                    Toast.makeText(RegisterActivity.this, rbChuanyuan.getText().toString(), Toast.LENGTH_SHORT).show();
                    roleId = "3";
                } else if (i == rbOther.getId()) {
                    Toast.makeText(RegisterActivity.this, rbOther.getText().toString(), Toast.LENGTH_SHORT).show();
                    roleId = "4";
                }
            }
        });
    }

    @Override
    protected void initData() {
        tvPwdLogin.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.tv_send_verify_code, R.id.tv_register, R.id.tv_pwd_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_verify_code:
                netSendVerifyCode();
                break;
            case R.id.tv_register:
                netRegister();
                break;
            case R.id.tv_pwd_login:
                finish();
                break;
        }
    }

    private void netRegister() {
        String phone = etInputPhone.getText().toString();
        String verifyCode = etInputVerifyCode.getText().toString();
        String pwd = etInputPwd.getText().toString();
        String confirmPwd = etInputConfirmPwd.getText().toString();
        boolean mobile = MultiVerify.isMobile(phone);

        if (!mobile) {
            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.isEmpty(verifyCode)) {
            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.input_verify_code), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(pwd)) {
            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.input_pwd), Toast.LENGTH_SHORT).show();
            return;
        }
        if (StringUtils.isEmpty(confirmPwd)) {
            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.input_pwd_agian), Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pwd.equals(confirmPwd)) {
            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.two_pwd_not_equal), Toast.LENGTH_SHORT).show();
            return;
        }

        NetUtils.postWithNoHeader(this, ConstantsUrls.REGISTER_BY_PHNOE)
                .addParams(Constants.PHONE, phone)
                .addParams(Constants.VCODE, verifyCode)
                .addParams(Constants.PASSWORD, pwd)
                .addParams(Constants.CONFIRM_PWD, confirmPwd)
                .addParams(Constants.ROLEID, roleId)
                .addParams(Constants.SOURCE, Constants.VCODE_TWO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(RegisterActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(RegisterActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrortBean = new Gson().fromJson(response, NetServiceCodeBean.class);
                            if (!netServiceErrortBean.getCode().equals("200")) {
                                Toast.makeText(RegisterActivity.this, netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                LoginResultBean loginResult = new Gson().fromJson(response, LoginResultBean.class);
                                String message = loginResult.getMessage();
                                String token = loginResult.getData().getToken();
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                                //保存token
                                CacheUtils.putString(RegisterActivity.this, Constants.TOKEN, token);

                                //保存id
                                CacheUtils.putString(RegisterActivity.this, Constants.ID, loginResult.getData().getObject().getId());

                                //保存角色id
                                CacheUtils.putString(RegisterActivity.this, Constants.ROLEID, loginResult.getData().getObject().getRoleId());

                                //注册成功直接回到首页
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    }
                });
    }

    private void netSendVerifyCode() {

        String phone = etInputPhone.getText().toString();
        boolean mobile = MultiVerify.isMobile(phone);
        if (mobile) {
            NetUtils.postWithNoHeader(this, ConstantsUrls.REGIDTER_SEND_VERIFICATION_CODE)
                    .addParams(Constants.PHONE, phone)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(RegisterActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(RegisterActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                                String message = sendVerifyCode.getMessage();
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

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
            Toast.makeText(RegisterActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
        }
    }
}
