package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindNonnReportBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.NoonReportInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.utils.RoleEnum;

import org.apache.commons.lang.StringUtils;

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
    @BindView(R.id.tv_minite)
    TextView tvMinite;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.ll_countdown_time)
    LinearLayout llCountdownTime;
    private String id = "";
    private int minute;
    private long second;
    private volatile boolean threadExit = true;
    private NoonReportInfoBean noonReportInfoBean;
    private DynamicRemindNonnReportBean.DataBean.ObjectBean noonReportInfo;

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (minute > 0) {
                        tvMinite.setText("0" + String.valueOf(minute));
                    } else {
                        tvMinite.setText("00");
                    }

                    if (second >= 10) {
                        tvSecond.setText(String.valueOf(second));
                    } else {
                        tvSecond.setText("0" + String.valueOf(second));
                    }

                    //实现秒大于0的时候自减，等于0的时候分减一，秒置90
                    if (second > 0) {
                        second--;
                    } else {
                        //倒计时到头的处理
                        if (minute == 0 && second == 0) {
                            tvMinite.setText("00");
                            tvSecond.setText("00");
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tvMinite.setText("");
                                    llCountdownTime.setVisibility(View.GONE);
                                }
                            }, 1000);

                        }
                        minute--;
                        second = 59;
                    }

            }
            super.handleMessage(msg);
        }
    };
    private Thread myThread;
    private String fromDynamic;
    private int id1;
    private Intent intent;

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
        getNoonReportInfo(true);
    }

    private void getNoonReportInfo(final boolean isShowCountdownTime) {
        intent = getIntent();

        fromDynamic = intent.getStringExtra(Constants.FROM_DYNAMIC);
        if (StringUtils.isNotBlank(fromDynamic)) {
            getNoonReportInfoFromDynamic(isShowCountdownTime);
        } else {
            getNoonReportInfoFromNet(isShowCountdownTime);
        }
    }

    private void getNoonReportInfoFromDynamic(final boolean isShowCountdownTime) {
        String id = intent.getStringExtra(Constants.ID);
        NetUtils.postWithHeader(this, ConstantsUrls.DYNAMIC_DETAILS)
                .addParams(Constants.ID, id)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        DynamicRemindNonnReportBean dynamicRemindNonnReportBean = new Gson().fromJson(response, DynamicRemindNonnReportBean.class);
                        noonReportInfo = dynamicRemindNonnReportBean.getData().getObject();
                        tvShipMessageName.setText(noonReportInfo.getShipName());
                        tvShipTime.setText(noonReportInfo.getCreateDate());
                        tvLatitude.setText(noonReportInfo.getLongitude());
                        tvLongtitude.setText(noonReportInfo.getLatitude());
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

                        int timer = noonReportInfo.getTimer();
                        CountdownTime(isShowCountdownTime, timer);
                    }
                });
    }

    private void getNoonReportInfoFromNet(final boolean isShowCountdownTime) {
        final String shipName = intent.getStringExtra(Constants.SHIP_NAME);
        tvShipMessageName.setText(shipName);
        NetUtils.postWithHeader(this, ConstantsUrls.REPORT_INFO)
                .addParams(Constants.ID, intent.getStringExtra(Constants.ID))
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        noonReportInfoBean = new Gson().fromJson(response, NoonReportInfoBean.class);
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

                        int timer = noonReportInfo.getTimer();
                        CountdownTime(isShowCountdownTime, timer);
                    }
                });
    }

    private void CountdownTime(boolean isShowCountdownTime, int timer) {
        if (isShowCountdownTime) {
            String roleId = CacheUtils.getString(ShipNoonMessageActivity.this, Constants.ROLEID);
            //倒计时
            if (timer / 1000 > 0) {
                if (roleId.equals(String.valueOf(RoleEnum.SHIPMASTER.getCode()))) {
                    llCountdownTime.setVisibility(View.VISIBLE);

                    minute = timer / 60 / 1000;

                    if (timer / 1000 % 60 != 0) {
                        second = timer / 1000 % 60;
                    } else {
                        second = 60;
                    }

                    tvMinite.setText("0" + String.valueOf(minute));
                    if (second >= 10) {
                        tvSecond.setText(String.valueOf(second));
                    } else {
                        tvSecond.setText("0" + String.valueOf(second));
                    }
                    new Thread(new MyThread()).start();
                }
            }
        }
    }

    public class MyThread implements Runnable {      // thread
        @Override
        public void run() {
            while (threadExit) {
                try {
                    Message message = new Message();
                    Thread.sleep(1000);     // sleep 1000ms
                    message.what = 1;
                    if (minute >= 0) {
                        handler.sendMessage(message);
                    } else {
                        threadExit = false;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_zhengwu_message;
    }

    @OnClick({R.id.iv_back, R.id.tv_share, R.id.tv_sign_no_read, R.id.tv_place_top, R.id.tv_edit})
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
            case R.id.tv_edit:
                ModifyNoonReport();
                break;
        }
    }

    private void ModifyNoonReport() {
        if (StringUtils.isNotBlank(fromDynamic)) {
            Intent intent = new Intent(ShipNoonMessageActivity.this, ModifyNoonReportActivity.class);
            intent.putExtra(Constants.NOON_REPORT_INFO, noonReportInfo);
            startActivity(intent);
        } else {
            Intent intent = new Intent(ShipNoonMessageActivity.this, ModifyNoonReportActivity.class);
            intent.putExtra(Constants.NOON_REPORT_INFO, noonReportInfoBean);
            intent.putExtra(Constants.FROM_SHIP, true);
            startActivity(intent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (StringUtils.isNotBlank(fromDynamic)) {
            getNoonReportInfoFromDynamic(false);
        } else {
            getNoonReportInfoFromNet(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        threadExit = false;
    }
}
