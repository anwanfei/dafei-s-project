package com.junhangxintong.chuanzhangtong.news.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.news.adapter.NewsFragmentAdapter;
import com.junhangxintong.chuanzhangtong.utils.BadgeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/5.
 */

public class NewsFragment extends BaseFragment {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tablayout_news)
    TabLayout tablayoutNews;
    @BindView(R.id.viewpager_news)
    ViewPager viewpagerNews;
    Unbinder unbinder;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_news, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText(getResources().getString(R.string.news));
        viewpagerNews.setAdapter(new NewsFragmentAdapter(getFragmentManager()));
        tablayoutNews.setupWithViewPager(viewpagerNews);
        tablayoutNews.getTabAt(0).setIcon(R.drawable.iv_all_messags);
        tablayoutNews.getTabAt(1).setIcon(R.drawable.iv_oil_price);
        tablayoutNews.getTabAt(2).setIcon(R.drawable.iv_nationality_convention);
        tablayoutNews.getTabAt(3).setIcon(R.drawable.iv_international_convention);

        final BadgeView badgeView = new BadgeView(getActivity(), tablayoutNews);

        badgeView.setText("9");
        badgeView.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
