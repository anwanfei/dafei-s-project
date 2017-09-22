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

    private List<String> mtitles;
    private List<Fragment> fragments;

    public MessageFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> mtitles) {
        super(fm);
        this.fragments = fragments;
        this.mtitles = mtitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mtitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitles.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}

