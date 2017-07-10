package com.atguigu.p2p.common;

import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/8/16 0016.
 */
public abstract class BaseHolder<T> {

    private View rootView;
    private T t;

    public BaseHolder(){
        rootView = initView();
        rootView.setTag(this);
        ButterKnife.bind(BaseHolder.this,rootView);
    }

    public abstract View initView();

    public View getRootView(){
        return rootView;
    }

    public abstract void setData(T t);

    public T getData(){
        return t;
    }

}
