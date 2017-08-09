package com.anwanfei.anfly.foundation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AndroidPhotoAnimationActivity extends BaseActivity {

    @BindView(R.id.gv_photo_animation)
    GridView gvPhotoAnimation;

    String[] arrContents = {"SHAP"};
    Class[] arrClasses = {ShapActivity.class};
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.photo_animation));
    }

    @Override
    protected void initData() {
        gvPhotoAnimation.setAdapter(new ArrayAdapter(this, R.layout.item_photo_animation, R.id.tv_content, arrContents));
        gvPhotoAnimation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(AndroidPhotoAnimationActivity.this, arrClasses[i]));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_android_phone_animation;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
