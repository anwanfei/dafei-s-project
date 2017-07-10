package com.junhangxintong.chuangzhangtong.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AccoutSettingActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_setting_pwd)
    TextView tvSettingPwd;
    @BindView(R.id.tv_setting_phone)
    TextView tvSettingPhone;
    @BindView(R.id.tv_mail_box)
    TextView tvMailBox;
    @BindView(R.id.tv_we_chat)
    TextView tvWeChat;
    @BindView(R.id.tv_QQ)
    TextView tvQQ;
    @BindView(R.id.tv_weibo)
    TextView tvWeibo;
    @BindView(R.id.tv_clear_buffer)
    TextView tvClearBuffer;
    @BindView(R.id.tv_flag_mean)
    TextView tvFlagMean;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.tv_login_out)
    TextView tvLoginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.account_setting));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_accout_setting;
    }

    @OnClick({R.id.iv_back, R.id.tv_setting_pwd, R.id.tv_setting_phone, R.id.tv_mail_box, R.id.tv_we_chat, R.id.tv_QQ, R.id.tv_weibo, R.id.tv_clear_buffer, R.id.tv_flag_mean, R.id.tv_about, R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_setting_pwd:
                break;
            case R.id.tv_setting_phone:
                break;
            case R.id.tv_mail_box:
                break;
            case R.id.tv_we_chat:
                break;
            case R.id.tv_QQ:
                break;
            case R.id.tv_weibo:
                break;
            case R.id.tv_clear_buffer:
                break;
            case R.id.tv_flag_mean:
                break;
            case R.id.tv_about:
                break;
            case R.id.tv_login_out:
                break;
        }
    }
}
