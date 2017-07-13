package com.metashipanwf.androidsummarize.badge;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.utils.BadgeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BadgeViewActivity extends AppCompatActivity {

    @BindView(R.id.tablayout_news)
    TabLayout tablayoutNews;
    @BindView(R.id.viewpager_news)
    ViewPager viewpagerNews;
    private String[] mtitles = {"国籍公约", "国内公约", "我们公约"};
    List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_view);
        ButterKnife.bind(this);

        fragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            fragments.add(new ViewpagerFragment());
        }


        viewpagerNews.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return mtitles.length;
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

}
