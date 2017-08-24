package com.junhangxintong.chuanzhangtong.shipposition.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by anwanfei on 2017/8/9.
 */

public class AdditiveCrewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<CrewBean> crews;
    private ViewHolder holder;
    public int choosedNums = 0;
    private TextView tvChoosedCrewNum;


    public AdditiveCrewAdapter(Context mContext, ArrayList<CrewBean> crews, TextView tvChoosedCrewNum) {
        this.mContext = mContext;
        this.crews = crews;
        this.tvChoosedCrewNum = tvChoosedCrewNum;
    }

    @Override
    public int getCount() {
        return crews.size();
    }

    @Override
    public Object getItem(int i) {
        return crews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        if (view == null) {

            view = View.inflate(mContext, R.layout.item_additive_crew, null);
            holder = new ViewHolder(view);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        boolean checkbox = crews.get(i).isCheckbox();
        if (checkbox) {
            holder.cbShip.setChecked(true);
        } else {
            holder.cbShip.setChecked(false);
        }

        holder.cbShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCb = crews.get(i).isCheckbox();
                if (isCb) {
                    crews.get(i).setCheckbox(false);
                    --choosedNums;
                } else {
                    crews.get(i).setCheckbox(true);
                    ++choosedNums;
                }
                tvChoosedCrewNum.setText("已选择" + choosedNums + "人");
            }
        });


        return view;
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
