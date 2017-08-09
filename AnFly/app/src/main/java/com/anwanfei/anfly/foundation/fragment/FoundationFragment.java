package com.anwanfei.anfly.foundation.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseFragment;
import com.anwanfei.anfly.foundation.adapter.FoundationAdapter;
import com.anwanfei.anfly.foundation.bean.FoundationBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/28.
 */

public class FoundationFragment extends BaseFragment {
    @BindView(R.id.rv_foundation)
    RecyclerView rvFoundation;
    Unbinder unbinder;
    private FoundationAdapter foundationAdapter;

    String[] arrNmaes = {"安卓入门", "安卓组件", "用户界面", "数据存储", "网络数据", "图像动画", "消息事件任务", "新特新", "安卓面试"};

    int[] arrImages = {R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar, R.drawable.bg_bar};
    private List<FoundationBean> mLists = new ArrayList<>();

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_foundation, null);
        return view;
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

        for (int i = 0; i < arrNmaes.length; i++) {
            FoundationBean foundationBean = new FoundationBean();
            foundationBean.setImageview(arrImages[i]);
            foundationBean.setName(arrNmaes[i]);

            mLists.add(foundationBean);
        }

        rvFoundation.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        foundationAdapter = new FoundationAdapter(getActivity(), mLists);
        rvFoundation.setAdapter(foundationAdapter);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
