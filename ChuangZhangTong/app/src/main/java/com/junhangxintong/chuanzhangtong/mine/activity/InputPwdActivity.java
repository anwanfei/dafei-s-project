package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class InputPwdActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_password)
    EditText etInputPassword;
    @BindView(R.id.text_input_password)
    TextInputLayout textInputPassword;
    @BindView(R.id.tv_reset_pws_complete)
    TextView tvResetPwsComplete;
    private String phone;
    private String vCode;

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
        Intent intent = getIntent();
        phone = intent.getStringExtra(Constants.PHONE);
        vCode = intent.getStringExtra(Constants.VCODE);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_input_pwd;
    }

    @OnClick({R.id.iv_back, R.id.tv_reset_pws_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_reset_pws_complete:
                netForgetPwdCommit();
                break;
        }
    }

    private void netForgetPwdCommit() {
        String newPwd = etInputPassword.getText().toString();
        if (newPwd.isEmpty()) {
            Toast.makeText(InputPwdActivity.this, getResources().getString(R.string.input_pwd), Toast.LENGTH_SHORT).show();
        } else {
            NetUtils.postWithNoHeader(this, ConstantsUrls.SUMBITNEWPASSWORD)
                    .addParams(Constants.PHONE, phone)
                    .addParams(Constants.VCODE, vCode)
                    .addParams(Constants.PASSWORD, newPwd)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(InputPwdActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(InputPwdActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceCodeBean netServiceErrortBean = new Gson().fromJson(response, NetServiceCodeBean.class);
                                Toast.makeText(InputPwdActivity.this, netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    });
        }

    }
}
