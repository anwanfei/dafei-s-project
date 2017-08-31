package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.Utils.EventBusMessage;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ScanPhotoActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.viewpager_scan_photo)
    ViewPager viewpagerScanPhoto;
    private ArrayList<String> photos;
    private photoPagerAdatper adapter;
    private int curPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        photos = intent.getStringArrayListExtra(Constants.PHONE_PATHS);
        curPosition = intent.getIntExtra(Constants.POSITION, 1);

        adapter = new photoPagerAdatper();
        viewpagerScanPhoto.setAdapter(adapter);

        viewpagerScanPhoto.setCurrentItem(curPosition, false);
        tvProgress.setText((curPosition + 1) + "/" + photos.size());
        viewpagerScanPhoto.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                curPosition = position;
                tvProgress.setText(curPosition + 1 + "/" + photos.size());

            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photos.remove(curPosition);
                adapter.notifyDataSetChanged();
                if (curPosition > 0 || photos.size() > 0) {
                    tvProgress.setText(curPosition + 1 + "/" + photos.size());
                } else {
                    EventBus.getDefault().post(new EventBusMessage(photos));
                    finish();
                }

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_photo;
    }

    @OnClick({R.id.iv_back, R.id.iv_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                EventBus.getDefault().post(new EventBusMessage(photos));
                finish();
                break;
            case R.id.iv_delete:
                break;
        }
    }

    private class photoPagerAdatper extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View inflate = LayoutInflater.from(ScanPhotoActivity.this).inflate(R.layout.photo_scan_layout, container, false);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_scan);

            showPhotoByGlide(imageView, position);
            container.addView(inflate);
            return inflate;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return photos.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    private void showPhotoByGlide(ImageView imageView, int position) {
        Glide.with(ScanPhotoActivity.this)
                .load(photos.get(position))
                .centerCrop()
                .into(imageView);
    }
}
