package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.shipposition.bean.MyShipInfoBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.utils.ShareUtils;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/8/8.
 */

public class ShipDetailsFragment extends BaseFragment implements View.OnClickListener {
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
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.mapbox_mapView)
    MapView mapboxMapView;
    private boolean isShowShipOthorDetails = true;
    private PopupWindow popupWindow;
    private boolean isShowPop = true;
    private boolean isShowShareAndFollow = true;
    private TextView tv_warm_close;
    private boolean isOpenWarm = true;
    private TextView tv_share_ship;
    private String id = "1";

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

        ivShare.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.iv_menu);
        tvTitle.setText(getResources().getString(R.string.huahai_one));

        //创建populwindown对象
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_ship_my_ship_details_menu, null);
        tv_warm_close = (TextView) view.findViewById(R.id.tv_warm_close);
        tv_share_ship = (TextView) view.findViewById(R.id.tv_share_ship);
        popupWindow = new PopupWindow(view, DensityUtil.dp2px(getActivity(), 120), LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setContentView(view);

        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
        tvUpdateTime.setText(DateUtil.getCurrentTimeMDHM());

        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra(Constants.ID);

        NetUtils.postWithHeader(getActivity(), ConstantsUrls.MY_SHIP_INFO)
                .addParams(Constants.ID, id)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        MyShipInfoBean myShipInfoBean = new Gson().fromJson(response, MyShipInfoBean.class);
                        MyShipInfoBean.DataBean.ObjectBean shipInfo = myShipInfoBean.getData().getObject();
                        tvShipName.setText(shipInfo.getShipName());
                        tvTitle.setText(shipInfo.getShipName());
                        tvChuanji.setText(shipInfo.getNation());
                        tvMmsi.setText(shipInfo.getMmsi());
                        tvShipHuhao.setText(shipInfo.getCallSign());
                        tvShipImo.setText(shipInfo.getImo());
                        tvShipType.setText(shipInfo.getType());
                        tvShipZize.setText((shipInfo.getShipSize() / 100) + "/" + (shipInfo.getShipWidth() / 100));
//                                tvUpdateTime.setText(shipInfo.getModifyDate());
                        tvUpdateTime.setText(DateUtil.getCurrentTimeYMDHMS());
                    }
                });


        mapboxMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                IconFactory instance = IconFactory.getInstance(getActivity());
                Icon icon = instance.fromResource(R.drawable.ic_my_ship_run);
                LatLng latLng = new LatLng(40, 116);
                mapboxMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mapboxMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_ship_details_down, R.id.iv_trajactory, R.id.iv_back, R.id.iv_share})
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
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.iv_share:
                if (isShowPop) {
                    showPop();
                    isShowPop = false;
                } else {
                    popupWindow.dismiss();
                    isShowPop = true;
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showPop() {

        popupWindow.showAsDropDown(ivShare, 0, 0);

        tv_warm_close.setOnClickListener(this);
        tv_share_ship.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_warm_close:
                popupWindow.dismiss();
                isShowShareAndFollow = true;
                if (isOpenWarm) {
                    tv_warm_close.setText(getResources().getString(R.string.warm_open));
                    Log.e("TAG", "tv_warm_close==========" + tv_warm_close.getText().toString());
                    Toast.makeText(getActivity(), getResources().getString(R.string.warm_close), Toast.LENGTH_SHORT).show();
                    tv_warm_close.setTextColor(getResources().getColor(R.color.black));
                    isOpenWarm = false;
                    isShowPop = true;
                    // TODO: 2017/8/13 关闭提醒的操作
                } else {
                    Log.e("TAG", "tv_warm_close==========" + tv_warm_close.getText().toString());
                    tv_warm_close.setText(getResources().getString(R.string.warm_close));
                    Toast.makeText(getActivity(), getResources().getString(R.string.warm_open), Toast.LENGTH_SHORT).show();
                    tv_warm_close.setTextColor(getResources().getColor(R.color.textcolor_gray80));
                    isOpenWarm = true;
                    isShowPop = true;
                    // TODO: 2017/8/13 打开提醒的操作

                }

                break;

            case R.id.tv_share_ship:
                popupWindow.dismiss();
                isShowShareAndFollow = true;

                //判断是否打开过pop
                isShowPop = true;
                // TODO: 2017/8/13
                share();

                break;
        }
    }

    private void share() {
        UMImage image = new UMImage(getActivity(), R.drawable.wx108);//资源文件

        UMWeb web = new UMWeb(Constants.SHARE_URL);
        web.setTitle(getResources().getString(R.string.app_name));//标题
        web.setThumb(image);  //缩略图
        web.setDescription(getResources().getString(R.string.app_description));//描述

        ShareUtils.share(getActivity(), web);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            popupWindow.dismiss();
        }
    }

}
