package com.junhangxintong.chuanzhangtong.dynamic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.MessageFragmentAdapter;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/5.
 */

public class DynamicRemindFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tablayout_message)
    TabLayout tablayoutMessage;
    @BindView(R.id.viewpager_message)
    ViewPager viewpagerMessage;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_message, null);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 2017/8/23 根据是否登录判断是否要进入登录界面
        String token = CacheUtils.getString(getActivity(), Constants.TOKEN);

        if (token.equals("")) {
            startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
            getActivity().finish();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText(getResources().getString(R.string.message));
        viewpagerMessage.setAdapter(new MessageFragmentAdapter(getFragmentManager()));
        tablayoutMessage.setupWithViewPager(viewpagerMessage);

        tablayoutMessage.getTabAt(0).setIcon(R.drawable.iv_all_messags);
        tablayoutMessage.getTabAt(1).setIcon(R.drawable.iv_ship_dynamic);
        tablayoutMessage.getTabAt(2).setIcon(R.drawable.ic_message_record);
        tablayoutMessage.getTabAt(3).setIcon(R.drawable.iv_crew_certificate);
        tablayoutMessage.getTabAt(4).setIcon(R.drawable.iv_ship_certificate);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
