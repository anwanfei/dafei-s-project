package com.junhangxintong.chuanzhangtong.mine.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.activity.CrewCertificateListsActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.CrewDetailsActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/7/14.
 */

public class MyCrewAdapter extends BaseAdapter implements View.OnClickListener {

    private Context mContext;
    private List<CrewBean> myCrewLists;
    private ViewHolder holder;

    private boolean isShowCheckBox;

    public MyCrewAdapter(Context mContext, List<CrewBean> myFleetLists) {
        this.mContext = mContext;
        this.myCrewLists = myFleetLists;
    }

    @Override
    public int getCount() {
        return myCrewLists.size();
    }

    @Override
    public Object getItem(int i) {
        return myCrewLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        //1、创建或获取viewHolder
        if (convertView == null) {
            //加载条目布局
            convertView = View.inflate(mContext, R.layout.item_mycrew, null);
            //创建holder对象
            holder = new ViewHolder(convertView);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        String myCrewName = myCrewLists.get(position).getCrewName();
        String myCrewDuty = myCrewLists.get(position).getDuty();
        String myCrewJobNum = myCrewLists.get(position).getJobNum();
        String myCrewNationnality = myCrewLists.get(position).getNationality();
        holder.tvItemCrewName.setText(myCrewName);
        holder.tvItemCrewDuty.setText(myCrewDuty);
        holder.tvItemCrewJobNum.setText(myCrewJobNum);
        holder.tvItemCrewNationality.setText(myCrewNationnality);
        boolean checkbox = myCrewLists.get(position).isCheckbox();
        if (checkbox) {
            holder.cbShip.setChecked(true);
        } else {
            holder.cbShip.setChecked(false);
        }

        holder.tvCertificate.setOnClickListener(this);
        holder.llCrewDetails.setOnClickListener(this);

        holder.cbShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCb = myCrewLists.get(position).isCheckbox();
                if (isCb) {
                    myCrewLists.get(position).setCheckbox(false);
                } else {
                    myCrewLists.get(position).setCheckbox(true);
                }
            }
        });

        //确定CheckBox的显示与否
        if (isShowCheckBox) {
            holder.cbShip.setVisibility(View.VISIBLE);
            holder.tvCertificate.setVisibility(View.GONE);
        } else {
            holder.cbShip.setVisibility(View.GONE);
            holder.tvCertificate.setVisibility(View.VISIBLE);
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
                Intent intent = new Intent(mContext, CrewCertificateListsActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.cb_ship:
                break;
            case R.id.ll_crew_details:
                mContext.startActivity(new Intent(mContext, CrewDetailsActivity.class));
                break;
        }
    }

    public void controlCheckboxShow(boolean b) {
        isShowCheckBox = b;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @BindView(R.id.cb_ship)
        CheckBox cbShip;
        @BindView(R.id.tv_item_crew_duty)
        TextView tvItemCrewDuty;
        @BindView(R.id.tv_item_crew_name)
        TextView tvItemCrewName;
        @BindView(R.id.tv_item_crew_job_num)
        TextView tvItemCrewJobNum;
        @BindView(R.id.tv_item_crew_nationality)
        TextView tvItemCrewNationality;
        @BindView(R.id.ll_crew_details)
        LinearLayout llCrewDetails;
        @BindView(R.id.tv_certificate)
        TextView tvCertificate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
