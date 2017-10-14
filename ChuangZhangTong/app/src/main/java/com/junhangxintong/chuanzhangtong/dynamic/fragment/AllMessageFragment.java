package com.junhangxintong.chuanzhangtong.dynamic.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.dynamic.activity.CrewCertificateActivity;
import com.junhangxintong.chuanzhangtong.dynamic.activity.ShipCertificateActivity;
import com.junhangxintong.chuanzhangtong.dynamic.activity.ShipDynamicActivity;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.DynamicRemindListsAdapter;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindListBean;
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

import org.apache.commons.lang.StringUtils;

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
    @BindView(R.id.refrsh)
    XRefreshView refresh;
    private int a = 0;
    Unbinder unbinder;

    List<String> allMessages = new ArrayList<>();
    private ShipNewsSubFragmentAdapter shipNewsSubFragmentAdapter;
    private List<DynamicRemindListBean.DataBean.ArrayBean> dynamincRemindLists;
    Class[] arrClasses = {ShipDynamicActivity.class, CrewCertificateActivity.class, ShipCertificateActivity.class};
    private String token;
    private boolean isGetData = false;
    private int page = 1;
    private List<DynamicRemindListBean.DataBean.ArrayBean> dynamincRemindLoadMoreLists;

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
        refresh.setPullLoadEnable(true);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 根据是否登录判断是否要进入登录界面
        token = CacheUtils.getString(getActivity(), Constants.TOKEN);

        if (StringUtils.isEmpty(token)) {
            startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
            getActivity().finish();
        }
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
            netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (StringUtils.isNotEmpty(token)) {
                netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
            }
        }
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
                netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                page += 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), true);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isGetData) {
            isGetData = true;
            netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
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
            Log.e("TAG", "所有消息");
            isGetData = true;
            //这里可以做网络请求或者需要的数据刷新操作
            netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
        } else {
            isGetData = false;
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    private void netGetDynamicRemindList(String pageSize, String page, final boolean isLoadmore) {
        String userId = CacheUtils.getString(getActivity(), Constants.ID);
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.DYNAMIC_REMIND_LIST)
                .addParams(Constants.PAGE_SIZE, pageSize)
                .addParams(Constants.PAGE, page)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.REMIND_TYPE, "")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onDataEmpty(String message) {
                        super.onDataEmpty(message);
                        refresh.stopLoadMore();
                        refresh.stopRefresh();
                    }

                    @Override
                    protected void onSuccess(String response, String message) {
                        DynamicRemindListBean dynamicRemindListBean = new Gson().fromJson(response, DynamicRemindListBean.class);
                        int count = dynamicRemindListBean.getData().getCount();

                        if (isLoadmore) {
                            dynamincRemindLoadMoreLists = dynamicRemindListBean.getData().getArray();
                            dynamincRemindLists.addAll(dynamincRemindLoadMoreLists);
                        } else {
                            dynamincRemindLists = dynamicRemindListBean.getData().getArray();
                        }

                        refresh.stopRefresh();

                        if (dynamincRemindLists.size() <= count) {
                            if (Build.VERSION.SDK_INT >= 11) {
                                dynamincRemindLists.addAll(dynamincRemindLists);
                            }
                            refresh.stopLoadMore();
                        } else {
                            refresh.setLoadComplete(true);
                        }

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
                    }
                });
    }

    private void refreshLoadmoreFail() {
        refresh.stopRefresh(false);
        refresh.stopLoadMore(false);
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
                                        intent1.putExtra(Constants.ID, ids);
                                        startActivity(intent1);
                                        break;
                                    case 2:
                                        Intent intent2 = new Intent(getActivity(), ShipBerthingPortMessageActivity.class);
                                        intent2.putExtra(Constants.ID, ids);
                                        intent2.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        startActivity(intent2);
                                        break;
                                    case 3:
                                        Intent intent3 = new Intent(getActivity(), ShipArrivalMessageActivity.class);
                                        intent3.putExtra(Constants.ID, ids);
                                        intent3.putExtra(Constants.FROM_DYNAMIC, Constants.FROM_DYNAMIC);
                                        startActivity(intent3);
                                        break;
                                    case 4:
                                        Intent intent4 = new Intent(getActivity(), ShipLeavePortMessageActivity.class);
                                        intent4.putExtra(Constants.ID, ids);
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
