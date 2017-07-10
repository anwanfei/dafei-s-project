package com.metashipanwf.androidsummarize.tablayout;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TablayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private List<TablayoutFragment> tablayoutFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();

    }

    private void initListener() {
        tvBack.setOnClickListener(this);


    }

    private void initView() {
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
    }

    private void initData() {
        tvTitle.setText("Tablayouty的使用");

        //初始化数据
        tablayoutFragments = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
          tablayoutFragments.add(new TablayoutFragment("标题"+i,"内容"+i));
        }

        //设置适配器
        TablayoutAdapter adapter = new TablayoutAdapter(getSupportFragmentManager(),tablayoutFragments);
        viewPager.setAdapter(adapter);

        //tablayout关联viewpager
        tabLayout.setupWithViewPager(viewPager);

        //设置tablayout的模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

    }


    @Override
    public void onClick(View view) {
        finish();
    }
}
