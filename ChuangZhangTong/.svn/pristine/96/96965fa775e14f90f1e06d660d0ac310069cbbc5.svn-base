package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.shipposition.activity.AllMessagesActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipNoonMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipMessagesAdapter;

import java.util.ArrayList;
import java.util.List;

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

    List<String> messages = new ArrayList<>();
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ship_recored_message, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        for (int i = 0; i < 5; i++) {
            messages.add("华海一号正午报");
        }

        ShipMessagesAdapter shipMessagesAdapter = new ShipMessagesAdapter(getActivity(), messages);
        lvMessage.setAdapter(shipMessagesAdapter);

        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: 2017/8/10 根据条目类型跳转
                if (true) {
                    startActivity(new Intent(getActivity(), ShipNoonMessageActivity.class));
                } else {

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.huahai_one));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_back, R.id.ll_show_all_messages})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.ll_show_all_messages:
                startActivity(new Intent(getActivity(), AllMessagesActivity.class));
                break;
        }
    }

}
