package com.junhangxintong.chuanzhangtong.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.news.bean.NewsConventionDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class WeatherDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_weather_name)
    TextView tvWeatherName;
    @BindView(R.id.tv_weather_name_field)
    TextView tvWeatherNameField;
    @BindView(R.id.tv_weather_wenhao)
    TextView tvWeatherWenhao;
    @BindView(R.id.tv_weather_wenhao_issue)
    TextView tvWeatherWenhaoIssue;
    @BindView(R.id.tv_weather_suoyinhao_field)
    TextView tvWeatherSuoyinhaoField;
    @BindView(R.id.tv_weather_suoyinhao)
    TextView tvWeatherSuoyinhao;
    @BindView(R.id.tv_weather_suoyinhao_time)
    TextView tvWeatherSuoyinhaoTime;
    @BindView(R.id.tv_weather_content)
    TextView tvWeatherContent;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.weather_center));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra(Constants.ID, 1);

        NetUtils.postWithHeader(this, ConstantsUrls.QUERY_NEWS_DETAILS)
                .addParams(Constants.ID, String.valueOf(id))
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        NewsConventionDetailsBean newsConventionDetailsBean = new Gson().fromJson(response, NewsConventionDetailsBean.class);
                        String context = newsConventionDetailsBean.getData().getObject().getContext();
                        String createAuthor = newsConventionDetailsBean.getData().getObject().getCreateAuthor();
                        String createDate = newsConventionDetailsBean.getData().getObject().getCreateDate();
                        String title = newsConventionDetailsBean.getData().getObject().getTitle();

                        tvWeatherName.setText(title);
                        tvWeatherWenhaoIssue.setText(createAuthor);
                        tvWeatherSuoyinhaoTime.setText(createDate);
                        tvWeatherContent.setText(context);
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_weather_details;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
