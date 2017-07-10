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
import butterknife.OnClick;

public class CrewManagementActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_add_crew)
    TextView tvAddCrew;
    @BindView(R.id.ll_no_crew)
    LinearLayout llNoCrew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.chuanyuanguanli));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_crew_management;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_crew, R.id.ll_no_crew})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_crew:
                startActivity(new Intent(CrewManagementActivity.this, CrewInfoInputActivity.class));
                break;
            case R.id.ll_no_crew:
                break;
        }
    }
}
