package com.junhangxintong.chuanzhangtong.news.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.news.adapter.OilPriceAdapter;

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

    List<String> oilPriceLists = new ArrayList<>();

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
            oilPriceLists.add("9.1" + i);
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
