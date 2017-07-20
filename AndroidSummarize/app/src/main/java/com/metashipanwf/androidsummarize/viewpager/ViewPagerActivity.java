package com.metashipanwf.androidsummarize.viewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.metashipanwf.androidsummarize.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        int[] images = {R.drawable.help1, R.drawable.help2, R.drawable.help2, R.drawable.help2, R.drawable.help2, R.drawable.help2};

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(ViewPagerActivity.this, images);
        viewpager.setAdapter(viewPagerAdapter);
    }
}
