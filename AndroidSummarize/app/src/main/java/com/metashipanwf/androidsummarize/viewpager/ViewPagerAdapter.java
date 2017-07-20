package com.metashipanwf.androidsummarize.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.metashipanwf.androidsummarize.R;

/**
 * Created by edz on 2017/7/19.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] images;

    public ViewPagerAdapter(Context mContext, int[] images) {
        this.mContext = mContext;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_viewpager, container, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.imageView);

        imageView.setImageResource(images[position]);
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
