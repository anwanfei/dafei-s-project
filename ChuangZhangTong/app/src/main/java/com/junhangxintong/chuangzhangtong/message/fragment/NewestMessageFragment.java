package com.junhangxintong.chuangzhangtong.message.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;

/**
 * Created by edz on 2017/7/20.
 */

public class NewestMessageFragment extends BaseFragment {
    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_newest_news, null);
        return view;
    }
}