package com.junhangxintong.chuangzhangtong.shipposition.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by edz on 2017/7/5.
 */

public class ShipPositionFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.mapview_ship_position)
    MapView mapviewShipPosition;
    Unbinder unbinder;
    @BindView(R.id.tv_ship_position_tu)
    TextView tvShipPositionTu;
    @BindView(R.id.tv_ship_position_my_chuandui)
    TextView tvShipPositionMyChuandui;
    @BindView(R.id.tv_ship_position_ceju)
    TextView tvShipPositionCeju;
    @BindView(R.id.iv_ship_position_location)
    ImageView ivShipPositionLocation;

    private PopupWindow popupWindow;
    private BaiduMap baiduMap;
    private LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();

    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    private MyLocationData locData;
    boolean isFirstLoc = true; // 是否首次定位
    private SensorManager mSensorManager;
    private MyLocationConfiguration.LocationMode mCurrentMode;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_ship_position, null);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        baiduMap = mapviewShipPosition.getMap();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapviewShipPosition != null) {
            mapviewShipPosition.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapviewShipPosition.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapviewShipPosition.onPause();
    }

    @OnClick({R.id.tv_ship_position_tu, R.id.tv_ship_position_my_chuandui, R.id.tv_ship_position_ceju})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ship_position_tu:
                showPopChooseMap();
                break;
            case R.id.tv_ship_position_my_chuandui:
                break;
            case R.id.tv_ship_position_ceju:
                break;
        }
    }

    private void showPopChooseMap() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_ship_position_map, null);

        LinearLayout ll_sea_map = (LinearLayout) view.findViewById(R.id.ll_sea_map);
        LinearLayout ll_land_map = (LinearLayout) view.findViewById(R.id.ll_land_map);
        LinearLayout ll_satellite_map = (LinearLayout) view.findViewById(R.id.ll_satellite_map);

        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(view);

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(tvShipPositionTu);

        ll_sea_map.setOnClickListener(this);
        ll_land_map.setOnClickListener(this);
        ll_satellite_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_sea_map:
                Toast.makeText(getActivity(), "正在拼命开发中", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
                break;
            case R.id.ll_land_map:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                popupWindow.dismiss();
                tvShipPositionTu.setBackgroundResource(R.drawable.iv_ditu);
                break;
            case R.id.ll_satellite_map:
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                popupWindow.dismiss();
                tvShipPositionTu.setBackgroundResource(R.drawable.iv_weixingtu);
                break;
        }
    }

    @OnClick(R.id.iv_ship_position_location)
    public void onViewClicked() {

        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;

        baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, null));
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.overlook(0);
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        //开启定位图层
        baiduMap.setMyLocationEnabled(true);

        // 定位初始化
        mLocClient = new LocationClient(getActivity());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

    }

    private class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapviewShipPosition == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
