package com.atguigu.p2p.common;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by shkstart on 2016/8/16 0016.
 */
public abstract class MyBaseAdapter2<T> extends BaseAdapter {
    public List<T> data;

    public MyBaseAdapter2(List<T> data){
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

    //要实现的操作：①加载具体的item的布局给convertView ② 将集合数据装配给item布局对应的convertView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T t = data.get(position);
        ViewHolder holder;
        if(convertView == null){
            convertView = initView();//初始化convertView
            holder = new ViewHolder(convertView);//初始化ViewHolder,同时作为tag设置给convertView
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //装配数据
        setData(convertView,t);

        //将装配好的convertView返回
        return convertView;
    }

    protected abstract void setData(View convertView, T t);

    protected abstract View initView();


    class ViewHolder{
        public ViewHolder(View rootView){
            rootView.setTag(this);
        }
    }

}
