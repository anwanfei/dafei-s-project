package com.junhangxintong.chuanzhangtong.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.news.bean.NewsListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/9/6.
 */

public class NewsListsAdapter extends BaseAdapter {

    private Context mContext;
    private List<NewsListBean.DataBean.ArrayBean> newsLists;
    private ViewHolder viewHolder;
    private boolean isRead = true;

    public NewsListsAdapter(Context mContext, List<NewsListBean.DataBean.ArrayBean> newsLists) {
        this.mContext = mContext;
        this.newsLists = newsLists;
    }

    @Override
    public int getCount() {
        return newsLists.size();
    }

    @Override
    public Object getItem(int i) {
        return newsLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {

            view = View.inflate(mContext, R.layout.item_ship_messages, null);
            viewHolder = new ViewHolder(view);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvMessageTime.setText(newsLists.get(i).getCreateDate());
        viewHolder.tvMessageTitle.setText(newsLists.get(i).getTitle());

      /*  if (isRead) {
            viewHolder.ivShowMessageNew.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ivShowMessageNew.setVisibility(View.GONE);
        }*/
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_message_title)
        TextView tvMessageTitle;
        @BindView(R.id.tv_message_time)
        TextView tvMessageTime;
        @BindView(R.id.iv_show_message_new)
        ImageView ivShowMessageNew;
        @BindView(R.id.ll_message)
        RelativeLayout llMessage;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /*public void ivMessageNewShowOrHide(boolean b) {
        isRead = b;
    }*/
}
