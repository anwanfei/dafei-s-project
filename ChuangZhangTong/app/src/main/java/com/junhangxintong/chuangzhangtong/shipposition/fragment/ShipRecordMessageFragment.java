package com.junhangxintong.chuangzhangtong.shipposition.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;
import com.junhangxintong.chuangzhangtong.shipposition.activity.AllMessagesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/8.
 */

public class ShipRecordMessageFragment extends BaseFragment {
    @BindView(R.id.baidu_map)
    MapView baiduMap;
    @BindView(R.id.tv_show_all_mesages)
    TextView tvShowAllMesages;
    @BindView(R.id.lv_message)
    ListView lvMessage;
    Unbinder unbinder;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ship_recored_message, null);
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

    @OnClick(R.id.tv_show_all_mesages)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), AllMessagesActivity.class));
    }
}
