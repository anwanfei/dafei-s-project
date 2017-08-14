package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipLeavePortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipMessagesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/10.
 */

public class LeaveMessageFragment extends BaseFragment {
    @BindView(R.id.lv_message)
    ListView lvMessage;
    Unbinder unbinder;
    List<String> messages = new ArrayList<>();
    private ShipMessagesAdapter shipMessagesAdapter;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_noon_message, null);
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
    protected void initData() {
        super.initData();
        for (int i = 0; i < 5; i++) {
            if (messages.size() < 5) {
                messages.add("华海一号离港报");
            }
        }

        shipMessagesAdapter = new ShipMessagesAdapter(getActivity(), messages);
        lvMessage.setAdapter(shipMessagesAdapter);

        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), ShipLeavePortMessageActivity.class));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
