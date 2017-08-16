package com.junhangxintong.chuanzhangtong.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.junhangxintong.chuanzhangtong.news.fragment.InternalNewsFragment;
import com.junhangxintong.chuanzhangtong.news.fragment.NationalityFragment;
import com.junhangxintong.chuanzhangtong.news.fragment.NewestNewsFragment;
import com.junhangxintong.chuanzhangtong.news.fragment.OilPriceFragment;

/**
 * Created by edz on 2017/7/8.
 */

public class NewsFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] mtitles = {"最新消息", "油市快递", "国籍公约", "国内公约"};

    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NewestNewsFragment();
        } else if (position == 1) {
            return new OilPriceFragment();
        } else if (position == 2) {
            return new NationalityFragment();
        }
        return new InternalNewsFragment();
    }

    @Override
    public int getCount() {
        return mtitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitles[position];
    }
}
