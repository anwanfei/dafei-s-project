package com.junhangxintong.chuangzhangtong.shipposition.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ShipNoonMessageActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_titlebar)

    LinearLayout llTitlebar;
    @BindView(R.id.tv_ship_message_name)
    TextView tvShipMessageName;
    /*@BindView(R.id.tv_ship_num)
    TextView tvShipNum;*/
    @BindView(R.id.tv_ship_time)
    TextView tvShipTime;
    @BindView(R.id.tv_latitude)
    TextView tvLatitude;
    @BindView(R.id.tv_ship_direction)
    TextView tvShipDirection;
    @BindView(R.id.tv_longtitude)
    TextView tvLongtitude;
    @BindView(R.id.tv_ship_speed)
    TextView tvShipSpeed;
    @BindView(R.id.tv_the_ship_heavy_oil)
    TextView tvTheShipHeavyOil;
    @BindView(R.id.tv_ship_draft)
    TextView tvShipDraft;
    @BindView(R.id.tv_the_ship_light_oil)
    TextView tvTheShipLightOil;
    @BindView(R.id.tv_the_ship_frashwater)
    TextView tvTheShipFrashwater;
    @BindView(R.id.tv_light_oil_consume)
    TextView tvLightOilConsume;
    @BindView(R.id.tv_frashwater_consume)
    TextView tvFrashwaterConsume;
    @BindView(R.id.tv_average_speed_between_24_hours)
    TextView tvAverageSpeedBetween24Hours;
    @BindView(R.id.tv_re_arrival_time)
    TextView tvReArrivalTime;
    @BindView(R.id.tv_pressure)
    TextView tvPressure;
    @BindView(R.id.tv_pitch)
    TextView tvPitch;
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_weather)
    TextView tvWeather;
    @BindView(R.id.tv_wave_level)
    TextView tvWaveLevel;
    @BindView(R.id.tv_wind_direction)
    TextView tvWindDirection;
    @BindView(R.id.tv_consume)
    TextView tvConsume;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_sign_no_read)
    TextView tvSignNoRead;
    @BindView(R.id.tv_place_top)
    TextView tvPlaceTop;
    @BindView(R.id.ll_zhengwu_bottom)
    LinearLayout llZhengwuBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.noon_message));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_zhengwu_message;
    }

    @OnClick({R.id.iv_back, R.id.tv_share, R.id.tv_sign_no_read, R.id.tv_place_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_sign_no_read:
                break;
            case R.id.tv_place_top:
                break;
        }
    }
}
