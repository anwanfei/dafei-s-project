package com.atguigu.p2p.fragment;

import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.Image;
import com.atguigu.p2p.bean.Index;
import com.atguigu.p2p.bean.Product;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.common.BaseFragment;
import com.atguigu.p2p.ui.RoundProgress;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.Bind;

/**
 * Created by shkstart on 2016/8/12 0012.
 */
public class HomeFragment extends BaseFragment {


    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.vp_home)
    ViewPager vpHome;
    @Bind(R.id.cp_home)
    CirclePageIndicator cpHome;
    @Bind(R.id.rp_home_progress)
    RoundProgress rpHomeProgress;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    @Bind(R.id.btn_home_join)
    Button btnHomeJoin;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
//        return null;
    }

    private Index index;
    private MyAdapter adapter;

    @Override
    protected void initData(String content) {
        if(TextUtils.isEmpty(content)){
            return;
        }

        index = new Index();
        //解析json数据：gson / fastjson
        JSONObject jsonObject = JSON.parseObject(content);
        //解析并得到Product对象
        String proInfo = jsonObject.getString("proInfo");
        Product product = JSON.parseObject(proInfo, Product.class);
        //解析并得到Image构成的集合
        String imageArr = jsonObject.getString("imageArr");
        List<Image> images = JSON.parseArray(imageArr, Image.class);

        index.product = product;
        index.images = images;

        //将得到的数据加载显示在ViewPager中
        adapter = new MyAdapter();
        //显示数据
        vpHome.setAdapter(adapter);
        //设置Indicator的显示
        cpHome.setViewPager(vpHome);

        //根据得到的Product中的progress来更新自定义的圆形进度条的进度
        currentProgress = Integer.parseInt(index.product.progress);
        new Thread(runnable).start();
    }

    @Override
    protected void initTitle() {
        ivCommonBack.setVisibility(View.GONE);
        ivCommonSetting.setVisibility(View.GONE);
        tvCommonTitle.setText("首页");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return index.images == null ? 0 : index.images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(getActivity());

            //设置为居中显示
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            //获取集合数据中指定位置的图片的url地址，联网将图片下载并设置给ImageView
            Picasso.with(getActivity()).load(index.images.get(position).IMAURL).into(imageView);
            //添加到viewpager中
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);

            container.removeView((View) object);

        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rpHomeProgress.setProgress(0);
            rpHomeProgress.setMax(100);

            for (int i = 0; i < currentProgress; i++) {
                rpHomeProgress.setProgress(rpHomeProgress.getProgress() + 1);

                SystemClock.sleep(30);
                //强制重绘
                rpHomeProgress.postInvalidate();
            }


        }
    };
    private int currentProgress;

}
