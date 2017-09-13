package com.junhangxintong.chuanzhangtong.dynamic.fragment;

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
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.dynamic.activity.CrewCertificateActivity;
import com.junhangxintong.chuanzhangtong.dynamic.activity.ShipCertificateActivity;
import com.junhangxintong.chuanzhangtong.dynamic.activity.ShipDynamicActivity;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.DynamicRemindListsAdapter;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindArrivalReportBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindBerthingReportBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindLeaveReportBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindListBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindNonnReportBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicReportTypeBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.news.adapter.ShipNewsSubFragmentAdapter;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipArrivalMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipBerthingPortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipLeavePortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipNoonMessageActivity;
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
 * Created by anwanfei on 2017/7/20.
 */

public class AllMessageFragment extends BaseFragment {
    @BindView(R.id.lv_message)
    ListView lvMessage;
    Unbinder unbinder;
    private int a = 0;

    List<String> allMessages = new ArrayList<>();
    private ShipNewsSubFragmentAdapter shipNewsSubFragmentAdapter;
    private List<DynamicRemindListBean.DataBean.ArrayBean> dynamincRemindLists;
    Class[] arrClasses = {ShipDynamicActivity.class, CrewCertificateActivity.class, ShipCertificateActivity.class};

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_daynamic_news_sub_layout, null);
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
        netGetDynamicRemindList("");
    }

    private void netGetDynamicRemindList(String remindType) {
        String userId = CacheUtils.getString(getActivity(), Constants.ID);
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.DYNAMIC_REMIND_LIST)
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.REMIND_TYPE, remindType)
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
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                DynamicRemindListBean dynamicRemindListBean = new Gson().fromJson(response, DynamicRemindListBean.class);
                                dynamincRemindLists = dynamicRemindListBean.getData().getArray();

                                DynamicRemindListsAdapter dynamicRemindListsAdapter = new DynamicRemindListsAdapter(getActivity(), dynamincRemindLists);
                                lvMessage.setAdapter(dynamicRemindListsAdapter);

                                lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        int remindType = dynamincRemindLists.get(i).getRemindType();
                                        int id = dynamincRemindLists.get(i).getId();
                                        switch (remindType) {
                                            case 1:
                                                netGetReportType(String.valueOf(id));
                                                break;
                                            case 2:
                                                gotoDynamicDetailsActivity(ShipDynamicActivity.class, String.valueOf(id));
                                                break;
                                            case 3:
                                                gotoDynamicDetailsActivity(CrewCertificateActivity.class, String.valueOf(id));
                                                break;
                                            case 4:
                                                gotoDynamicDetailsActivity(ShipCertificateActivity.class, String.valueOf(id));
                                                break;
                                        }
                                        view.findViewById(R.id.iv_show_message_new).setVisibility(View.GONE);
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

    private void netGetReportType(String id) {

        NetUtils.postWithHeader(getActivity(), ConstantsUrls.DYNAMIC_DETAILS)
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
                            int reportType = dynamicReportTypeBean.getData().getReportType();
                            if (code.equals("200")) {
                                switch (reportType) {
                                    case 1:
                                        DynamicRemindNonnReportBean dynamicRemindNonnReportBean = new Gson().fromJson(response, DynamicRemindNonnReportBean.class);
                                        Intent intent1 = new Intent(getActivity(), ShipNoonMessageActivity.class);
                                        intent1.putExtra(Constants.DYNAMIC_REPORT, dynamicRemindNonnReportBean);
                                        intent1.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        startActivity(intent1);

                                        break;
                                    case 2:
                                        DynamicRemindBerthingReportBean dynamicRemindBerthingReportBean = new Gson().fromJson(response, DynamicRemindBerthingReportBean.class);
                                        Intent intent2 = new Intent(getActivity(), ShipBerthingPortMessageActivity.class);
                                        intent2.putExtra(Constants.DYNAMIC_REPORT, dynamicRemindBerthingReportBean);
                                        intent2.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        DynamicRemindArrivalReportBean dynamicRemindArrivalReportBean = new Gson().fromJson(response, DynamicRemindArrivalReportBean.class);
                                        Intent intent3 = new Intent(getActivity(), ShipArrivalMessageActivity.class);
                                        intent3.putExtra(Constants.DYNAMIC_REPORT, dynamicRemindArrivalReportBean);
                                        intent3.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        DynamicRemindLeaveReportBean dynamicRemindLeaveReportBean = new Gson().fromJson(response, DynamicRemindLeaveReportBean.class);
                                        Intent intent4 = new Intent(getActivity(), ShipLeavePortMessageActivity.class);
                                        intent4.putExtra(Constants.DYNAMIC_REPORT, dynamicRemindLeaveReportBean);
                                        intent4.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        startActivity(intent4);
                                        break;
                                }

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

    private void gotoDynamicDetailsActivity(Class targetClass, String id) {
        Intent intent = new Intent(getActivity(), targetClass);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
    }
}
