package com.junhangxintong.chuanzhangtong.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.news.adapter.OilPriceAdapter;
import com.junhangxintong.chuanzhangtong.news.bean.NewsListBean;
import com.junhangxintong.chuanzhangtong.news.bean.OilPriceBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

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

        Intent intent = getIntent();
        int id = intent.getIntExtra(Constants.ID, 10);

        NetUtils.postWithHeader(this, ConstantsUrls.QUERY_NEWS_DETAILS)
                .addParams(Constants.ID, String.valueOf(id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(OilPriceActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(OilPriceActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                NewsListBean newsListBean = new Gson().fromJson(response, NewsListBean.class);
//                                newsLists = newsListBean.getData().getArray();

//                                NewsListsAdapter newsListsAdapter = new NewsListsAdapter(OilPriceActivity.this, newsLists);
//                                lvMessage.setAdapter(newsListsAdapter);

                            } else if (code.equals("601")) {
                                startActivity(new Intent(OilPriceActivity.this, LoginRegisterActivity.class));
                            } else {
                                Toast.makeText(OilPriceActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


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
