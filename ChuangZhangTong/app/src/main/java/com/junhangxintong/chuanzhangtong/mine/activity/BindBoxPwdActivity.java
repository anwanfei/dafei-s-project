package com.junhangxintong.chuanzhangtong.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class BindBoxPwdActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_mail_box)
    EditText etMailBox;
    @BindView(R.id.tv_bind_confirm)
    TextView tvBindConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.bind_account_pwd));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bind_box_pwd;
    }

    @OnClick({R.id.iv_back, R.id.tv_bind_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_bind_confirm:
                break;
        }
    }
}
