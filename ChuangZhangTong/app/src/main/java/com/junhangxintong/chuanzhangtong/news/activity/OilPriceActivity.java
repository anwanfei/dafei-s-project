package com.junhangxintong.chuanzhangtong.news.activity;

import android.content.Context;
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
import com.junhangxintong.chuanzhangtong.news.bean.NewsOilPriceBean;
import com.junhangxintong.chuanzhangtong.news.bean.NewsOilPriceListBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class OilPriceActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.lv_oil_price)
    ListView lvOilPrice;

    List<NewsOilPriceBean> oilPriceLists = new ArrayList<>();

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
                                NewsOilPriceListBean newsOilPriceListBean = new Gson().fromJson(response, NewsOilPriceListBean.class);
                                List<NewsOilPriceListBean.DataBean.ArrayBean> newsOilPriceLists = newsOilPriceListBean.getData().getArray();

                                for (int i = 0; i < newsOilPriceLists.size(); i += 2) {
                                    NewsOilPriceBean oilPriceBean = new NewsOilPriceBean();

                                    oilPriceBean.setDate(newsOilPriceLists.get(i).getCreateDate());

                                    oilPriceBean.setBulunteDollar(newsOilPriceLists.get(i).getOilPrice() + "");
                                    oilPriceBean.setBuluntepDownRange(newsOilPriceLists.get(i).getOilChg() + "");
                                    oilPriceBean.setBulunteUpDownQuato(newsOilPriceLists.get(i).getOilFluctuation() + "");

                                    oilPriceBean.setWTIDollar(newsOilPriceLists.get(i + 1).getOilPrice() + "");
                                    oilPriceBean.setWTIUpDownRange(newsOilPriceLists.get(i + 1).getOilChg() + "");
                                    oilPriceBean.setWTIUpDownQuato(newsOilPriceLists.get(i + 1).getOilFluctuation() + "");


                                    oilPriceLists.add(oilPriceBean);
                                }

                                OilPriceAdapter oilPriceAdapter = new OilPriceAdapter(OilPriceActivity.this, oilPriceLists);
                                lvOilPrice.setAdapter(oilPriceAdapter);

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(OilPriceActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(OilPriceActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else {
                                Toast.makeText(OilPriceActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

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
