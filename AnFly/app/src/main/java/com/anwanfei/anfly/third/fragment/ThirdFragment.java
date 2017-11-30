package com.anwanfei.anfly.third.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseFragment;
import com.anwanfei.anfly.third.activity.eventbus.EventBusSubscribeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/28.
 */

public class ThirdFragment extends BaseFragment {
    @BindView(R.id.tv_eventbus)
    TextView tvEventbus;
    Unbinder unbinder;

    @Override
    protected View initView() {
//        View view = View.inflate(getActivity(), R.layout.fragment_third, null);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_third, null);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.tv_eventbus)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), EventBusSubscribeActivity.class));
    }
}
