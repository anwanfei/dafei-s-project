package com.junhangxintong.chuanzhangtong.dynamic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicReportBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/9/11.
 */

public class DynamicReportListsAdapter extends BaseAdapter {

    private Context mContext;
    private List<DynamicReportBean.DataBean.ArrayBean> dynamicReportLists;
    private ViewHolder viewHolder;
    private boolean isRead = true;

    public DynamicReportListsAdapter(Context mContext, List<DynamicReportBean.DataBean.ArrayBean> dynamicReportLists) {
        this.mContext = mContext;
        this.dynamicReportLists = dynamicReportLists;
    }

    @Override
    public int getCount() {
        return dynamicReportLists.size();
    }

    @Override
    public Object getItem(int i) {
        return dynamicReportLists.get(i);
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

        viewHolder.tvMessageTime.setText(dynamicReportLists.get(i).getCreateDate());
        viewHolder.tvMessageTitle.setText(dynamicReportLists.get(i).getTitle());

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
