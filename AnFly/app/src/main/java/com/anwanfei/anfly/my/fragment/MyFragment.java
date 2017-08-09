package com.anwanfei.anfly.my.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseFragment;

/**
 * Created by anwanfei on 2017/7/28.
 */

public class MyFragment extends BaseFragment {
    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my, null);
        return view;
    }
}
