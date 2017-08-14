package com.junhangxintong.chuanzhangtong.dynamic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.news.adapter.ShipNewsSubFragmentAdapter;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/20.
 */

public class ShipDynamicFragment extends BaseFragment {

    List<String> allMessages = new ArrayList<>();
    @BindView(R.id.lv_dynamic_ship)
    ListView lvDynamicShip;
    Unbinder unbinder;
    private ShipNewsSubFragmentAdapter shipNewsSubFragmentAdapter;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_ship_daynamic, null);
        return view;
    }


    @Override
    protected void initData() {
        super.initData();
        for (int i = 0; i < Constants.TEST_DATA_NUM; i++) {
            if (allMessages.size() < Constants.TEST_DATA_NUM) {
                allMessages.add("船舶动态通知：华海一号起航");
            }
        }

        shipNewsSubFragmentAdapter = new ShipNewsSubFragmentAdapter(getActivity(), allMessages);
        lvDynamicShip.setAdapter(shipNewsSubFragmentAdapter);
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
}
