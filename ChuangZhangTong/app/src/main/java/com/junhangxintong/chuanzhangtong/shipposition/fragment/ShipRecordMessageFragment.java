package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.mapapi.map.MapView;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.shipposition.activity.AllMessagesActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipNoonMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.WriteArrivalMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.WriteBerthingMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.WriteLeaveMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.WriteNoonMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipMessagesAdapter;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/8.
 */

public class ShipRecordMessageFragment extends BaseFragment implements View.OnClickListener {
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
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.ll_show_all_messages)
    LinearLayout llShowAllMessages;
    @BindView(R.id.view_line)
    View viewLine;
    private PopupWindow popupWindow;
    private boolean isShowPop;
    Class[] arrClass = {WriteNoonMessageActivity.class, WriteArrivalMessageActivity.class, WriteBerthingMessageActivity.class, WriteLeaveMessageActivity.class};

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
        ivShare.setVisibility(View.VISIBLE);
        tvSetting.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.ic_input_message);
        tvSetting.setText(getResources().getString(R.string.input_message));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_back, R.id.ll_show_all_messages, R.id.iv_share, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.ll_show_all_messages:
                startActivity(new Intent(getActivity(), AllMessagesActivity.class));
                break;
            case R.id.iv_share:
                ShowHidePop();
                break;
            case R.id.tv_setting:
                ShowHidePop();
                break;
        }
    }

    private void ShowHidePop() {
        if (isShowPop) {
            hidePop();
            isShowPop = false;
        } else {
            showPopShipMessage();
            isShowPop = true;
        }
    }

    private void showPopShipMessage() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_ship_message, null);
        TextView tv_pop_noon_message = (TextView) view.findViewById(R.id.tv_pop_noon_message);
        TextView tv_pop_arrival_message = (TextView) view.findViewById(R.id.tv_pop_arrival_message);
        TextView tv_pop_berthing = (TextView) view.findViewById(R.id.tv_pop_berthing);
        TextView tv_ship_leave_message = (TextView) view.findViewById(R.id.tv_ship_leave_message);

        popupWindow = new PopupWindow(view, DensityUtil.dp2px(getActivity(), 120), LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setContentView(view);

        //点击pop以外的部分消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(tvSetting, 0, 10);

        tv_ship_leave_message.setOnClickListener(this);
        tv_pop_arrival_message.setOnClickListener(this);
        tv_pop_berthing.setOnClickListener(this);
        tv_pop_noon_message.setOnClickListener(this);

    }

    private void hidePop() {
        popupWindow.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pop_noon_message:
                gotoWriteMessageActivity(0);
                break;
            case R.id.tv_pop_arrival_message:
                gotoWriteMessageActivity(1);
                break;
            case R.id.tv_pop_berthing:
                gotoWriteMessageActivity(2);
                break;
            case R.id.tv_ship_leave_message:
                gotoWriteMessageActivity(3);
                break;
        }
    }

    public void gotoWriteMessageActivity(int num) {
        startActivity(new Intent(getActivity(), arrClass[num]));
        getActivity().finish();
    }
}
