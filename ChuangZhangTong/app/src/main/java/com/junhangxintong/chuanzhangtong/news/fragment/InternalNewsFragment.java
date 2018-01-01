package com.junhangxintong.chuanzhangtong.news.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.news.activity.NationalityConventionActivity;
import com.junhangxintong.chuanzhangtong.news.adapter.NewsListsAdapter;
import com.junhangxintong.chuanzhangtong.news.adapter.ShipNewsSubFragmentAdapter;
import com.junhangxintong.chuanzhangtong.news.bean.NewsListBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/8.
 */

public class InternalNewsFragment extends BaseFragment {
    @BindView(R.id.lv_message)
    ListView lvMessage;
    Unbinder unbinder;

    @BindView(R.id.refrsh)
    XRefreshView refresh;
    private ShipNewsSubFragmentAdapter shipNewsSubFragmentAdapter;
    private List<NewsListBean.DataBean.ArrayBean> internalNewsLists;
    private int page = 1;
    private List<NewsListBean.DataBean.ArrayBean> internalNewsLoadMoreLists;

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
                netGetInternalNewsList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                page += 1;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        netGetInternalNewsList(Constants.PAGE_SIZE_10, String.valueOf(page), true);
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
        netGetInternalNewsList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
    }

    private void netGetInternalNewsList(String pageSize, String page, final boolean isLoadmore) {
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.QUERY_NEWS_LISTS)
                .addParams(Constants.PAGE, page)
                .addParams(Constants.PAGE_SIZE, pageSize)
                .addParams(Constants.NEWS_TYPE, "0")
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
                        NewsListBean newsListBean = new Gson().fromJson(response, NewsListBean.class);
                        int count = newsListBean.getData().getCount();

                        if (isLoadmore) {
                            internalNewsLoadMoreLists = newsListBean.getData().getArray();
                            internalNewsLists.addAll(internalNewsLoadMoreLists);
                        } else {
                            internalNewsLists = newsListBean.getData().getArray();
                        }

                        refresh.stopRefresh();

                        if (internalNewsLists.size() <= count) {
                            if (Build.VERSION.SDK_INT >= 11) {
                                internalNewsLists.addAll(internalNewsLists);
                            }
                            refresh.stopLoadMore();
                        } else {
                            refresh.setLoadComplete(true);
                            refresh.stopLoadMore();
                        }
                        NewsListsAdapter newsListsAdapter = new NewsListsAdapter(getActivity(), internalNewsLists);
                        lvMessage.setAdapter(newsListsAdapter);
                        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = internalNewsLists.get(i).getId();
                                Intent intent = new Intent(getActivity(), NationalityConventionActivity.class);
                                intent.putExtra(Constants.ID, id);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        netGetInternalNewsList(Constants.PAGE_SIZE_10, String.valueOf(page), false);
    }
}
