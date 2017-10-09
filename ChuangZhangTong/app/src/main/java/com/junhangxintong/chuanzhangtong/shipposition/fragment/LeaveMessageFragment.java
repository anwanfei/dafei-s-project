package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.DynamicReportListsAdapter;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindLeaveReportBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicReportBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicReportTypeBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.ReportListBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipLeavePortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipMessagesAdapter;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipReportsAdapter;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

/**
 * Created by anwanfei on 2017/8/10.
 */

public class LeaveMessageFragment extends BaseFragment {
    @BindView(R.id.lv_message)
    ListView lvMessage;
    Unbinder unbinder;
    List<String> messages = new ArrayList<>();
    private ShipMessagesAdapter shipMessagesAdapter;
    private List<ReportListBean.DataBean.ArrayBean> reportLists;
    private List<DynamicReportBean.DataBean.ArrayBean> dynamicReportLists;
    private String fromDynamic = "";
    private String shipId;
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

        Intent intent = getActivity().getIntent();

        shipId = intent.getStringExtra(Constants.ID);
        shipName = intent.getStringExtra(Constants.SHIP_NAME);

        fromDynamic = intent.getStringExtra(Constants.FROM_DYNAMIC);
        if (fromDynamic != null) {
            netGetLeavrReportListFromDynamic();
        } else {
            netGetLeavrReportListFromShip();
        }
    }

    private void netGetLeavrReportListFromDynamic() {
        String userId = CacheUtils.getString(getActivity(), Constants.ID);
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_DYNAMIC_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.REPORT_TYPE, "4")
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
                                DynamicReportBean dynamicReportBean = new Gson().fromJson(response, DynamicReportBean.class);
                                dynamicReportLists = dynamicReportBean.getData().getArray();

                                DynamicReportListsAdapter dynamicReportListsAdapter = new DynamicReportListsAdapter(getActivity(), dynamicReportLists);
                                lvMessage.setAdapter(dynamicReportListsAdapter);

                                lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        int id = dynamicReportLists.get(i).getId();
                                        Intent intent = new Intent(getActivity(), ShipLeavePortMessageActivity.class);
                                        String shipName = dynamicReportLists.get(i).getShipName();
                                        intent.putExtra(Constants.ID, String.valueOf(id));
                                        intent.putExtra(Constants.SHIP_NAME,shipName);
                                        startActivity(intent);
                                    }
                                });

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE);
                                if (sharedPreferences != null) {
                                    sharedPreferences.edit().clear().commit();
                                }
                                //保存获取权限的sp
                                CacheUtils.putBoolean(getActivity(), Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (fromDynamic != null) {
            netGetLeavrReportListFromDynamic();
        } else {
            netGetLeavrReportListFromShip();
        }
    }

    private void netGetLeavrReportListFromShip() {

        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.SHIP_NAME, shipName)
                .addParams(Constants.TYPE, "4")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
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
                                ReportListBean reportListBean = new Gson().fromJson(response, ReportListBean.class);
                                reportLists = reportListBean.getData().getArray();

                                ShipReportsAdapter shipMessagesAdapter = new ShipReportsAdapter(getActivity(), reportLists);
                                lvMessage.setAdapter(shipMessagesAdapter);

                                lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(getActivity(), ShipLeavePortMessageActivity.class);
                                        int id = reportLists.get(i).getId();
                                        intent.putExtra(Constants.ID, String.valueOf(id));
                                        intent.putExtra(Constants.SHIP_NAME, shipName);
                                        startActivity(intent);
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
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void netGetReportType(String id) {

        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_INFO)
                .addParams(Constants.ID, id)
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
                            DynamicReportTypeBean dynamicReportTypeBean = new Gson().fromJson(response, DynamicReportTypeBean.class);
                            String message = dynamicReportTypeBean.getMessage();
                            String code = dynamicReportTypeBean.getCode();
                            if (code.equals("200")) {
                                DynamicRemindLeaveReportBean dynamicRemindLeaveReportBean = new Gson().fromJson(response, DynamicRemindLeaveReportBean.class);
                                Intent intent4 = new Intent(getActivity(), ShipLeavePortMessageActivity.class);
                                intent4.putExtra(Constants.DYNAMIC_REPORT, dynamicRemindLeaveReportBean);
                                intent4.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                startActivity(intent4);

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getActivity().getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(getActivity(), Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

}
