package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFleetActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_my_fleet)
    LinearLayout llMyFleet;
    @BindView(R.id.ll_follow_fleet)
    LinearLayout llFollowFleet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.add_ship));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_fleet;
    }

    @OnClick({R.id.iv_back, R.id.ll_my_fleet, R.id.ll_follow_fleet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_my_fleet:
                Intent intent = new Intent(MyFleetActivity.this, MyFleetListActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_follow_fleet:
                break;
        }
    }
}
