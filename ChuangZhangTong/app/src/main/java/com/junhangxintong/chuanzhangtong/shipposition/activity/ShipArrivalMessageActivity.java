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
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.shipposition.bean.ArrivalReportInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class ShipArrivalMessageActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_titlebar)
    LinearLayout llTitlebar;
    @BindView(R.id.tv_ship_message_name)
    TextView tvShipMessageName;
    @BindView(R.id.tv_ship_time)
    TextView tvShipTime;
    @BindView(R.id.ll_ship_name_time)
    LinearLayout llShipNameTime;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_sign_no_read)
    TextView tvSignNoRead;
    @BindView(R.id.tv_place_top)
    TextView tvPlaceTop;
    @BindView(R.id.ll_zhengwu_bottom)
    LinearLayout llZhengwuBottom;
    @BindView(R.id.ll_layout_horizental_gray_line)
    LinearLayout llLayoutHorizentalGrayLine;
    @BindView(R.id.tv_arrival_port)
    TextView tvArrivalPort;
    @BindView(R.id.tv_port_stop_position)
    TextView tvPortStopPosition;
    @BindView(R.id.tv_anchor_arrival_time)
    TextView tvAnchorArrivalTime;
    @BindView(R.id.tv_anchor_posotion)
    TextView tvAnchorPosotion;
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
    @BindView(R.id.tv_remark)
    TextView tvRemark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.arrival_message));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String shipId = intent.getStringExtra(Constants.SHIP_ID);
        String shipName = intent.getStringExtra(Constants.SHIP_NAME);
        tvShipMessageName.setText(shipName);

        NetUtils.postWithHeader(this, ConstantsUrls.REPORT_INFO)
                .addParams(Constants.ID, shipId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ShipArrivalMessageActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ShipArrivalMessageActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                ArrivalReportInfoBean arrivalReportInfoBean = new Gson().fromJson(response, ArrivalReportInfoBean.class);
                                ArrivalReportInfoBean.DataBean.ObjectBean arrivalInfo = arrivalReportInfoBean.getData().getObject();

                                tvShipTime.setText(arrivalInfo.getCreateDate());
                                tvArrivalPort.setText(arrivalInfo.getArrivePort());
                                tvAnchorPosotion.setText(arrivalInfo.getPortRadsteadBerth());
                                tvAnchorArrivalTime.setText(arrivalInfo.getArriveAnchorDate());
                                tvAnchorPosotion.setText(arrivalInfo.getAnchorPosition());
                                tvLatitude.setText(arrivalInfo.getLatitude());
                                tvLongtitude.setText(arrivalInfo.getLongitude());
                                tvShipDirection.setText(arrivalInfo.getCourse());
                                tvShipSpeed.setText(arrivalInfo.getCurrShipSpeed());
                                tvTheShipHeavyOil.setText(arrivalInfo.getShipHeavyOil());
                                tvShipDraft.setText(arrivalInfo.getShipForwardDraft());
                                tvTheShipLightOil.setText(arrivalInfo.getShipLightOil());
                                tvTheShipFrashwater.setText(arrivalInfo.getShipFreshwater());
                                tvLightOilConsume.setText(arrivalInfo.getLightOilConsumption());
                                tvFrashwaterConsume.setText(arrivalInfo.getFreshwaterConsumption());
                                tvRemark.setText(arrivalInfo.getRemark());

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ShipArrivalMessageActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ShipArrivalMessageActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ShipArrivalMessageActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_arrival_message;
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
