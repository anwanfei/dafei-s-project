package com.atguigu.p2p.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by shkstart on 2016/8/16 0016.
 */
public abstract class MyBaseAdapter1<T> extends BaseAdapter {
    public List<T> data;

    public MyBaseAdapter1(List<T> data){
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return myGetView(position,convertView,parent);
    }

    public abstract View myGetView(int position, View convertView, ViewGroup parent);
}
