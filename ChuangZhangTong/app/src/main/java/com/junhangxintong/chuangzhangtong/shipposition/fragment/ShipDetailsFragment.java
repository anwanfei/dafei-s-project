package com.junhangxintong.chuangzhangtong.shipposition.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.MapView;
import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/8.
 */

public class ShipDetailsFragment extends BaseFragment {
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
    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private boolean isShowShipOthorDetails = true;

    @Override
    protected View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_ship_details, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.huahai_one));
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_ship_details_down, R.id.iv_trajactory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                Toast.makeText(getActivity(), "正在拼命开发中...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        getActivity().finish();
    }
}
