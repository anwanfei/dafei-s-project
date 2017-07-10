package com.atguigu.p2p.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.p2p.R;
import com.atguigu.p2p.common.BaseFragment;
import com.atguigu.p2p.util.UIUtils;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by shkstart on 2016/8/12 0012.
 */
public class InvestFragment extends BaseFragment {


    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.tab_invest_indicator)
    TabPageIndicator tabInvestIndicator;
    @Bind(R.id.vp_invest)
    ViewPager vpInvest;

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return null;
    }

    private MyAdapter adapter;
    @Override
    protected void initData(String content) {
        initFragment();
        adapter = new MyAdapter(getFragmentManager());
        //加载数据显示
        vpInvest.setAdapter(adapter);
        //设置tabInvestIndicator与ViewPager的关联
        tabInvestIndicator.setViewPager(vpInvest);
    }

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    //初始化集合数据
    private void initFragment() {
        ProductListFragment productListFragment = new ProductListFragment();
        ProductRecommondFragment productRecommondFragment = new ProductRecommondFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();

        fragmentList.add(productListFragment);
        fragmentList.add(productRecommondFragment);
        fragmentList.add(productHotFragment);
    }

    public void initTitle() {
        ivCommonBack.setVisibility(View.GONE);
        ivCommonSetting.setVisibility(View.GONE);

        tvCommonTitle.setText("投资");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invest;
    }


    /**
     * 提供PageAdapter的内部实现类
     *
     * FragmentStatePagerAdapter:适时的销毁数据
     * FragmentPagerAdapter:不会销毁数据
     */

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return UIUtils.getStringArray(R.array.invest_array)[position];
        }
    }
}
