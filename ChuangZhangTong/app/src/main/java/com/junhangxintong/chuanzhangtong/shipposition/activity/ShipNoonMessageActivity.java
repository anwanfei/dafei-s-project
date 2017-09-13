package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindNonnReportBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.shipposition.bean.NoonReportInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

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
        Intent intent = getIntent();

        String fromDynamic = intent.getStringExtra(Constants.FROM_DYNAMIC);
        if (fromDynamic != null) {
            DynamicRemindNonnReportBean dynamicRemindNonnReportBean = (DynamicRemindNonnReportBean) intent.getSerializableExtra(Constants.DYNAMIC_REPORT);
            DynamicRemindNonnReportBean.DataBean.ObjectBean noonReportInfo = dynamicRemindNonnReportBean.getData().getObject();
            tvShipMessageName.setText(noonReportInfo.getShipName());
            tvShipTime.setText(noonReportInfo.getCreateDate());
            tvLatitude.setText(noonReportInfo.getLatitude());
            tvLongtitude.setText(noonReportInfo.getLongitude());
            tvShipDirection.setText(noonReportInfo.getCourse());
            tvShipSpeed.setText(noonReportInfo.getCurrShipSpeed());
            tvTheShipHeavyOil.setText(noonReportInfo.getShipHeavyOil());
            tvShipDraft.setText(noonReportInfo.getShipForwardDraft());
            tvTheShipLightOil.setText(noonReportInfo.getShipLightOil());
            tvTheShipFrashwater.setText(noonReportInfo.getShipFreshwater());
            tvLightOilConsume.setText(noonReportInfo.getLightOilConsumption());
            tvFrashwaterConsume.setText(noonReportInfo.getFreshwaterConsumption());
            tvShipSpeed.setText(noonReportInfo.getAvgSpeed());
            tvReArrivalTime.setText(noonReportInfo.getExpectArrivalDate());
            tvPressure.setText(noonReportInfo.getPressure());
            tvPitch.setText(noonReportInfo.getSnailRange());
            tvTemperature.setText(noonReportInfo.getTemperature());
            tvWeather.setText(noonReportInfo.getWeather());
            tvWaveLevel.setText(noonReportInfo.getWaveLevel());
            tvWindDirection.setText(noonReportInfo.getWindDirection());
            tvConsume.setText(noonReportInfo.getConsume());
            tvRemark.setText(noonReportInfo.getRemark());
        } else {
            String id = intent.getStringExtra(Constants.ID);
            final String shipName = intent.getStringExtra(Constants.SHIP_NAME);
            tvShipMessageName.setText(shipName);

            NetUtils.postWithHeader(this, ConstantsUrls.REPORT_INFO)
                    .addParams(Constants.ID, id)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ShipNoonMessageActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(ShipNoonMessageActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                                String message = netServiceErrort.getMessage();
                                String code = netServiceErrort.getCode();
                                if (code.equals("200")) {
                                    NoonReportInfoBean noonReportInfoBean = new Gson().fromJson(response, NoonReportInfoBean.class);
                                    NoonReportInfoBean.DataBean.ObjectBean noonReportInfo = noonReportInfoBean.getData().getObject();
                                    tvShipTime.setText(noonReportInfo.getCreateDate());
                                    tvLatitude.setText(noonReportInfo.getLatitude());
                                    tvLongtitude.setText(noonReportInfo.getLongitude());
                                    tvShipDirection.setText(noonReportInfo.getCourse());
                                    tvShipSpeed.setText(noonReportInfo.getCurrShipSpeed());
                                    tvTheShipHeavyOil.setText(noonReportInfo.getShipHeavyOil());
                                    tvShipDraft.setText(noonReportInfo.getShipForwardDraft());
                                    tvTheShipLightOil.setText(noonReportInfo.getShipLightOil());
                                    tvTheShipFrashwater.setText(noonReportInfo.getShipFreshwater());
                                    tvLightOilConsume.setText(noonReportInfo.getLightOilConsumption());
                                    tvFrashwaterConsume.setText(noonReportInfo.getFreshwaterConsumption());
                                    tvShipSpeed.setText(noonReportInfo.getAvgSpeed());
                                    tvReArrivalTime.setText(noonReportInfo.getExpectArrivalDate());
                                    tvPressure.setText(noonReportInfo.getPressure());
                                    tvPitch.setText(noonReportInfo.getSnailRange());
                                    tvTemperature.setText(noonReportInfo.getTemperature());
                                    tvWeather.setText(noonReportInfo.getWeather());
                                    tvWaveLevel.setText(noonReportInfo.getWaveLevel());
                                    tvWindDirection.setText(noonReportInfo.getWindDirection());
                                    tvConsume.setText(noonReportInfo.getConsume());
                                    tvRemark.setText(noonReportInfo.getRemark());

                                } else if (code.equals("601")) {
                                    //清除了sp存储
                                    getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                    //保存获取权限的sp
                                    CacheUtils.putBoolean(ShipNoonMessageActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                    startActivity(new Intent(ShipNoonMessageActivity.this, LoginRegisterActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(ShipNoonMessageActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }
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
