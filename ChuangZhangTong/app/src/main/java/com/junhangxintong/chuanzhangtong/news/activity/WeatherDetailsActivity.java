package com.junhangxintong.chuanzhangtong.news.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.news.bean.NewsConventionDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

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
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(WeatherDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(WeatherDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrortBean = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrortBean.getMessage();
                            String code = netServiceErrortBean.getCode();
                            if (code.equals("200")) {
                                NewsConventionDetailsBean newsConventionDetailsBean = new Gson().fromJson(response, NewsConventionDetailsBean.class);
                                String context = newsConventionDetailsBean.getData().getObject().getContext();
                                String createAuthor = newsConventionDetailsBean.getData().getObject().getCreateAuthor();
                                String createDate = newsConventionDetailsBean.getData().getObject().getCreateDate();
                                String title = newsConventionDetailsBean.getData().getObject().getTitle();

                                tvWeatherName.setText(title);
                                tvWeatherWenhaoIssue.setText(createAuthor);
                                tvWeatherSuoyinhaoTime.setText(createDate);
                                tvWeatherContent.setText(context);
                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(WeatherDetailsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(WeatherDetailsActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else {
                                Toast.makeText(WeatherDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
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
