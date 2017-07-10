package com.metashipanwf.androidsummarize.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.my.LoginActivity;

/**
 * author:AnWanfei
 * time:2016/11/4.
 * Email:anwanfei_sp@163.com
 * function：主要写账号登录（手机注册、微信、QQ、微博）、分享、支付
 */
public class MyFragment extends BaseFragment {

    private FrameLayout fl_login;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_myfragment_layout, null);
        fl_login = (FrameLayout) view.findViewById(R.id.fl_login);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        fl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }
}
