package com.anwanfei.anfly.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anwanfei on 2017/7/27.
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

    /**
     * 由子类实现该方法，创建自己的视图
     *
     * @return
     */
    protected abstract View initView();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    /**
     * 子类，需要初始化数据，联网请求数据并且绑定数据，等重写该方法
     */
    protected void initData() {

    }

    /*
   由子类实现该方法，实现监听
    */
    protected void initListener() {

    }
}
