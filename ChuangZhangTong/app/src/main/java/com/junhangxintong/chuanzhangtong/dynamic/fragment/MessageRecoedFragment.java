package com.junhangxintong.chuanzhangtong.dynamic.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.DynamicRemindListsAdapter;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindListBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicReportTypeBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.news.adapter.ShipNewsSubFragmentAdapter;
import com.junhangxintong.chuanzhangtong.shipposition.activity.AllMessagesActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipArrivalMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipBerthingPortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipLeavePortMessageActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.ShipNoonMessageActivity;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.common.MyApplication.token;
import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

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
    private List<DynamicRemindListBean.DataBean.ArrayBean> dynamincRemindLists;
    private boolean isGetData = false;

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
        if (StringUtils.isNotEmpty(token)) {
            netGetDynamicRemindList();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (StringUtils.isNotEmpty(token)) {
                netGetDynamicRemindList();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!isGetData) {
            isGetData = true;
            netGetDynamicRemindList();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isGetData = false;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            Log.e("TAG", "报文");
            isGetData = true;
            //这里可以做网络请求或者需要的数据刷新操作
            netGetDynamicRemindList();
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @OnClick(R.id.ll_show_all_messages)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), AllMessagesActivity.class);
        intent.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
        startActivity(intent);
    }

    private void netGetDynamicRemindList() {
        String userId = CacheUtils.getString(getActivity(), Constants.ID);
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.DYNAMIC_REMIND_LIST)
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.REMIND_TYPE, "1")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        final DynamicRemindListBean dynamicRemindListBean = new Gson().fromJson(response, DynamicRemindListBean.class);
                        dynamincRemindLists = dynamicRemindListBean.getData().getArray();

                        DynamicRemindListsAdapter dynamicRemindListsAdapter = new DynamicRemindListsAdapter(getActivity(), dynamincRemindLists);
                        lvMessageRecord.setAdapter(dynamicRemindListsAdapter);

                        lvMessageRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = dynamincRemindLists.get(i).getId();
                                netGetReportType(String.valueOf(id));
                                view.findViewById(R.id.iv_show_message_new).setVisibility(View.GONE);
                            }
                        });
                    }
                });
    }

    private void netGetReportType(final String ids) {

        NetUtils.postWithHeader(getActivity(), ConstantsUrls.DYNAMIC_DETAILS)
                .addParams(Constants.ID, ids)
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
                                        Intent intent1 = new Intent(getActivity(), ShipNoonMessageActivity.class);
                                        intent1.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        intent1.putExtra(Constants.ID,ids);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent(getActivity(), ShipBerthingPortMessageActivity.class);
                                        intent2.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        intent2.putExtra(Constants.ID, ids);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent(getActivity(), ShipArrivalMessageActivity.class);
                                        intent3.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        intent3.putExtra(Constants.ID, ids);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent(getActivity(), ShipLeavePortMessageActivity.class);
                                        intent4.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        intent4.putExtra(Constants.ID, ids);
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
}
