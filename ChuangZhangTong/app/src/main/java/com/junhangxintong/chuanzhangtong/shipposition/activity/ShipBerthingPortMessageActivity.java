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
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindBerthingReportBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.BerthingReportInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.utils.RoleEnum;

import org.apache.commons.lang.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ShipBerthingPortMessageActivity extends BaseActivity {

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
    @BindView(R.id.tv_loading_unloading_cargo_port)
    TextView tvLoadingUnloadingCargoPort;
    @BindView(R.id.tv_anchor_leave_time)
    TextView tvAnchorLeaveTime;
    @BindView(R.id.tv_system_stop_time)
    TextView tvSystemStopTime;
    @BindView(R.id.tv_test_cabin_start_time)
    TextView tvTestCabinStartTime;
    @BindView(R.id.tv_test_cabin_end_time)
    TextView tvTestCabinEndTime;
    @BindView(R.id.tv_port_position)
    TextView tvPortPosition;
    @BindView(R.id.tv_tug_use_num)
    TextView tvTugUseNum;
    @BindView(R.id.tv_ship_draft)
    TextView tvShipDraft;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_minite)
    TextView tvMinite;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.ll_countdown_time)
    LinearLayout llCountdownTime;
    private int minute;
    private int second;
    private boolean threadExit = true;

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
    private String fromDynamic;
    private DynamicRemindBerthingReportBean.DataBean.ObjectBean berthingReportInfoFromDynamic;
    private BerthingReportInfoBean.DataBean.ObjectBean berthingReportInfoFromShip;

    private Intent intent;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.berthing_message));
    }

    @Override
    protected void initData() {
        intent = getIntent();

        //来自动态提醒
        fromDynamic = intent.getStringExtra(Constants.FROM_DYNAMIC);
        getBerthingReport(true);
    }

    private void getBerthingReport(final boolean isShowCountdownTime) {

        if (StringUtils.isNotBlank(fromDynamic)) {
            getBerthinReportInfoFromDynamic(isShowCountdownTime);
        } else {
            getBerthinReportInfoFromNet(isShowCountdownTime);
        }
    }

    private void getBerthinReportInfoFromDynamic(final boolean isShowCountdownTime) {
        String dynamic_id = intent.getStringExtra(Constants.ID);
        NetUtils.postWithHeader(this, ConstantsUrls.DYNAMIC_DETAILS)
                .addParams(Constants.ID, dynamic_id)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        DynamicRemindBerthingReportBean dynamicRemindBerthingReportBean =  new Gson().fromJson(response,DynamicRemindBerthingReportBean.class);
                        berthingReportInfoFromDynamic = dynamicRemindBerthingReportBean.getData().getObject();
                        tvShipMessageName.setText(berthingReportInfoFromDynamic.getShipName());
                        tvShipTime.setText(berthingReportInfoFromDynamic.getCreateDate());
                        tvLoadingUnloadingCargoPort.setText(berthingReportInfoFromDynamic.getLoadPort());
                        tvAnchorLeaveTime.setText(berthingReportInfoFromDynamic.getAnchorAweighDate());
                        tvSystemStopTime.setText(berthingReportInfoFromDynamic.getXtBerthDate());
                        tvTestCabinStartTime.setText(berthingReportInfoFromDynamic.getHoldInspectionBeginDate());
                        tvTestCabinEndTime.setText(berthingReportInfoFromDynamic.getHoldInspectionEndDate());
                        tvPortPosition.setText(berthingReportInfoFromDynamic.getPortBearth());
                        tvTugUseNum.setText(berthingReportInfoFromDynamic.getTugUseNum());
                        tvShipDraft.setText(berthingReportInfoFromDynamic.getShipForwardDraft());

                        String isPilotage = berthingReportInfoFromDynamic.getIsPilotage();
                        id = String.valueOf(berthingReportInfoFromDynamic.getId());

                        if (isPilotage != null) {
                            if (isPilotage.equals("1")) {
                                tvRemark.setText(getResources().getString(R.string.yes));
                            } else {
                                tvRemark.setText(getResources().getString(R.string.no));
                            }
                        }

                        int timer = berthingReportInfoFromDynamic.getTimer();
                        CountdownTime(isShowCountdownTime, 10000);
                    }
                });
    }

    private void getBerthinReportInfoFromNet(final boolean isShowCountdownTime) {
        String id = intent.getStringExtra(Constants.ID);
        String shipName = intent.getStringExtra(Constants.SHIP_NAME);
        tvShipMessageName.setText(shipName);
        NetUtils.postWithHeader(this, ConstantsUrls.REPORT_INFO)
                .addParams(Constants.ID, id)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        BerthingReportInfoBean berthingReportInfoBean = new Gson().fromJson(response, BerthingReportInfoBean.class);
                        berthingReportInfoFromShip = berthingReportInfoBean.getData().getObject();

                        tvShipTime.setText(berthingReportInfoFromShip.getCreateDate());
                        tvLoadingUnloadingCargoPort.setText(berthingReportInfoFromShip.getLoadPort());
                        tvAnchorLeaveTime.setText(berthingReportInfoFromShip.getAnchorAweighDate());
                        tvSystemStopTime.setText(berthingReportInfoFromShip.getXtBerthDate());
                        tvTestCabinStartTime.setText(berthingReportInfoFromShip.getHoldInspectionBeginDate());
                        tvTestCabinEndTime.setText(berthingReportInfoFromShip.getHoldInspectionEndDate());
                        tvPortPosition.setText(berthingReportInfoFromShip.getPortBearth());
                        tvTugUseNum.setText(berthingReportInfoFromShip.getTugUseNum());
                        tvShipDraft.setText(berthingReportInfoFromShip.getShipForwardDraft());

                        String isPilotage = berthingReportInfoFromShip.getIsPilotage();

                        if (isPilotage != null) {
                            if (isPilotage.equals("1")) {
                                tvRemark.setText(getResources().getString(R.string.yes));
                            } else {
                                tvRemark.setText(getResources().getString(R.string.no));
                            }
                        }
                        int timer = berthingReportInfoFromShip.getTimer();
                        CountdownTime(isShowCountdownTime, 10000);
                    }
                });
    }

    private void CountdownTime(boolean isShowCountdownTime, int timer) {

        if (isShowCountdownTime) {
            String roleId = CacheUtils.getString(ShipBerthingPortMessageActivity.this, Constants.ROLEID);
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
        return R.layout.activity_ship_berthing_port_message;
    }

    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.tv_sign_no_read, R.id.tv_place_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                ModifyBerthingReport();
                break;
            case R.id.tv_sign_no_read:
                break;
            case R.id.tv_place_top:
                break;
        }
    }

    private void ModifyBerthingReport() {
        if (StringUtils.isNotBlank(fromDynamic)) {
            Intent intent = new Intent(ShipBerthingPortMessageActivity.this, ModifyBerthingReportActivity.class);
            intent.putExtra(Constants.BERTHING_REPORT_INFO, berthingReportInfoFromDynamic);
            startActivity(intent);
        } else {
            Intent intent = new Intent(ShipBerthingPortMessageActivity.this, ModifyBerthingReportActivity.class);
            intent.putExtra(Constants.BERTHING_REPORT_INFO, berthingReportInfoFromShip);
            intent.putExtra(Constants.FROM_SHIP, true);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBerthingReport(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        threadExit = false;
    }
}
