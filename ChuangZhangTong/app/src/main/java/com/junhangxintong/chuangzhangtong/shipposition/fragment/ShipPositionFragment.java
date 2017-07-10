package com.junhangxintong.chuangzhangtong.shipposition.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;

/**
 * Created by edz on 2017/7/5.
 */

public class ShipPositionFragment extends BaseFragment {
    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_ship_position, null);
        return view;
    }
}
