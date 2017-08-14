package com.junhangxintong.chuanzhangtong.shipposition.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.shipposition.activity.OtherShipDetailsActivity;
import com.junhangxintong.chuanzhangtong.shipposition.bean.ShipDetailsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/8/4.
 */

public class SearchResultAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShipDetailsBean> shipDetailsBeanList;

    public SearchResultAdapter(Context mContext, List<ShipDetailsBean> shipDetailsBeanList) {
        this.mContext = mContext;
        this.shipDetailsBeanList = shipDetailsBeanList;
    }

    @Override
    public int getCount() {
        return shipDetailsBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return shipDetailsBeanList.get(i);
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
            //创建holder对象
            holder = new ViewHolder();
            //加载条目布局
            convertView = View.inflate(mContext, R.layout.item_ship_position_search, null);

            //找控件
            holder.tvSearchShipName = (TextView) convertView.findViewById(R.id.tv_search_ship_name);
            holder.tvSearchShipMmsi = (TextView) convertView.findViewById(R.id.tv_search_ship_mmsi);
            holder.tvSearchShipDetails = (TextView) convertView.findViewById(R.id.tv_search_ship_details);
            holder.tvSearchFollow = (TextView) convertView.findViewById(R.id.tv_search_follow);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        ShipDetailsBean shipDetailsBean = shipDetailsBeanList.get(i);
        String mmsi = shipDetailsBean.getMmsi();
        String shipName = shipDetailsBean.getShipName();

        holder.tvSearchShipName.setText(shipName);
        holder.tvSearchShipMmsi.setText(mContext.getResources().getString(R.string.MMSI) + mmsi);

        holder.tvSearchShipDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, OtherShipDetailsActivity.class));
            }
        });
        final ViewHolder finalHolder = holder;
        holder.tvSearchFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "关注成功", Toast.LENGTH_SHORT).show();
                finalHolder.tvSearchFollow.setText(mContext.getResources().getString(R.string.followed));
                finalHolder.tvSearchFollow.setBackgroundResource(R.drawable.tv_frame_gray_bg);
                finalHolder.tvSearchFollow.setTextColor(mContext.getResources().getColor(R.color.gray_identity));
            }
        });

        //返回view
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_search_ship_name)
        TextView tvSearchShipName;
        @BindView(R.id.tv_search_ship_mmsi)
        TextView tvSearchShipMmsi;
        @BindView(R.id.tv_search_update_time)
        TextView tvSearchUpdateTime;
        @BindView(R.id.tv_search_ship_details)
        TextView tvSearchShipDetails;
        @BindView(R.id.tv_search_follow)
        TextView tvSearchFollow;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        ViewHolder() {
        }
    }
}
