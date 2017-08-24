package com.junhangxintong.chuanzhangtong.news.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class NationalityConventionActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_convention_name)
    TextView tvConventionName;
    @BindView(R.id.tv_convention_name_field)
    TextView tvConventionNameField;
    @BindView(R.id.tv_convention_wenhao)
    TextView tvConventionWenhao;
    @BindView(R.id.tv_convention_wenhao_issue)
    TextView tvConventionWenhaoIssue;
    @BindView(R.id.tv_convention_suoyinhao_field)
    TextView tvConventionSuoyinhaoField;
    @BindView(R.id.tv_convention_suoyinhao)
    TextView tvConventionSuoyinhao;
    @BindView(R.id.tv_convention_suoyinhao_time)
    TextView tvConventionSuoyinhaoTime;
    @BindView(R.id.tv_convention_content)
    TextView tvConventionContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ocean_convention));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_nationality_convention;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
