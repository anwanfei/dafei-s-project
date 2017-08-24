package com.junhangxintong.chuanzhangtong.mine.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BindMailServerActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_how_to_setting)
    TextView tvHowToSetting;
    @BindView(R.id.tv_account_type)
    TextView tvAccountType;
    @BindView(R.id.rl_account_type)
    RelativeLayout rlAccountType;
    @BindView(R.id.et_acceot_mail_server)
    EditText etAcceotMailServer;
    @BindView(R.id.rl_acceot_mail_server)
    LinearLayout rlAcceotMailServer;
    @BindView(R.id.et_send_mail_server)
    EditText etSendMailServer;
    @BindView(R.id.rl_send_mail_server)
    LinearLayout rlSendMailServer;
    @BindView(R.id.et_mail_box_colon)
    EditText etMailBoxColon;
    @BindView(R.id.et_pwd_colon)
    EditText etPwdColon;
    @BindView(R.id.tv_test_account_setting)
    TextView tvTestAccountSetting;
    @BindView(R.id.tv_bind_confirm)
    TextView tvBindConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.bind_box_server));
    }

    @Override
    protected void initData() {
        tvHowToSetting.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_mail_server;
    }

    @OnClick({R.id.iv_back, R.id.tv_how_to_setting, R.id.tv_account_type, R.id.rl_account_type, R.id.rl_acceot_mail_server, R.id.rl_send_mail_server, R.id.tv_test_account_setting, R.id.tv_bind_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_how_to_setting:
                break;
            case R.id.tv_account_type:
                break;
            case R.id.rl_account_type:
                break;
            case R.id.rl_acceot_mail_server:
                break;
            case R.id.rl_send_mail_server:
                break;
            case R.id.tv_test_account_setting:
                break;
            case R.id.tv_bind_confirm:
                break;
        }
    }
}
