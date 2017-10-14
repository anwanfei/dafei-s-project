package com.junhangxintong.chuanzhangtong.dynamic.fragment;

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

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.dynamic.activity.CrewCertificateActivity;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.DynamicRemindListsAdapter;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindListBean;
import com.junhangxintong.chuanzhangtong.news.adapter.ShipNewsSubFragmentAdapter;
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

import static com.junhangxintong.chuanzhangtong.common.MyApplication.token;

/**
 * Created by anwanfei on 2017/8/14.
 */

public class CrewCertificateFragment extends BaseFragment {
    @BindView(R.id.lv_message)
    ListView lvMessage;
    Unbinder unbinder;

    List<String> allMessages = new ArrayList<>();
    @BindView(R.id.refrsh)
    XRefreshView refresh;
    private ShipNewsSubFragmentAdapter shipNewsSubFragmentAdapter;
    private List<DynamicRemindListBean.DataBean.ArrayBean> dynamincRemindLists;
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    protected void initData() {
        super.initData();

        if (StringUtils.isNotEmpty(token)) {
            netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), false);

            lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    int id = dynamincRemindLists.get(i).getId();
                    Intent intent = new Intent(getActivity(), CrewCertificateActivity.class);
                    intent.putExtra(Constants.ID, String.valueOf(id));
                    startActivity(intent);
                    view.findViewById(R.id.iv_show_message_new).setVisibility(View.GONE);
                }
            });
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (StringUtils.isNotEmpty(token)) {
                netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), false);

                lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        int id = dynamincRemindLists.get(i).getId();
                        Intent intent = new Intent(getActivity(), CrewCertificateActivity.class);
                        intent.putExtra(Constants.ID, String.valueOf(id));
                        startActivity(intent);
                        view.findViewById(R.id.iv_show_message_new).setVisibility(View.GONE);
                    }
                });
            }
        }
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
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //   进入当前Fragment
        if (enter && !isGetData) {
            Log.e("TAG", "船员证书");
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
                .addParams(Constants.REMIND_TYPE, "3")
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
                    }
                });
    }
}
