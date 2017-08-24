package com.junhangxintong.chuanzhangtong.dynamic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.news.adapter.ShipNewsSubFragmentAdapter;
import com.junhangxintong.chuanzhangtong.shipposition.activity.AllMessagesActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipArrivalMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipBerthingPortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipLeavePortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipNoonMessageActivity;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/23.
 */

public class MessageRecoedFragment extends BaseFragment {

    List<String> allMessages = new ArrayList<>();
    @BindView(R.id.lv_message_record)
    ListView lvMessageRecord;
    @BindView(R.id.ll_show_all_messages)
    LinearLayout llShowAllMessages;
    private ShipNewsSubFragmentAdapter shipNewsSubFragmentAdapter;
    private Unbinder unbinder;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_message_record_news_sub_layout, null);
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

    @Override
    protected void initData() {
        super.initData();
        for (int i = 0; i < Constants.TEST_DATA_NUM; i++) {
            if (allMessages.size() < 15) {
                allMessages.add("华海" + i + "号靠泊报");
                allMessages.add("haixun225" + i + "离港报");
                allMessages.add("君航正午报");
                allMessages.add("haixun225" + i + "抵港报");
                allMessages.add("haixun2251靠舶报");
                allMessages.add("君航离港报");
                allMessages.add("haixun225" + i + "正午报");
                allMessages.add("华海" + i + "号抵港报");
            }
        }

        shipNewsSubFragmentAdapter = new ShipNewsSubFragmentAdapter(getActivity(), allMessages);
        lvMessageRecord.setAdapter(shipNewsSubFragmentAdapter);

        lvMessageRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0 || i == 4 || i == 8 || i == 12) {
                    startActivity(new Intent(getActivity(), ShipBerthingPortMessageActivity.class));
                } else if (i == 1 || i == 5 || i == 9 || i == 13) {
                    startActivity(new Intent(getActivity(), ShipLeavePortMessageActivity.class));
                } else if (i == 2 || i == 6 || i == 10 || i == 14) {
                    startActivity(new Intent(getActivity(), ShipNoonMessageActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), ShipArrivalMessageActivity.class));
                }

                view.findViewById(R.id.iv_show_message_new).setVisibility(View.GONE);
            }
        });
    }

    @OnClick(R.id.ll_show_all_messages)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), AllMessagesActivity.class));
    }
}
