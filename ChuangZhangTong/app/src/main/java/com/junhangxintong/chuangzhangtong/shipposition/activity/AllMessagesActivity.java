package com.junhangxintong.chuangzhangtong.shipposition.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.news.fragment.InternalNewsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AllMessagesActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tablayout_all_messages)
    TabLayout tablayoutAllMessages;
    @BindView(R.id.viewpager_all_meesage)
    ViewPager viewpagerAllMeesage;
    private String[] mtitles = {"正午报", "抵港报", "靠泊报", "离港报"};
    List<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.all_history_messages));
    }

    @Override
    protected void initData() {

        fragments = new ArrayList<>();
        for (int i = 0; i < mtitles.length; i++) {
            fragments.add(new InternalNewsFragment());
        }
        viewpagerAllMeesage.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return mtitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mtitles[position];
            }
        });
        tablayoutAllMessages.setupWithViewPager(viewpagerAllMeesage);
        tablayoutAllMessages.getTabAt(0).setIcon(R.drawable.iv_zhengwu_message);
        tablayoutAllMessages.getTabAt(1).setIcon(R.drawable.iv_arrival_message);
        tablayoutAllMessages.getTabAt(2).setIcon(R.drawable.iv_kaobo_message);
        tablayoutAllMessages.getTabAt(3).setIcon(R.drawable.iv_leave_meaage);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_all_messages;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
