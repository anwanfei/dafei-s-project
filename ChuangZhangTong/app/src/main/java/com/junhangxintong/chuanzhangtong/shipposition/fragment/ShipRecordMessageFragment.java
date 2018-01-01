package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.mine.bean.ReportListBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.AllMessagesActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipArrivalMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipBerthingPortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipLeavePortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipNoonMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.WriteArrivalMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.WriteBerthingMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.WriteLeaveMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.WriteNoonMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipReportsAdapter;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

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
    @BindView(R.id.tv_show_no_message)
    TextView tvShowNoMessage;
    @BindView(R.id.refresh)
    XRefreshView refresh;
    private PopupWindow popupWindow;
    private boolean isShowPop;
    Class[] arrClass = {WriteNoonMessageActivity.class, WriteArrivalMessageActivity.class, WriteBerthingMessageActivity.class, WriteLeaveMessageActivity.class};
    private String id;
    private List<ReportListBean.DataBean.ArrayBean> reportLists;
    private String shipName;
    Class[] arrClasses = {ShipNoonMessageActivity.class, ShipBerthingPortMessageActivity.class, ShipArrivalMessageActivity.class, ShipLeavePortMessageActivity.class};
    private String roleId;
    private int page = 1;
    private List<ReportListBean.DataBean.ArrayBean> reportLoadMoreLists;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ship_recored_message, null);
        return view;
    }

    @Override
    protected void initListener() {
        super.initListener();

        refresh.setPinnedTime(1000);
        refresh.setAutoLoadMore(false);
        refresh.setMoveForHorizontal(true);
        refresh.setMoveHeadWhenDisablePullRefresh(true);//当下拉的时候不能上拉

        //设置当非RecyclerView上拉加载完成以后的回弹时间
        refresh.setScrollBackDuration(300);

        refresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                netGetNewestReport(Constants.PAGE_SIZE_10, String.valueOf(page), false);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                page += 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        netGetNewestReport(Constants.PAGE_SIZE_10, String.valueOf(page), true);
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra(Constants.ID);
        shipName = intent.getStringExtra(Constants.SHIP_NAME);
        tvTitle.setText(shipName);

        netGetNewestReport(Constants.PAGE_SIZE_10, String.valueOf(page), false);
    }

    private void netGetNewestReport(String pageSize, String page, final boolean isLoadmore) {
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_LISTS)
                .addParams(Constants.PAGE, page)
                .addParams(Constants.PAGE_SIZE, pageSize)
                .addParams(Constants.SHIP_ID, id)
                .addParams(Constants.SHIP_NAME, shipName)
                .addParams(Constants.TYPE, "")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onDataEmpty(String message) {
                        super.onDataEmpty(message);
                        lvMessage.setVisibility(View.GONE);
                        tvShowNoMessage.setVisibility(View.VISIBLE);
                        tvShowAllMesages.setVisibility(View.GONE);

                        refresh.stopLoadMore();
                        refresh.stopRefresh();
                    }

                    @Override
                    protected void onSuccess(String response, String message) {
                        lvMessage.setVisibility(View.VISIBLE);
                        llShowAllMessages.setVisibility(View.VISIBLE);
                        tvShowAllMesages.setVisibility(View.VISIBLE);

                        ReportListBean reportListBean = new Gson().fromJson(response, ReportListBean.class);

                        int count = reportListBean.getData().getCount();

                        if (isLoadmore) {
                            reportLoadMoreLists = reportListBean.getData().getArray();
                            reportLists.addAll(reportLoadMoreLists);
                        } else {
                            reportLists = reportListBean.getData().getArray();
                        }

                        refresh.stopRefresh();

                        if (reportLists.size() <= count) {
                            if (Build.VERSION.SDK_INT >= 11) {
                                reportLists.addAll(reportLists);
                            }
                            refresh.stopLoadMore();
                        } else {
                            refresh.setLoadComplete(true);
                            refresh.stopLoadMore();
                        }

                        ShipReportsAdapter shipMessagesAdapter = new ShipReportsAdapter(getActivity(), reportLists);
                        lvMessage.setAdapter(shipMessagesAdapter);

                        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                // TODO: 2017/8/10 根据条目类型跳转
                                int type = reportLists.get(i).getType();
                                int id = reportLists.get(i).getId();
                                switch (type) {
                                    case 1:
                                        gotoMessageDetailsActivity(0, id);
                                        break;
                                    case 2:
                                        gotoMessageDetailsActivity(1, id);
                                        break;
                                    case 3:
                                        gotoMessageDetailsActivity(2, id);
                                        break;
                                    case 4:
                                        gotoMessageDetailsActivity(3, id);
                                        break;
                                }
                            }
                        });
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        refresh.setPullLoadEnable(true);

        roleId = CacheUtils.getString(getActivity(), Constants.ROLEID);

        ivBack.setVisibility(View.GONE);
        tvTitle.setText(getResources().getString(R.string.huahai_one));
        ivShare.setVisibility(View.VISIBLE);
        tvSetting.setVisibility(View.VISIBLE);
        if (roleId.equals("2")) {
            ivShare.setImageResource(R.drawable.ic_input_message);
            tvSetting.setText(getResources().getString(R.string.input_message));
        }

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
                gotoAllMesageAcitivity();
                break;
            case R.id.iv_share:
                ShowHidePop();
                break;
            case R.id.tv_setting:
                ShowHidePop();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        netGetNewestReport(Constants.PAGE_SIZE_10, String.valueOf(page), false);
    }

    private void gotoAllMesageAcitivity() {
        Intent intent = new Intent(getActivity(), AllMessagesActivity.class);
        intent.putExtra(Constants.ID, id);
        intent.putExtra(Constants.SHIP_NAME, shipName);
        startActivity(intent);
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
        Intent intent = new Intent(getActivity(), arrClass[num]);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
        hidePop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            if (popupWindow != null) {
                popupWindow.dismiss();
            }
        }
    }

    private void gotoMessageDetailsActivity(int num, int id) {
        Intent intent = new Intent(getActivity(), arrClasses[num]);
        intent.putExtra(Constants.ID, String.valueOf(id));
        intent.putExtra(Constants.SHIP_NAME, shipName);
        startActivity(intent);
    }
}
