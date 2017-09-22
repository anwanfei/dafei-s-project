package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipListBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.MyShipDetailsActivity;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/9/21.
 */

public class MyShipAdapter extends BaseAdapter {

    private Context mContext;
    private List<ShipListBean.DataBean.ArrayBean> myFleetLists;
    private ViewHolder holder;

    private boolean isShowCheckBox;

    public MyShipAdapter(Context mContext, List<ShipListBean.DataBean.ArrayBean> myFleetLists) {
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
            //加载条目布局
            convertView = View.inflate(mContext, R.layout.item_myfleet_crew, null);

            //创建holder对象
            holder = new ViewHolder(convertView);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        String myFleetName = myFleetLists.get(position).getShipName();
        holder.tvItemShipName.setText(myFleetName);


        holder.tvItemShipName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = myFleetLists.get(position).getId();
                String shipName = myFleetLists.get(position).getShipName();
                Intent intent = new Intent(mContext, MyShipDetailsActivity.class);
                intent.putExtra(Constants.ID, String.valueOf(id));
                intent.putExtra(Constants.SHIP_NAME, shipName);
                mContext.startActivity(intent);
            }
        });

        //返回view
        return convertView;
    }

    public void controlCheckboxShow(boolean b) {
        isShowCheckBox = b;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.tv_item_ship_name)
        TextView tvItemShipName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
