package com.junhangxintong.chuangzhangtong.message.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.junhangxintong.chuangzhangtong.message.fragment.NewestMessageFragment;
import com.junhangxintong.chuangzhangtong.news.fragment.InternalNewsFragment;
import com.junhangxintong.chuangzhangtong.news.fragment.NationalityFragment;
import com.junhangxintong.chuangzhangtong.news.fragment.NewestNewsFragment;

/**
 * Created by edz on 2017/7/8.
 */

public class MessageFragmentAdapter extends FragmentStatePagerAdapter {

    private String[] mtitles = {"所有消息", "船舶动态", "船员证书", "船舶证书"};

    public MessageFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new NewestNewsFragment();
        } else if (position == 2) {
            return new InternalNewsFragment();
        } else if (position == 3) {
            return new NationalityFragment();
        }
        return new NewestMessageFragment();
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

