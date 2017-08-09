package com.junhangxintong.chuangzhangtong.shipposition.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.MapView;
import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.junhangxintong.chuangzhangtong.R.id.tv_follow_ship;
import static com.junhangxintong.chuangzhangtong.R.id.tv_share_ship;

public class OtherShipDetailsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.mapview_ship_details)
    MapView mapviewShipDetails;
    @BindView(R.id.iv_ship_details_down)
    ImageView ivShipDetailsDown;
    @BindView(R.id.tv_ship_name)
    TextView tvShipName;
    @BindView(R.id.iv_trajactory)
    ImageView ivTrajactory;
    @BindView(R.id.tv_ship_start)
    TextView tvShipStart;
    @BindView(R.id.tv_ship_start_upde_time)
    TextView tvShipStartUpdeTime;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.tv_ship_details_update_time)
    TextView tvShipDetailsUpdateTime;
    @BindView(R.id.tv_ship_end)
    TextView tvShipEnd;
    @BindView(R.id.tv_ship_end_upde_time)
    TextView tvShipEndUpdeTime;
    @BindView(R.id.rl_ship_details_main)
    RelativeLayout rlShipDetailsMain;
    @BindView(R.id.tv_chuanji)
    TextView tvChuanji;
    @BindView(R.id.tv_ship_huhao)
    TextView tvShipHuhao;
    @BindView(R.id.tv_ship_type)
    TextView tvShipType;
    @BindView(R.id.tv_mmsi)
    TextView tvMmsi;
    @BindView(R.id.tv_ship_imo)
    TextView tvShipImo;
    @BindView(R.id.tv_ship_zize)
    TextView tvShipZize;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_chishui)
    TextView tvChishui;
    @BindView(R.id.tv_purpose)
    TextView tvPurpose;
    @BindView(R.id.tv_ship_re_arrive_time)
    TextView tvShipReArriveTime;
    @BindView(R.id.tv_latitude)
    TextView tvLatitude;
    @BindView(R.id.tv_diretion)
    TextView tvDiretion;
    @BindView(R.id.tv_longitude)
    TextView tvLongitude;
    @BindView(R.id.tv_ship_speed)
    TextView tvShipSpeed;
    @BindView(R.id.tv_update_time)
    TextView tvUpdateTime;
    @BindView(R.id.ll_ship_details_othor)
    LinearLayout llShipDetailsOthor;
    @BindView(R.id.ll_ship_details_main)
    LinearLayout llShipDetailsMain;
    @BindView(R.id.ll_ship_details)
    RelativeLayout llShipDetails;
    private boolean isShowShipOthorDetails = true;
    private boolean isShowShareAndFollow = true;
    private PopupWindow popupWindow;

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_detils));
        ivShare.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.iv_menu);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.iv_ship_details_down, R.id.iv_trajactory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                if (isShowShareAndFollow) {
                    showShareAndFollowShipPop();
                    isShowShareAndFollow = false;
                } else {
                    popupWindow.dismiss();
                    isShowShareAndFollow = true;
                }
                break;
            case R.id.iv_ship_details_down:
                if (isShowShipOthorDetails) {
                    ivShipDetailsDown.setImageResource(R.drawable.bg_ship_details_down);
                    llShipDetailsOthor.setVisibility(View.GONE);
                    isShowShipOthorDetails = false;
                } else {
                    ivShipDetailsDown.setImageResource(R.drawable.bg_ship_details_top_arrow);
                    llShipDetailsOthor.setVisibility(View.VISIBLE);
                    isShowShipOthorDetails = true;
                }
                break;
            case R.id.iv_trajactory:
                Toast.makeText(OtherShipDetailsActivity.this, "正在拼命开发中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showShareAndFollowShipPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ship_details_menu, null);
        TextView tv_follow_ship = (TextView) view.findViewById(R.id.tv_follow_ship);
        TextView tv_share_ship = (TextView) view.findViewById(R.id.tv_share_ship);

        popupWindow = new PopupWindow(view, DensityUtil.dp2px(this, 120), LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setContentView(view);
        popupWindow.showAsDropDown(ivShare, 0, 0);

        tv_follow_ship.setOnClickListener(this);
        tv_share_ship.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case tv_follow_ship:
                popupWindow.dismiss();
                isShowShareAndFollow = true;
                Toast.makeText(OtherShipDetailsActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                // TODO: 2017/8/8
                break;

            case tv_share_ship:
                popupWindow.dismiss();
                isShowShareAndFollow = true;
                Toast.makeText(OtherShipDetailsActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                // TODO: 2017/8/8

                break;
        }
    }
}
