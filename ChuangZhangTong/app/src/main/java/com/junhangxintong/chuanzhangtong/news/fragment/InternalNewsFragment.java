package com.junhangxintong.chuanzhangtong.news.fragment;

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
import com.junhangxintong.chuanzhangtong.news.activity.NationalityConventionActivity;
import com.junhangxintong.chuanzhangtong.news.adapter.NewsListsAdapter;
import com.junhangxintong.chuanzhangtong.news.adapter.ShipNewsSubFragmentAdapter;
import com.junhangxintong.chuanzhangtong.news.bean.NewsListBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import java.util.ArrayList;
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

    List<String> internalNewsLists = new ArrayList<>();
    private ShipNewsSubFragmentAdapter shipNewsSubFragmentAdapter;
    private List<NewsListBean.DataBean.ArrayBean> newsLists;

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
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.QUERY_NEWS_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.NEWS_TYPE, "0")
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        NewsListBean newsListBean = new Gson().fromJson(response, NewsListBean.class);
                        newsLists = newsListBean.getData().getArray();

                        NewsListsAdapter newsListsAdapter = new NewsListsAdapter(getActivity(), newsLists);
                        lvMessage.setAdapter(newsListsAdapter);
                        lvMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = newsLists.get(i).getId();
                                Intent intent = new Intent(getActivity(), NationalityConventionActivity.class);
                                intent.putExtra(Constants.ID, id);
                                startActivity(intent);
                            }
                        });
                    }
                });
    }
}
