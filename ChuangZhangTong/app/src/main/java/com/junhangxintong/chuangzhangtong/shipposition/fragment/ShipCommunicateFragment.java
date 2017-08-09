package com.junhangxintong.chuangzhangtong.shipposition.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;

/**
 * Created by anwanfei on 2017/8/8.
 */

public class ShipCommunicateFragment extends BaseFragment {
    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ship_communicate, null);
        return view;
    }
}
