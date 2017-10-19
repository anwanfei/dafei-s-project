package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.DynamicReportListsAdapter;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindArrivalReportBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicReportBean;
import com.junhangxintong.chuanzhangtong.mine.bean.ReportListBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipArrivalMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipReportsAdapter;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/10.
 */

public class ArrivaMessageFragment extends BaseFragment {
    @BindView(R.id.lv_message)
    ListView lvMessage;
    Unbinder unbinder;
    private List<ReportListBean.DataBean.ArrayBean> reportLists;
    private List<DynamicReportBean.DataBean.ArrayBean> dynamicReportLists;
    private String fromDynamic = "";
    private Intent intent;
    private String shipName;

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

        intent = getActivity().getIntent();

        fromDynamic = intent.getStringExtra(Constants.FROM_DYNAMIC);
        if (fromDynamic != null) {
            netGetArrivalReportFromDynamic();
        } else {
            netGetArrivalReportFromShip();
        }
    }

    private void netGetArrivalReportFromDynamic() {
        String userId = CacheUtils.getString(getActivity(), Constants.ID);
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_DYNAMIC_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.REPORT_TYPE, "3")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        DynamicReportBean dynamicReportBean = new Gson().fromJson(response, DynamicReportBean.class);
                        dynamicReportLists = dynamicReportBean.getData().getArray();

                        DynamicReportListsAdapter dynamicReportListsAdapter = new DynamicReportListsAdapter(getActivity(), dynamicReportLists);
                        lvMessage.setAdapter(dynamicReportListsAdapter);

                        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String id = String.valueOf(dynamicReportLists.get(i).getId());
                                String shipName = dynamicReportLists.get(i).getShipName();
                                Intent intent3 = new Intent(getActivity(), ShipArrivalMessageActivity.class);
                                intent3.putExtra(Constants.ID, id);
                                intent.putExtra(Constants.SHIP_NAME, ArrivaMessageFragment.this.shipName);
                                startActivity(intent3);
                            }
                        });
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (fromDynamic != null) {
            netGetArrivalReportFromDynamic();
        } else {
            netGetArrivalReportFromShip();
        }
    }

    private void netGetArrivalReportFromShip() {
        final String shipId = intent.getStringExtra(Constants.ID);
        shipName = intent.getStringExtra(Constants.SHIP_NAME);

        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.SHIP_NAME, shipName)
                .addParams(Constants.TYPE, "3")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        lvMessage.setVisibility(View.VISIBLE);
                        ReportListBean reportListBean = new Gson().fromJson(response, ReportListBean.class);
                        reportLists = reportListBean.getData().getArray();

                        ShipReportsAdapter shipMessagesAdapter = new ShipReportsAdapter(getActivity(), reportLists);
                        lvMessage.setAdapter(shipMessagesAdapter);

                        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(getActivity(), ShipArrivalMessageActivity.class);
                                int id = reportLists.get(i).getId();
                                intent.putExtra(Constants.ID, String.valueOf(id));
                                intent.putExtra(Constants.SHIP_NAME, shipName);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    protected void onDataEmpty(String message) {
                        super.onDataEmpty(message);
                        lvMessage.setVisibility(View.GONE);
                    }
                });
    }

    private void netGetReportType(String id) {

        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_INFO)
                .addParams(Constants.ID, id)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        DynamicRemindArrivalReportBean dynamicRemindArrivalReportBean = new Gson().fromJson(response, DynamicRemindArrivalReportBean.class);
                        Intent intent3 = new Intent(getActivity(), ShipArrivalMessageActivity.class);
                        intent3.putExtra(Constants.DYNAMIC_REPORT, dynamicRemindArrivalReportBean);
                        intent3.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                        startActivity(intent3);

                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
