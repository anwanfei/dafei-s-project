package com.junhangxintong.chuanzhangtong.news.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/8/15.
 */

public class OilPriceAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> oilPriceLists;
    private ViewHolder viewHolder;

    public OilPriceAdapter(Context mContext, List<String> oilPriceLists) {
        this.mContext = mContext;
        this.oilPriceLists = oilPriceLists;
    }

    @Override
    public int getCount() {
        return oilPriceLists.size();
    }

    @Override
    public Object getItem(int i) {
        return oilPriceLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {

            view = View.inflate(mContext, R.layout.item_oil_price_layout, null);
            viewHolder = new ViewHolder(view);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (i % 2 != 0 && i != 0) {
            viewHolder.llOilPriceItemRoot.setBackgroundColor(mContext.getResources().getColor(R.color.gray_F5));
        }

        if (i == 0) {
            viewHolder.llOilPriceItemRoot.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        String string = oilPriceLists.get(i);
        viewHolder.tvOilPriceTime.setText(string);

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.tv_oil_price_time)
        TextView tvOilPriceTime;
        @BindView(R.id.tv_oil_price_of_butelun)
        TextView tvOilPriceOfButelun;
        @BindView(R.id.tv_oil_price_of_wti)
        TextView tvOilPriceOfWti;
        @BindView(R.id.tv_oil_price_of_butelun_up_down_quota)
        TextView tvOilPriceOfButelunUpDownQuota;
        @BindView(R.id.tv_oil_price_of_wti_up_down_quota)
        TextView tvOilPriceOfWtiUpDownQuota;
        @BindView(R.id.tv_oil_price_of_butelun_up_down_range)
        TextView tvOilPriceOfButelunUpDownRange;
        @BindView(R.id.tv_oil_price_of_wti_up_down_range)
        TextView tvOilPriceOfWtiUpDownRange;
        @BindView(R.id.ll_oil_price_item_root)
        LinearLayout llOilPriceItemRoot;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
