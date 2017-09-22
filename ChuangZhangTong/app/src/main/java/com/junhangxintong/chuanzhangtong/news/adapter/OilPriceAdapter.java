package com.junhangxintong.chuanzhangtong.news.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.news.bean.NewsOilPriceBean;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anwanfei on 2017/8/15.
 */

public class OilPriceAdapter extends BaseAdapter {

    private Context mContext;
    private List<NewsOilPriceBean> oilPriceLists;
    private ViewHolder viewHolder;

    public OilPriceAdapter(Context mContext, List<NewsOilPriceBean> oilPriceLists) {
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
        }else {
            viewHolder.llOilPriceItemRoot.setBackgroundColor(mContext.getResources().getColor(R.color.white99));
        }

        if (i == 0) {
            viewHolder.llOilPriceItemRoot.setBackgroundColor(mContext.getResources().getColor(R.color.white99));
        }

        NewsOilPriceBean oilPriceBean = oilPriceLists.get(i);
        String date = oilPriceBean.getDate();
        String substringDate = date.substring(5);
        viewHolder.tvOilPriceTime.setText(substringDate);

        viewHolder.tvOilPriceOfButelun.setText(getTwoDecimalPlacesNoPercent(oilPriceBean.getBulunteDollar()));

        viewHolder.tvOilPriceOfWti.setText(getTwoDecimalPlacesNoPercent(oilPriceBean.getWTIDollar()));

        String bulunteUpDownQuato = oilPriceBean.getBulunteUpDownQuato();
        double doubleBulunteUpDownQuato = Double.parseDouble(bulunteUpDownQuato);
        if (doubleBulunteUpDownQuato > 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(doubleBulunteUpDownQuato / 100);
            viewHolder.tvOilPriceOfButelunUpDownQuota.setText(format);
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(doubleBulunteUpDownQuato / 100);
            viewHolder.ivOilPriceOfButelunUpDownQuota.setImageResource(R.drawable.iv_bottom_arrow);
            String substringFormat = format.substring(1);
            viewHolder.tvOilPriceOfButelunUpDownQuota.setText(substringFormat);
        }

        viewHolder.tvOilPriceOfWtiUpDownQuota.setText(getTwoDecimalPlacesWithPercent(oilPriceBean.getWTIUpDownQuato(), viewHolder.ivOilPriceOfWtiUpDownQuota));

        viewHolder.tvOilPriceOfButelunUpDownRange.setText("0" + getTwoDecimalPlacesWithPercent(oilPriceBean.getBuluntepDownRange(), viewHolder.ivOilPriceOfButelunUpDownRange) + "%");

        viewHolder.tvOilPriceOfWtiUpDownRange.setText("0" + getTwoDecimalPlacesWithPercent(oilPriceBean.getWTIUpDownRange(), viewHolder.ivOilPriceOfWtiUpDownRange) + "%");

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
        @BindView(R.id.iv_oil_price_of_butelun_up_down_quota)
        ImageView ivOilPriceOfButelunUpDownQuota;
        @BindView(R.id.iv_oil_price_of_wti_up_down_quota)
        ImageView ivOilPriceOfWtiUpDownQuota;
        @BindView(R.id.iv_oil_price_of_butelun_up_down_range)
        ImageView ivOilPriceOfButelunUpDownRange;
        @BindView(R.id.iv_oil_price_of_wti_up_down_range)
        ImageView ivOilPriceOfWtiUpDownRange;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private String getTwoDecimalPlacesNoPercent(String num) {
        double toDouble = Double.parseDouble(num);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String format = decimalFormat.format(toDouble / 100);

        return format;
    }

    private String getTwoDecimalPlacesWithPercent(String num, ImageView imageView) {
        double toDouble = Double.parseDouble(num);
        if (toDouble > 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(toDouble / 100);
            return format;
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(toDouble / 100);
            imageView.setImageResource(R.drawable.iv_bottom_arrow);
            String substringFormat = format.substring(1);
            return substringFormat;
        }
    }
}
