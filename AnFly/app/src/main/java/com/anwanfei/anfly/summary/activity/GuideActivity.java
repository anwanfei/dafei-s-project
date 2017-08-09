package com.anwanfei.anfly.summary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.anwanfei.anfly.MainActivity;
import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;
import com.anwanfei.anfly.summary.adapter.GuideAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 1.导入依赖库viewpagerindivcatorlibrary
 * 2.viewpager四步走
 *  ①找控件viewpager
 *  ②设置数据（把要显示的图片从数组遍历添加到集合中）
 *   ③设置设配器PagerAdapter
 *  ④设置监听
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.circleindicator)
    com.viewpagerindicator.CirclePageIndicator circleindicator;
    @BindView(R.id.btn_start)
    TextView btnStart;
    @BindView(R.id.fl_guide)
    FrameLayout flGuide;
    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        int[] photos = {R.drawable.bg, R.drawable.bg, R.drawable.bg};

        imageViews = new ArrayList<>();
        for (int i = 0; i < photos.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(photos[i]);
            imageViews.add(imageView);
        }

        //设置适配器
        GuideAdapter guideAdapter = new GuideAdapter(GuideActivity.this, imageViews);
        viewpager.setAdapter(guideAdapter);


        //设置监听
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        //设置小红点
        circleindicator.setViewPager(viewpager);

        //点击开始按钮跳转
        flGuide.setOnClickListener(new MyOnClickListener());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {
                //最后一个页面，显示按钮
                flGuide.setVisibility(View.VISIBLE);
            } else {
                //不是最后一个页面，隐藏按钮
                flGuide.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
