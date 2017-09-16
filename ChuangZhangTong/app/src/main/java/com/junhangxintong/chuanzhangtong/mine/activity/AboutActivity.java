package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_about_version)
    TextView tvAboutVersion;
    @BindView(R.id.tv_check_updata)
    TextView tvCheckUpdata;
    @BindView(R.id.tv_feed_back)
    TextView tvFeedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.about));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @OnClick({R.id.iv_back, R.id.tv_score, R.id.tv_about_version, R.id.tv_check_updata, R.id.tv_feed_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_score:
                Toast.makeText(AboutActivity.this, getResources().getString(R.string.developing), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_about_version:
                break;
            case R.id.tv_check_updata:
                Toast.makeText(AboutActivity.this, getResources().getString(R.string.newest_version), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_feed_back:
                startActivity(new Intent(AboutActivity.this, FeedbackActivity.class));
                break;
        }
    }
}
