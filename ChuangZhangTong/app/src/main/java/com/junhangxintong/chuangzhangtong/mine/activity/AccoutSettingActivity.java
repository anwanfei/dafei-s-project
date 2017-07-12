package com.junhangxintong.chuangzhangtong.mine.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AccoutSettingActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.rl_setting_pwd)
    RelativeLayout rlSettingPwd;
    @BindView(R.id.rl_setting_phone)
    RelativeLayout rlSettingPhone;
    @BindView(R.id.rl_mail_box)
    RelativeLayout rlMailBox;
    @BindView(R.id.rl_wechat)
    RelativeLayout rlWechat;
    @BindView(R.id.rl_qq)
    RelativeLayout rlQq;
    @BindView(R.id.rl_weibo)
    RelativeLayout rlWeibo;
    @BindView(R.id.rl_clear_buffer)
    RelativeLayout rlClearBuffer;
    @BindView(R.id.rl_flag_mean)
    RelativeLayout rlFlagMean;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.rl_login_out)
    RelativeLayout rlLoginOut;
    private AlertDialog show_clear_buffer_dialog;

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

    @OnClick({R.id.iv_back, R.id.rl_setting_pwd, R.id.rl_setting_phone, R.id.rl_mail_box, R.id.rl_wechat, R.id.rl_qq, R.id.rl_weibo, R.id.rl_clear_buffer, R.id.rl_flag_mean, R.id.rl_about, R.id.rl_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_setting_pwd:
                startActivity(new Intent(AccoutSettingActivity.this, ResetPwdActivity.class));
                break;
            case R.id.rl_setting_phone:
                break;
            case R.id.rl_mail_box:
                break;
            case R.id.rl_wechat:
                break;
            case R.id.rl_qq:
                break;
            case R.id.rl_weibo:
                break;
            case R.id.rl_clear_buffer:
                showDialogClearBuffer();
                break;
            case R.id.rl_flag_mean:
                startActivity(new Intent(AccoutSettingActivity.this, FlagMeanActivity.class));
                break;
            case R.id.rl_about:
                startActivity(new Intent(AccoutSettingActivity.this, AboutActivity.class));
                break;
            case R.id.rl_login_out:
                break;
        }
    }

    private void showDialogClearBuffer() {
        View inflate = getLayoutInflater().inflate(R.layout.dialog_clear_butter, null);
        TextView tv_cancel_clear_buffer = (TextView) inflate.findViewById(R.id.tv_cancel_clear_buffer);
        TextView tv_ok_clear_butter = (TextView) inflate.findViewById(R.id.tv_ok_clear_butter);

        tv_cancel_clear_buffer.setOnClickListener(this);
        tv_ok_clear_butter.setOnClickListener(this);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setView(inflate);

        show_clear_buffer_dialog = dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_clear_buffer:
                show_clear_buffer_dialog.dismiss();
                break;
            case R.id.tv_ok_clear_butter:
                Toast.makeText(AccoutSettingActivity.this, "已清除", Toast.LENGTH_SHORT).show();
                show_clear_buffer_dialog.dismiss();
                break;
        }
    }
}
