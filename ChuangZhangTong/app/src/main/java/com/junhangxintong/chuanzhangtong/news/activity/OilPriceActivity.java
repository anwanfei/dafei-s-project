package com.junhangxintong.chuanzhangtong.news.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.news.adapter.OilPriceAdapter;
import com.junhangxintong.chuanzhangtong.news.bean.OilPriceBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class OilPriceActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_oil_price)
    ListView lvOilPrice;

    List<OilPriceBean> oilPriceLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.oil_price));
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 5; i++) {
            OilPriceBean oilPriceBean = new OilPriceBean();
            oilPriceBean.setDate("9.1" + i);
            oilPriceBean.setBulunteDollar("48.5" + i);
            oilPriceBean.setWTIDollar("52.1" + i);
            oilPriceBean.setBuluntepDownRange("1.0" + i + "%");
            oilPriceBean.setWTIUpDownRange("2.0" + i + "%");
            oilPriceBean.setBulunteUpDownQuato("0.4" + i);
            oilPriceBean.setWTIUpDownQuato("0.5" + i);
            oilPriceLists.add(oilPriceBean);
        }

        OilPriceAdapter oilPriceAdapter = new OilPriceAdapter(this, oilPriceLists);
        lvOilPrice.setAdapter(oilPriceAdapter);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_oil_price;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
