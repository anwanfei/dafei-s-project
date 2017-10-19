package com.junhangxintong.chuanzhangtong.dynamic.fragment;

import android.content.Intent;
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
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.dynamic.activity.ShipDynamicActivity;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.ShipDynamicRemindListsAdapter;
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

/**
 * Created by anwanfei on 2017/7/20.
 */

public class ShipDynamicFragment extends BaseFragment {

    List<String> allMessages = new ArrayList<>();
    @BindView(R.id.lv_dynamic_ship)
    ListView lvDynamicShip;
    @BindView(R.id.refresh)
    XRefreshView refresh;
    Unbinder unbinder;
    private ShipNewsSubFragmentAdapter shipNewsSubFragmentAdapter;
    private String token;
    private boolean isGetData = false;
    private int page = 1;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_ship_daynamic, null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();

        token = CacheUtils.getString(getActivity(), Constants.TOKEN);

        if (StringUtils.isNotEmpty(token)) {
            netGetDynamicRemindList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
        }

        for (int i = 0; i < 3; i++) {
            if (allMessages.size() < 3) {
                allMessages.add("君航号靠泊提醒");
                allMessages.add("中国号起航提醒");
                allMessages.add("华海号离港提醒");
            }
        }
        ShipDynamicRemindListsAdapter shipDynamicRemindListsAdapter = new ShipDynamicRemindListsAdapter(getActivity(), allMessages);
        lvDynamicShip.setAdapter(shipDynamicRemindListsAdapter);
        lvDynamicShip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(), ShipDynamicActivity.class));
            }
        });
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        refresh.setPullLoadEnable(true);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        //进入当前Fragment
        if (enter && !isGetData) {
            Log.e("TAG", "船舶动态");
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
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.REMIND_TYPE, "2")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {

                        /*DynamicRemindListBean dynamicRemindListBean = new Gson().fromJson(response, DynamicRemindListBean.class);
                        dynamincRemindLists = dynamicRemindListBean.getData().getArray();

                        DynamicRemindListsAdapter dynamicRemindListsAdapter = new DynamicRemindListsAdapter(getActivity(), dynamincRemindLists);
                        lvMessage.setAdapter(dynamicRemindListsAdapter);
                        lvDynamicShip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                startActivity(new Intent(getActivity(), ShipDynamicActivity.class));
                                view.findViewById(R.id.iv_show_message_new).setVisibility(View.GONE);
                            }
                        });*/
                    }
                });
    }
}
