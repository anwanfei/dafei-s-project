package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Context;
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
import android.widget.Toast;

import com.baidu.mapapi.map.MapView;
import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
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
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

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
    @BindView(R.id.tv_show_no_message)
    TextView tvShowNoMessage;
    private PopupWindow popupWindow;
    private boolean isShowPop;
    Class[] arrClass = {WriteNoonMessageActivity.class, WriteArrivalMessageActivity.class, WriteBerthingMessageActivity.class, WriteLeaveMessageActivity.class};
    private String id;
    private List<ReportListBean.DataBean.ArrayBean> reportLists;
    private String shipName;
    Class[] arrClasses = {ShipNoonMessageActivity.class, ShipBerthingPortMessageActivity.class, ShipArrivalMessageActivity.class, ShipLeavePortMessageActivity.class};
    private String roleId;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ship_recored_message, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra(Constants.ID);
        shipName = intent.getStringExtra(Constants.SHIP_NAME);
        tvTitle.setText(shipName);

        netGetNewestReport();
    }

    private void netGetNewestReport() {
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.SHIP_ID, id)
                .addParams(Constants.SHIP_NAME, shipName)
                .addParams(Constants.TYPE, "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(getActivity(), Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                lvMessage.setVisibility(View.VISIBLE);
                                llShowAllMessages.setVisibility(View.VISIBLE);
                                ReportListBean reportListBean = new Gson().fromJson(response, ReportListBean.class);
                                reportLists = reportListBean.getData().getArray();


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

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getActivity().getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(getActivity(), Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                            } else if (code.equals("404")) {
                                lvMessage.setVisibility(View.GONE);
                                tvShowNoMessage.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

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
        netGetNewestReport();
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
