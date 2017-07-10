package com.metashipanwf.androidsummarize.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author:AnWanfei
 * time:2016/11/4.
 * Email:anwanfei_sp@163.com
 * 每一个activity中都用菜单显示使用方法或者步骤
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 上下文
     */
    protected Context mContext;

    /**
     * 该Fragment是否被初始化过
     */
    private boolean isInit = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 由子类实现该方法，创建自己的视图
     */
    protected abstract View initView();


    /**
     * 子类，需要初始化数据、联网请求数据并且绑定数据等重写该方法
     */
    protected void initData() {}


}
