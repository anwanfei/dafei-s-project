package com.metashipanwf.androidsummarize.tablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author:AnWanfei
 * time:2017/2/28.
 * Email:anwanfei_sp@163.com
 * function:
 */

public class TablayoutAdapter extends FragmentPagerAdapter {

    private List<TablayoutFragment> tablayoutFragments;

    public TablayoutAdapter(FragmentManager fm) {
        super(fm);
    }

    public TablayoutAdapter(FragmentManager fm, List<TablayoutFragment> tablayoutFragments) {
        super(fm);
        this.tablayoutFragments = tablayoutFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return tablayoutFragments.get(position);
    }

    @Override
    public int getCount() {
        return tablayoutFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablayoutFragments.get(position).getTitle();
    }
}
