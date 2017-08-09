package com.anwanfei.anfly.summary.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseFragment;
import com.anwanfei.anfly.summary.activity.BasicTestActivity;
import com.anwanfei.anfly.summary.activity.CountDownTimerActivity;
import com.anwanfei.anfly.summary.activity.GuideActivity;
import com.anwanfei.anfly.summary.activity.TablayoutActivity;
import com.anwanfei.anfly.summary.activity.VibratorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/28.
 */

public class SummaryFragment extends BaseFragment {
    @BindView(R.id.lv_summary_list)
    ListView lvSummaryList;
    Unbinder unbinder;
    List<String> summaryLists;

    private String[] arrSummaryList = {"引导界面", "tablayout显示消息提醒", "常用基本操作", "震动测试","倒计时实现"/*, "侧滑菜单", "Activity切换过场动画", "启用相机拍照并保存图片",
            "打包签名", "代码混淆", "反编译", "渠道打包", "二维码", "机械学习"*/};

    Class[] arrClasses = {GuideActivity.class, TablayoutActivity.class, BasicTestActivity.class, VibratorActivity.class, CountDownTimerActivity.class};

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_summary, null);
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

        summaryLists = new ArrayList<>();

        for (int i = 0; i < arrSummaryList.length; i++) {
            summaryLists.add(arrSummaryList[i]);
        }

        lvSummaryList.setAdapter(new ArrayAdapter(getActivity(), R.layout.item_summary_fragment, R.id.tv_content, summaryLists));

        lvSummaryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), arrClasses[i]));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
