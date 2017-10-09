package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.dynamic.bean.DynamicRemindLeaveReportBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.shipposition.bean.LeaveReportInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.utils.RoleEnum;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class ShipLeavePortMessageActivity extends BaseActivity {

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
    @BindView(R.id.tv_cartgo_type)
    TextView tvCartgoType;
    @BindView(R.id.tv_cartgo_num)
    TextView tvCartgoNum;
    @BindView(R.id.tv_loading_unloading_cargo_start_time)
    TextView tvLoadingUnloadingCargoStartTime;
    @BindView(R.id.tv_loading_unloading_cargo_complete_time)
    TextView tvLoadingUnloadingCargoCompleteTime;
    @BindView(R.id.tv_leave_time)
    TextView tvLeaveTime;
    @BindView(R.id.tv_re_time_arrive_port)
    TextView tvReTimeArrivePort;
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
    private boolean threadExit = true;
    private int minute;
    private int second;
    private Intent intent;
    private String fromDynamic;
    private DynamicRemindLeaveReportBean.DataBean.ObjectBean leavaReportInfoFromDynamic;
    private LeaveReportInfoBean.DataBean.ObjectBean leavaReportInfoFromNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.leave_message));
    }

    @Override
    protected void initData() {
        intent = getIntent();
        fromDynamic = intent.getStringExtra(Constants.FROM_DYNAMIC);
        getLeaveReportInfo(true);
    }

    private void getLeaveReportInfo(final boolean isShowCountdownTime) {
        if (StringUtils.isNotBlank(fromDynamic)) {
            String dynamic_id = intent.getStringExtra(Constants.ID);
            NetUtils.postWithHeader(this, ConstantsUrls.DYNAMIC_DETAILS)
                    .addParams(Constants.ID, dynamic_id)
                    .build()
                    .execute(new NetUtils.MyStringCallback() {
                        @Override
                        protected void onSuccess(String response, String message) {
                            DynamicRemindLeaveReportBean dynamicRemindNonnReportBean = new Gson().fromJson(response,DynamicRemindLeaveReportBean.class);
                            leavaReportInfoFromDynamic = dynamicRemindNonnReportBean.getData().getObject();
                            tvShipMessageName.setText(leavaReportInfoFromDynamic.getShipName());
                            tvShipTime.setText(leavaReportInfoFromDynamic.getCreateDate());
                            tvLoadingUnloadingCargoPort.setText(leavaReportInfoFromDynamic.getLoadPort());
                            tvCartgoType.setText(leavaReportInfoFromDynamic.getGoodsType());
                            tvCartgoNum.setText(leavaReportInfoFromDynamic.getGoodsNum());
                            tvLoadingUnloadingCargoStartTime.setText(leavaReportInfoFromDynamic.getLoadBeginDate());
                            tvLoadingUnloadingCargoCompleteTime.setText(leavaReportInfoFromDynamic.getLeavaBerthDate());
                            tvLeaveTime.setText(leavaReportInfoFromDynamic.getLeavaBerthDate());
                            tvReTimeArrivePort.setText(leavaReportInfoFromDynamic.getExpectArriveBearthDate());
                            tvTugUseNum.setText(leavaReportInfoFromDynamic.getTugUseNum());
                            tvShipDraft.setText(leavaReportInfoFromDynamic.getShipForwardDraft());
                            String isPilotage = leavaReportInfoFromDynamic.getIsPilotage();

                            if (isPilotage != null) {
                                if (isPilotage.equals("1")) {
                                    tvRemark.setText(getResources().getString(R.string.yes));
                                } else {
                                    tvRemark.setText(getResources().getString(R.string.no));
                                }
                            }

                            int timer = leavaReportInfoFromDynamic.getTimer();

                            CountdownTime(isShowCountdownTime, 10000);
                        }
                    });
        } else {

            String id = intent.getStringExtra(Constants.ID);
            String shipName = intent.getStringExtra(Constants.SHIP_NAME);
            tvShipMessageName.setText(shipName);

            NetUtils.postWithHeader(this, ConstantsUrls.REPORT_INFO)
                    .addParams(Constants.ID, id)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ShipLeavePortMessageActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(ShipLeavePortMessageActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                                String message = netServiceErrort.getMessage();
                                String code = netServiceErrort.getCode();
                                if (code.equals("200")) {
                                    LeaveReportInfoBean leaveReportInfoBean = new Gson().fromJson(response, LeaveReportInfoBean.class);
                                    leavaReportInfoFromNet = leaveReportInfoBean.getData().getObject();
                                    tvShipTime.setText(leavaReportInfoFromNet.getCreateDate());
                                    tvLoadingUnloadingCargoPort.setText(leavaReportInfoFromNet.getLoadPort());
                                    tvCartgoType.setText(leavaReportInfoFromNet.getGoodsType());
                                    tvCartgoNum.setText(leavaReportInfoFromNet.getGoodsNum());
                                    tvLoadingUnloadingCargoStartTime.setText(leavaReportInfoFromNet.getLoadBeginDate());
                                    tvLoadingUnloadingCargoCompleteTime.setText(leavaReportInfoFromNet.getLeavaBerthDate());
                                    tvLeaveTime.setText(leavaReportInfoFromNet.getLeavaBerthDate());
                                    tvReTimeArrivePort.setText(leavaReportInfoFromNet.getExpectArriveBearthDate());
                                    tvTugUseNum.setText(leavaReportInfoFromNet.getTugUseNum());
                                    tvShipDraft.setText(leavaReportInfoFromNet.getShipForwardDraft());
                                    String isPilotage = leavaReportInfoFromNet.getIsPilotage();

                                    if (isPilotage != null) {
                                        if (isPilotage.equals("1")) {
                                            tvRemark.setText(getResources().getString(R.string.yes));
                                        } else {
                                            tvRemark.setText(getResources().getString(R.string.no));
                                        }
                                    }

                                    int timer = leavaReportInfoFromNet.getTimer();

                                    CountdownTime(isShowCountdownTime, 10000);

                                } else if (code.equals("601")) {
                                    //清除了sp存储
                                    getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                    //保存获取权限的sp
                                    CacheUtils.putBoolean(ShipLeavePortMessageActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                    startActivity(new Intent(ShipLeavePortMessageActivity.this, LoginRegisterActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(ShipLeavePortMessageActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }
    }

    private void CountdownTime(boolean isShowCountdownTime, int timer) {

        if (isShowCountdownTime) {
            String roleId = CacheUtils.getString(ShipLeavePortMessageActivity.this, Constants.ROLEID);
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
        return R.layout.activity_leave_port_message;
    }

    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.tv_sign_no_read, R.id.tv_place_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                ModifyLeaveReport();
                break;
            case R.id.tv_sign_no_read:
                break;
            case R.id.tv_place_top:
                break;
        }
    }

    private void ModifyLeaveReport() {
        if (StringUtils.isNotBlank(fromDynamic)) {
            Intent intent = new Intent(ShipLeavePortMessageActivity.this, ModifyLeaveReportActivity.class);
            intent.putExtra(Constants.LEAVE_REPORT_INFO, leavaReportInfoFromDynamic);
            startActivity(intent);
        } else {
            Intent intent = new Intent(ShipLeavePortMessageActivity.this, ModifyLeaveReportActivity.class);
            intent.putExtra(Constants.LEAVE_REPORT_INFO, leavaReportInfoFromNet);
            intent.putExtra(Constants.FROM_SHIP, true);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLeaveReportInfo(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        threadExit = false;
    }
}
