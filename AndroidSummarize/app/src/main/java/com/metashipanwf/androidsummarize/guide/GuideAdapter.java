package com.metashipanwf.androidsummarize.guide;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * author:AnWanfei
 * time:2017/8/1.
 * Email:anwanfei_sp@163.com
 * function:
 */
public class GuideAdapter extends PagerAdapter {

    private GuideActivity guideActivity;
    private ArrayList<ImageView> imageViews;

    public GuideAdapter(GuideActivity guideActivity, ArrayList<ImageView> imageViews) {
        this.guideActivity= guideActivity;
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageview = imageViews.get(position);
        container.addView(imageview);
        return imageview;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
