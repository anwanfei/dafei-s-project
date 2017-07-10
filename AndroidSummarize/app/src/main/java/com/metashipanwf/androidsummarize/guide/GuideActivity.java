package com.metashipanwf.androidsummarize.guide;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.activity.MainActivity;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager viewpager;
    private FrameLayout flGuide;
    private CirclePageIndicator circleindicator;
    private ArrayList<ImageView> imageViews;
    public static final String STARTMAINACTIVITY = "start_mainactivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        flGuide = (FrameLayout) findViewById(R.id.fl_guide);
        circleindicator = (CirclePageIndicator) findViewById(R.id.circleindicator);

        //设置数据
        initData();

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

    private void initData() {
        int[] photos = {R.drawable.help1, R.drawable.help2};

        imageViews = new ArrayList<>();
        for (int i = 0; i < photos.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(photos[i]);
            imageViews.add(imageView);
        }
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

            //保存点击状态
//            CacheUtils.putBoolean(GuideActivity.this, STARTMAINACTIVITY, true);

            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new AlertDialog.Builder(this)
                .setTitle("详细做法")
                .setMessage("1.导入依赖库viewpagerindivcatorlibrary\n" +
                        "2.viewpager四步走\n" +
                        "①找控件viewpager\n" +
                        "②设置数据（把要显示的图片从数组遍历添加到集合中）\n" +
                        "③设置设配器PagerAdapter\n" +
                        "④设置监听")
                .setPositiveButton("学会了",null)
                .show();
        return super.onOptionsItemSelected(item);
    }
}
