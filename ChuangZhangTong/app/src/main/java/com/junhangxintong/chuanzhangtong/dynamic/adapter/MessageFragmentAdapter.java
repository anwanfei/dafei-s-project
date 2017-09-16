package com.junhangxintong.chuanzhangtong.dynamic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by edz on 2017/7/8.
 */

public class MessageFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] mtitles = {"所有消息", "船舶动态", "报文记录", "船员证书", "船舶证书"};

    private List<Fragment> fragments;

    public MessageFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}

