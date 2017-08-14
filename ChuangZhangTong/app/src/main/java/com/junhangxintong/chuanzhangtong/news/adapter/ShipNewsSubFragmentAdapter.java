package com.junhangxintong.chuanzhangtong.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/8/10.
 */

public class ShipNewsSubFragmentAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> messages;
    private ViewHolder viewHolder;

    public ShipNewsSubFragmentAdapter(Context mContext, List<String> messages) {
        this.mContext = mContext;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
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

        viewHolder.tvMessageTitle.setText(messages.get(i));

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
}
