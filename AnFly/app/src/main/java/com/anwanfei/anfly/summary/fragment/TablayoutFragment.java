package com.anwanfei.anfly.summary.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseFragment;

/**
 * Created by anwanfei on 2017/8/1.
 */

public class TablayoutFragment extends BaseFragment {
    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tablayout, null);
        return view;
    }
}
