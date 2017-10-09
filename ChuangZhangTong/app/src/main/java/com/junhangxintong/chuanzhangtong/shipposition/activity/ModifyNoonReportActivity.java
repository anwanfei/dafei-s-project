package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindNonnReportBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.AddNoonReportBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.NoonReportInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyNoonReportActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_latitude_with_colon)
    EditText etInputLatitudeWithColon;
    @BindView(R.id.et_input_longitude)
    EditText etInputLongitude;
    @BindView(R.id.et_input_ship_direction)
    EditText etInputShipDirection;
    @BindView(R.id.et_input_current_ship_speed)
    EditText etInputCurrentShipSpeed;
    @BindView(R.id.et_input_the_ship_heavy_oil)
    EditText etInputTheShipHeavyOil;
    @BindView(R.id.et_input_the_ship_light_oil)
    EditText etInputTheShipLightOil;
    @BindView(R.id.et_input_ship_draft)
    EditText etInputShipDraft;
    @BindView(R.id.et_input_the_ship_frashwater)
    EditText etInputTheShipFrashwater;
    @BindView(R.id.et_input_light_oil_consume)
    EditText etInputLightOilConsume;
    @BindView(R.id.frashwater_consume)
    EditText frashwaterConsume;
    @BindView(R.id.et_input_average_speed_between_24_hours)
    EditText etInputAverageSpeedBetween24Hours;
    @BindView(R.id.tv_input_re_arrival_time)
    TextView tvInputReArrivalTime;
    @BindView(R.id.et_input_pressure)
    EditText etInputPressure;
    @BindView(R.id.et_input_temperature)
    EditText etInputTemperature;
    @BindView(R.id.et_input_wave_level)
    EditText etInputWaveLevel;
    @BindView(R.id.et_input_pitch)
    EditText etInputPitch;
    @BindView(R.id.et_input_weather)
    EditText etInputWeather;
    @BindView(R.id.et_input_wind_direction)
    EditText etInputWindDirection;
    @BindView(R.id.et_input_consume)
    EditText etInputConsume;
    @BindView(R.id.et_input_remark)
    EditText etInputRemark;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.ll_commit)
    LinearLayout llCommit;
    private NoonReportInfoBean noonReportInfoBean;
    private String reArrivalTiem = "";
    private AlertDialog show;
    private String userId;
    private int id;
    private DynamicRemindNonnReportBean.DataBean.ObjectBean noonReportInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.modify_noon_message));
    }

    @Override
    protected void initData() {
        userId = CacheUtils.getString(this, Constants.ID);

        Intent intent = getIntent();
        boolean fromShip = intent.getBooleanExtra(Constants.FROM_SHIP, false);
        if (fromShip) {
            noonReportInfoBean = (NoonReportInfoBean) intent.getSerializableExtra(Constants.NOON_REPORT_INFO);
            NoonReportInfoBean.DataBean.ObjectBean noonReportInfo = noonReportInfoBean.getData().getObject();
            etInputLongitude.setText(noonReportInfo.getLatitude());
            etInputLatitudeWithColon.setText(noonReportInfo.getLongitude());
            etInputShipDirection.setText(noonReportInfo.getCourse());
            etInputCurrentShipSpeed.setText(noonReportInfo.getCurrShipSpeed());
            etInputTheShipHeavyOil.setText(noonReportInfo.getShipHeavyOil());
            etInputTheShipLightOil.setText(noonReportInfo.getShipForwardDraft());
            etInputShipDraft.setText(noonReportInfo.getShipLightOil());
            etInputTheShipFrashwater.setText(noonReportInfo.getShipFreshwater());
            etInputLightOilConsume.setText(noonReportInfo.getLightOilConsumption());
            frashwaterConsume.setText(noonReportInfo.getFreshwaterConsumption());
            etInputAverageSpeedBetween24Hours.setText(noonReportInfo.getAvgSpeed());
            tvInputReArrivalTime.setText(noonReportInfo.getExpectArrivalDate());
            etInputPressure.setText(noonReportInfo.getPressure());
            etInputPitch.setText(noonReportInfo.getSnailRange());
            etInputTemperature.setText(noonReportInfo.getTemperature());
            etInputWeather.setText(noonReportInfo.getWeather());
            etInputWaveLevel.setText(noonReportInfo.getWaveLevel());
            etInputWindDirection.setText(noonReportInfo.getWindDirection());
            etInputConsume.setText(noonReportInfo.getConsume());
            etInputRemark.setText(noonReportInfo.getRemark());

            id = noonReportInfo.getId();
        } else {
            noonReportInfo = (DynamicRemindNonnReportBean.DataBean.ObjectBean) intent.getSerializableExtra(Constants.NOON_REPORT_INFO);
            etInputLongitude.setText(noonReportInfo.getLatitude());
            etInputLatitudeWithColon.setText(noonReportInfo.getLongitude());
            etInputShipDirection.setText(noonReportInfo.getCourse());
            etInputCurrentShipSpeed.setText(noonReportInfo.getCurrShipSpeed());
            etInputTheShipHeavyOil.setText(noonReportInfo.getShipHeavyOil());
            etInputTheShipLightOil.setText(noonReportInfo.getShipForwardDraft());
            etInputShipDraft.setText(noonReportInfo.getShipLightOil());
            etInputTheShipFrashwater.setText(noonReportInfo.getShipFreshwater());
            etInputLightOilConsume.setText(noonReportInfo.getLightOilConsumption());
            frashwaterConsume.setText(noonReportInfo.getFreshwaterConsumption());
            etInputAverageSpeedBetween24Hours.setText(noonReportInfo.getAvgSpeed());
            tvInputReArrivalTime.setText(noonReportInfo.getExpectArrivalDate());
            etInputPressure.setText(noonReportInfo.getPressure());
            etInputPitch.setText(noonReportInfo.getSnailRange());
            etInputTemperature.setText(noonReportInfo.getTemperature());
            etInputWeather.setText(noonReportInfo.getWeather());
            etInputWaveLevel.setText(noonReportInfo.getWaveLevel());
            etInputWindDirection.setText(noonReportInfo.getWindDirection());
            etInputConsume.setText(noonReportInfo.getConsume());
            etInputRemark.setText(noonReportInfo.getRemark());

            id = noonReportInfo.getId();
        }
    }

    private void netCommitReport() {
        String longitude = etInputLongitude.getText().toString();
        String latitude = etInputLatitudeWithColon.getText().toString();
        String shipDirection = etInputShipDirection.getText().toString();
        String curSpeed = etInputCurrentShipSpeed.getText().toString();
        String heavyOil = etInputTheShipHeavyOil.getText().toString();
        String lightOil = etInputTheShipLightOil.getText().toString();
        String shipDraft = etInputShipDraft.getText().toString();
        String freshWater = etInputTheShipFrashwater.getText().toString();
        String lightOilConsume = etInputLightOilConsume.getText().toString();
        String freshWaterConsume = frashwaterConsume.getText().toString();
        String avgSpeed = etInputAverageSpeedBetween24Hours.getText().toString();
        reArrivalTiem = tvInputReArrivalTime.getText().toString();
        String pressure = etInputPressure.getText().toString();
        String temprature = etInputTemperature.getText().toString();
        String waveLevel = etInputWaveLevel.getText().toString();
        String pitch = etInputPitch.getText().toString();
        String weather = etInputWeather.getText().toString();
        String windDirection = etInputWindDirection.getText().toString();
        String consume = etInputConsume.getText().toString();
        String remark = etInputRemark.getText().toString();

        AddNoonReportBean.ReportJsonDataBean reportJsonData = new AddNoonReportBean.ReportJsonDataBean();
        reportJsonData.setLatitude(latitude);
        reportJsonData.setLongitude(longitude);
        reportJsonData.setCourse(shipDirection);
        reportJsonData.setCurrShipSpeed(curSpeed);
        reportJsonData.setShipHeavyOil(heavyOil);
        reportJsonData.setShipLightOil(lightOil);
        reportJsonData.setShipForwardDraft(shipDraft);
        reportJsonData.setShipFreshwater(freshWater);
        reportJsonData.setLightOilConsumption(lightOilConsume);
        reportJsonData.setFreshwaterConsumption(freshWaterConsume);
        reportJsonData.setAvgSpeed(avgSpeed);
        reportJsonData.setExpectArrivalDate(reArrivalTiem);
        reportJsonData.setPressure(pressure);
        reportJsonData.setTemperature(temprature);
        reportJsonData.setWaveLevel(waveLevel);
        reportJsonData.setSnailRange(pitch);
        reportJsonData.setWeather(weather);
        reportJsonData.setWindDirection(windDirection);
        reportJsonData.setConsume(consume);
        reportJsonData.setRemark(remark);
        reportJsonData.setId(id);

        String json = new Gson().toJson(reportJsonData);

        NetUtils.postWithHeader(this, ConstantsUrls.MODIFY_REPORT)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.TYPE, "1")
                .addParams(Constants.REPORT_JSON, json)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        Toast.makeText(ModifyNoonReportActivity.this, message, Toast.LENGTH_SHORT).show();
                        show.dismiss();
                        finish();
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_noon_report;
    }

    @OnClick({R.id.iv_back, R.id.tv_input_re_arrival_time, R.id.ll_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_input_re_arrival_time:
                DateUtil.getDateTime(this, tvInputReArrivalTime);
                break;
            case R.id.ll_commit:
                showCommitDialog();
                break;
        }
    }

    private void showCommitDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_clear_butter, null);
        TextView tv_cancel_clear_buffer = (TextView) view.findViewById(R.id.tv_cancel_clear_buffer);
        TextView tv_ok_clear_butter = (TextView) view.findViewById(R.id.tv_ok_clear_butter);
        TextView tv_dialog_title = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_message = (TextView) view.findViewById(R.id.tv_dialog_message);

        tv_dialog_title.setText(getResources().getString(R.string.publish_message_title));
        tv_dialog_message.setText(getResources().getString(R.string.publish_message_message));

        tv_cancel_clear_buffer.setOnClickListener(this);
        tv_ok_clear_butter.setOnClickListener(this);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.style_dialog);
        dialog.setView(view);
        show = dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_clear_buffer:
                show.dismiss();
                break;
            case R.id.tv_ok_clear_butter:
                netCommitReport();
                break;
        }
    }

}
