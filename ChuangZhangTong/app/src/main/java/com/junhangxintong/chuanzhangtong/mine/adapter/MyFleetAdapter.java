package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.activity.CertificateListsActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.MyFleetBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/7/14.
 */

public class MyFleetAdapter extends BaseAdapter implements View.OnClickListener {

    private Context mContext;
    private List<MyFleetBean> myFleetLists;
    private ViewHolder holder;

    private boolean isShowCheckBox;

    public MyFleetAdapter(Context mContext, List<MyFleetBean> myFleetLists) {
        this.mContext = mContext;
        this.myFleetLists = myFleetLists;
    }

    @Override
    public int getCount() {
        return myFleetLists.size();
    }

    @Override
    public Object getItem(int i) {
        return myFleetLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        //1、创建或获取viewHolder
        holder = null;
        if (convertView == null) {
            //创建holder对象
            holder = new ViewHolder();
            //加载条目布局
            convertView = View.inflate(mContext, R.layout.item_myfleet, null);

            //找控件
            holder.tvCertificate = (TextView) convertView.findViewById(R.id.tv_certificate);
            holder.tvItemShipName = (TextView) convertView.findViewById(R.id.tv_item_ship_name);
            holder.cbShip = (CheckBox) convertView.findViewById(R.id.cb_ship);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        String myFleetName = myFleetLists.get(position).getShipName();
        holder.tvItemShipName.setText(myFleetName);
        boolean checkbox = myFleetLists.get(position).isCheckbox();
        if (checkbox) {
            holder.cbShip.setChecked(true);
        } else {
            holder.cbShip.setChecked(false);
        }

        holder.tvCertificate.setOnClickListener(this);
        holder.tvItemShipName.setOnClickListener(this);

        holder.cbShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCb = myFleetLists.get(position).isCheckbox();
                if (isCb) {
                    myFleetLists.get(position).setCheckbox(false);
                } else {
                    myFleetLists.get(position).setCheckbox(true);
                }
            }
        });

        //确定CheckBox的显示与否
        if (isShowCheckBox) {
            holder.cbShip.setVisibility(View.VISIBLE);
            holder.tvCertificate.setVisibility(View.GONE);
        } else {
            holder.cbShip.setVisibility(View.GONE);
            holder.tvCertificate.setVisibility(View.VISIBLE
            );
        }

        //返回view
        return convertView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_item_ship_name:
                break;
            case R.id.tv_certificate:
                mContext.startActivity(new Intent(mContext, CertificateListsActivity.class));
                break;
            case R.id.cb_ship:
                break;
        }
    }

    static class ViewHolder {
        @BindView(R.id.tv_item_ship_name)
        TextView tvItemShipName;
        @BindView(R.id.tv_certificate)
        TextView tvCertificate;
        @BindView(R.id.cb_ship)
        CheckBox cbShip;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        ViewHolder() {
        }
    }

    public void controlCheckboxShow(boolean b) {
        isShowCheckBox = b;
        notifyDataSetChanged();
    }
}
