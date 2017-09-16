package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.MainActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.bean.LoginResultBean;
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
    @BindView(R.id.rb_test_url)
    RadioButton rbTestUrl;
    @BindView(R.id.rb_local_url)
    RadioButton rbLocalUrl;
    @BindView(R.id.rb_base_url)
    RadioButton rbBaseUrl;
    @BindView(R.id.rg_choose_url)
    RadioGroup rgChooseUrl;

    private String local_base_url = "http://192.168.0.102:8082";
    private String www_test_base_url = "http://116.62.152.191:8082";
    private String www_base_url = "http://192.168.0.102:8082";

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
                tvLogin.setTextColor(getResources().getColor(R.color.white));
                tvLogin.setBackgroundResource(R.drawable.tv_bg);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (etInputPhone.getText().toString().isEmpty()) {
                    tvLogin.setTextColor(getResources().getColor(R.color.black));
                    tvLogin.setBackgroundResource(R.drawable.bg_identity_gray);
                }
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //网络选择按钮开关
        rgChooseUrl.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == rbTestUrl.getId()) {
                    CacheUtils.putString(LoginRegisterActivity.this, Constants.BASE_URL, www_test_base_url);
                } else if (i == rbBaseUrl.getId()) {
                    CacheUtils.putString(LoginRegisterActivity.this, Constants.BASE_URL, www_base_url);
                } else if (i == rbLocalUrl.getId()) {
                    CacheUtils.putString(LoginRegisterActivity.this, Constants.BASE_URL, local_base_url);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_register;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(LoginRegisterActivity.this, MainActivity.class));
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
                finish();
                break;
            case R.id.tv_forget_pwd:
                startActivity(new Intent(LoginRegisterActivity.this, ForgetPwdActivity.class));
                break;
            case R.id.tv_login:
                netLoginByPwd();
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

    private void netLoginByPwd() {
        String phone = etInputPhone.getText().toString();
        String pwd = etInputPwd.getText().toString();

        boolean mobile = MultiVerify.isMobile(phone);

        if (!mobile) {
            Toast.makeText(LoginRegisterActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        if (StringUtils.isEmpty(pwd)) {
            Toast.makeText(LoginRegisterActivity.this, getResources().getString(R.string.input_pwd), Toast.LENGTH_SHORT).show();
            return;
        }
        NetUtils.postWithNoHeader(this, ConstantsUrls.LOGIN_BY_PHNOE)
                .addParams(Constants.PHONE, phone)
                .addParams(Constants.PASSWORD, pwd)
                .addParams(Constants.SOURCE, Constants.VCODE_TWO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(LoginRegisterActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(LoginRegisterActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrortBean = new Gson().fromJson(response, NetServiceErrortBean.class);
                            if (!netServiceErrortBean.getCode().equals("200")) {
                                Toast.makeText(LoginRegisterActivity.this, netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                LoginResultBean loginResult = new Gson().fromJson(response, LoginResultBean.class);
                                String message = loginResult.getMessage();
                                Toast.makeText(LoginRegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                                //保存token
                                CacheUtils.putString(LoginRegisterActivity.this, Constants.TOKEN, loginResult.getData().getToken());

                                //保存id
                                CacheUtils.putString(LoginRegisterActivity.this, Constants.ID, loginResult.getData().getObject().getId());

                                //保存角色id
                                CacheUtils.putString(LoginRegisterActivity.this, Constants.ROLEID, loginResult.getData().getObject().getRoleId());

                                //登录成功回到首页
                                startActivity(new Intent(LoginRegisterActivity.this, MainActivity.class));
                                finish();
                            }
                        }

                    }
                });
    }
}
