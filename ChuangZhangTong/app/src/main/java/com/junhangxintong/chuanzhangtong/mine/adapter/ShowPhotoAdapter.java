package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.bean.UrlBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/9/12.
 */

public class ShowPhotoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UrlBean> urlLists;
    private String domain;

    public ShowPhotoAdapter(Context context, ArrayList<UrlBean> urlLists, String domain) {
        this.context = context;
        this.urlLists = urlLists;
        this.domain = domain;
    }

    @Override
    public int getCount() {
        return urlLists.size();
    }

    @Override
    public Object getItem(int i) {
        return urlLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        //1、创建或获取viewHolder
        ViewHolder holder = null;
        if (convertView == null) {

            //加载条目布局
            convertView = View.inflate(context, R.layout.item_show_photo, null);

            //创建holder对象
            holder = new ViewHolder(convertView);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        String picUrl = urlLists.get(i).getPicUrl();
        String url = domain + picUrl;
        Glide.with(context)
                .load(url)
                .into(holder.ivPhoto);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivPhoto)
        ImageView ivPhoto;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
