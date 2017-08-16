package com.anwanfei.anfly.summary.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;
import com.anwanfei.anfly.summary.fragment.TablayoutFragment;
import com.anwanfei.anfly.utils.BadgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TablayoutActivity extends BaseActivity {

    @BindView(R.id.tablayout_news)
    TabLayout tablayoutNews;
    @BindView(R.id.viewpager_news)
    ViewPager viewpagerNews;

    private String[] mtitles = {"少年头", "国际范", "皮皮虾"};
    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

        fragments = new ArrayList<>();
        for (int i = 0; i < mtitles.length; i++) {
            fragments.add(new TablayoutFragment());
        }
    }

    @Override
    protected void initData() {

        viewpagerNews.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mtitles[position];
            }
        });

        tablayoutNews.setupWithViewPager(viewpagerNews);

        for (int i = 0; i < mtitles.length; i++) {
            setUpTabBadge(i);
        }
    }

    private void setUpTabBadge(int position) {
        TabLayout.Tab tab = tablayoutNews.getTabAt(position);
        if (tab != null) {
            View view = LayoutInflater.from(this).inflate(R.layout.tab_title_layout, null);
            ((TextView) view.findViewById(R.id.tv_title)).setText(mtitles[position]);
            tab.setCustomView(view);
            BadgeView badgeView = new BadgeView(this, (View) view.getParent());
            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
            badgeView.setText(position + "");
            badgeView.show(true);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tablayout;
    }
}
