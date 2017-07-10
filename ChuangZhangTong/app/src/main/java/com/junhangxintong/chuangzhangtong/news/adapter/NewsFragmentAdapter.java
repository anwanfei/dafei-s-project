package com.junhangxintong.chuangzhangtong.news.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.junhangxintong.chuangzhangtong.news.fragment.InternalNewsFragment;
import com.junhangxintong.chuangzhangtong.news.fragment.NationalityFragment;
import com.junhangxintong.chuangzhangtong.news.fragment.NewestNewsFragment;

/**
 * Created by edz on 2017/7/8.
 */

public class NewsFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] mtitles = {"最新消息", "国籍公约", "国内公约"};

    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new NewestNewsFragment();
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

