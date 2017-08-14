package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.ll_layout_horizental_gray_line)
    LinearLayout llLayoutHorizentalGrayLine;
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

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_leave_port_message;
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
