package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Intent;
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
import com.junhangxintong.chuanzhangtong.dynamic.adapter.DynamicReportListsAdapter;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicReportBean;
import com.junhangxintong.chuanzhangtong.mine.bean.ReportListBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipNoonMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipMessagesAdapter;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.ShipReportsAdapter;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/10.
 */

public class NoonzMessageFragment extends BaseFragment {
    @BindView(R.id.lv_message)
    ListView lvMessage;
    Unbinder unbinder;
    List<String> messages = new ArrayList<>();
    private ShipMessagesAdapter shipMessagesAdapter;
    private List<ReportListBean.DataBean.ArrayBean> reportLists;
    private String shipId;
    private List<DynamicReportBean.DataBean.ArrayBean> dynamicReportLists;
    private String fromDynamic = "";
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
        if (StringUtils.isNotBlank(fromDynamic)) {
            netGetNoonReportListFromDynamic();
        } else {
            netGetNoonReportListFromShip();
        }
    }

    private void netGetNoonReportListFromDynamic() {
        String userId = CacheUtils.getString(getActivity(), Constants.ID);
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_DYNAMIC_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.REPORT_TYPE, "1")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        final DynamicReportBean dynamicReportBean = new Gson().fromJson(response, DynamicReportBean.class);
                        dynamicReportLists = dynamicReportBean.getData().getArray();

                        DynamicReportListsAdapter dynamicReportListsAdapter = new DynamicReportListsAdapter(getActivity(), dynamicReportLists);
                        lvMessage.setAdapter(dynamicReportListsAdapter);

                        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String id = String.valueOf(dynamicReportLists.get(i).getId());
                                Intent intent1 = new Intent(getActivity(), ShipNoonMessageActivity.class);
                                String shipName = dynamicReportLists.get(i).getShipName();
                                intent1.putExtra(Constants.ID, id);
                                intent1.putExtra(Constants.SHIP_NAME, shipName);
                                startActivity(intent1);
                            }
                        });
                    }
                });
    }

    private void netGetNoonReportListFromShip() {
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.REPORT_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.SHIP_NAME, shipName)
                .addParams(Constants.TYPE, "1")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onDataEmpty(String message) {
                        super.onDataEmpty(message);
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        lvMessage.setVisibility(View.GONE);
                    }

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
                                Intent intent = new Intent(getActivity(), ShipNoonMessageActivity.class);
                                int id = reportLists.get(i).getId();
                                intent.putExtra(Constants.ID, String.valueOf(id));
                                intent.putExtra(Constants.SHIP_NAME, shipName);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (StringUtils.isNotBlank(fromDynamic)) {
            netGetNoonReportListFromDynamic();
        } else {
            netGetNoonReportListFromShip();
        }
    }
}
