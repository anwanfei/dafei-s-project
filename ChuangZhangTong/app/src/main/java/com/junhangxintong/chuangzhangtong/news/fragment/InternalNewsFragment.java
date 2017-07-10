package com.junhangxintong.chuangzhangtong.news.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;

/**
 * Created by edz on 2017/7/8.
 */

public class InternalNewsFragment extends BaseFragment {
    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_message, null);
        return view;
    }
}
