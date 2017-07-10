package com.atguigu.p2p.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.p2p.ui.LoadingPage;
import com.atguigu.p2p.util.UIUtils;
import com.loopj.android.http.RequestParams;

import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/8/15 0015.
 * 提供通用的Fragment的功能
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = UIUtils.getXmlView(getLayoutId());
//        ButterKnife.bind(this, view);
//
//        initTitle();
//        initData();

//        return view;
                                                              //fragment中：getActivity()
        loadingPage = new LoadingPage(container.getContext()){//application : activity.getApplication() /context.getApplicationContext()

            @Override
            public int layoutId() {
                return getLayoutId();
            }

            @Override
            protected void onSuccess(ResultState resultState, View successView) {
                ButterKnife.bind(BaseFragment.this, successView);
                initTitle();
                initData(resultState.getContent());
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            protected String url() {
                return getUrl();
            }
        };
        return loadingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showLoadingPage();
            }
        },2000);

    }

    protected abstract RequestParams getParams();

    protected abstract String getUrl();

    protected abstract void initData(String content);

    protected abstract void initTitle();

    public abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void showLoadingPage(){
        loadingPage.show();
    }

}
