package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class ResetPwdActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_pwd_first)
    EditText etInputPwdFirst;
    @BindView(R.id.et_input_pwd_again)
    EditText etInputPwdAgain;
    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.reset_pwd));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reset_pwd;
    }

    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                netSetPwd();
                break;
        }
    }

    private void netSetPwd() {
        String pwd = etInputPwdFirst.getText().toString();
        String confirmPwd = etInputPwdAgain.getText().toString();
        String userId = CacheUtils.getString(this, Constants.ID);
        if (pwd.equals("")) {
            Toast.makeText(ResetPwdActivity.this, getResources().getString(R.string.input_pwd), Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirmPwd.equals("")) {
            Toast.makeText(ResetPwdActivity.this, getResources().getString(R.string.input_pwd_again), Toast.LENGTH_SHORT).show();
            return;
        }

        if (pwd.equals(confirmPwd)) {
            NetUtils.postWithHeader(this, ConstantsUrls.RESET_PWD)
                    .addParams(Constants.USER_ID, userId)
                    .addParams(Constants.PASSWORD, pwd)
                    .addParams(Constants.CONFIRM_PWD, confirmPwd)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ResetPwdActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(ResetPwdActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                                String message = sendVerifyCode.getMessage();
                                String code = sendVerifyCode.getCode();
                                Toast.makeText(ResetPwdActivity.this, message, Toast.LENGTH_SHORT).show();
                                if (code.equals("601")) {
                                    startActivity(new Intent(ResetPwdActivity.this, LoginRegisterActivity.class));
                                    finish();
                                }
                                if (code.equals("200")) {
                                    finish();
                                }
                            }
                        }
                    });
        } else {
            Toast.makeText(ResetPwdActivity.this, getResources().getString(R.string.two_pwd_not_equal), Toast.LENGTH_SHORT).show();
        }
    }
}
