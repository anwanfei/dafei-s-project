package com.junhangxintong.chuanzhangtong.dynamic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.junhangxintong.chuanzhangtong.dynamic.fragment.AllMessageFragment;
import com.junhangxintong.chuanzhangtong.dynamic.fragment.CrewCertificateFragment;
import com.junhangxintong.chuanzhangtong.dynamic.fragment.ShipCertificateFragment;
import com.junhangxintong.chuanzhangtong.dynamic.fragment.ShipDynamicFragment;

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
        if (position == 0) {
            return new AllMessageFragment();
        } else if (position == 1) {
            return new ShipDynamicFragment();
        } else if (position == 2) {
            return new CrewCertificateFragment();
        }
        return new ShipCertificateFragment();
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

