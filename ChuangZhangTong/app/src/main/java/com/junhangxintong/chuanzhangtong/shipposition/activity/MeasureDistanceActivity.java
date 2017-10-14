package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.junhangxintong.chuanzhangtong.R.id.tv_setting;

public class MeasureDistanceActivity extends BaseActivity {

    @BindView(R.id.mapbox_mapView)
    MapView mapboxMapView;
    @BindView(R.id.iv_ship_position_location)
    ImageView ivShipPositionLocation;
    @BindView(R.id.iv_ship_position_zoom_big)
    ImageView ivShipPositionZoomBig;
    @BindView(R.id.iv_ship_position_zoom_small)
    ImageView ivShipPositionZoomSmall;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(tv_setting)
    TextView tvSetting;
    private int mapStyle;
    final List<LatLng> latLngs = new ArrayList<>();
    private long distanceTo;
    private TextView tv_qidian;
    private TextView tv_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText("点选起点");
        tvSetting.setText("清除");
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mapStyle = intent.getIntExtra(Constants.MAP_STYLE, 1);
        switch (mapStyle) {
            case 1:
                mapboxMapView.setStyleUrl(ConstantsUrls.SEA_MAP);
                break;
            case 2:
                mapboxMapView.setStyleUrl(ConstantsUrls.GOOGLE_MAP);
                break;
            case 3:
                mapboxMapView.setStyleUrl(ConstantsUrls.SATELLITE_MAP);
                break;
        }


        //对mapbox的异步处理
        mapboxMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
                ivShipPositionZoomBig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CameraPosition cameraPosition = mapboxMap.getCameraPosition();
                        double zoom = cameraPosition.zoom;
                        mapboxMap.setZoom((++zoom));
                    }
                });

                ivShipPositionZoomSmall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CameraPosition cameraPosition = mapboxMap.getCameraPosition();
                        double zoom = cameraPosition.zoom;
                        mapboxMap.setZoom((--zoom));

                    }
                });

                ivShipPositionLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mapboxMap.setMyLocationEnabled(true);
                        double latitude = mapboxMap.getMyLocation().getLatitude();
                        double longitude = mapboxMap.getMyLocation().getLongitude();
                        LatLng latLng = new LatLng(latitude, longitude);
                        mapboxMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mapboxMap.setZoom(16);
                    }
                });

                mapboxMap.setOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng point) {
                        //获得icon
                        IconFactory instance = IconFactory.getInstance(MeasureDistanceActivity.this);
                        Icon icon = instance.fromResource(R.drawable.ic_point_red);

                        //把当前的坐标添加到坐标集合中
                        latLngs.add(point);
                        MarkerOptions position = new MarkerOptions().position(new LatLng(point.getLatitude(), point.getLongitude()));
                        mapboxMap.addMarker(position.setIcon(icon));

                        //如果坐标集合大于1，绘制折线
                        if (latLngs.size() > 1) {
                            //绘制折线
                            mapboxMap.addPolyline(new PolylineOptions().addAll(latLngs).width(2).color(Color.argb(255, 255, 1, 1)));

                            //点击marker操作，这里做测距
                            mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {
//                                    marker.showInfoWindow(mapboxMap, mapView);
                                    if (latLngs.size() > 1) {
                                        int rePoint = (int) marker.getId() / 2 - 2;
                                        Log.e("TAG", "markerId==============" + marker.getId());
                                        LatLng curLstlng = marker.getPosition();
                                        for (int i = 0; i < latLngs.size(); i++) {
                                            if (curLstlng.getLatitude() == latLngs.get(i).getLatitude() && curLstlng.getLongitude() == latLngs.get(i).getLongitude()) {
                                                distanceTo = Math.round((marker.getPosition().distanceTo(latLngs.get(i - 1)) * 10) / 10.0);
                                            }
                                        }
                                    }
                                    return false;
                                }
                            });
                        }
                    }
                });

                tvSetting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        mapboxMap.removeMarker(markers.get(0));
                      /*  mapboxMap.clear();
                        mapboxMap.getMarkers().clear();
                        mapboxMap.getPolylines().clear();*/
                        latLngs.clear();
                        mapboxMap.clear();
//                        mapboxMap.getMarkers().clear();
//                        mapboxMap.getPolylines().clear();

                    }
                });

                mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
                    @Nullable
                    @Override
                    public View getInfoWindow(@NonNull final Marker marker) {
                        View inflate = LayoutInflater.from(MeasureDistanceActivity.this).inflate(R.layout.item_ceju, null);
                        tv_qidian = (TextView) inflate.findViewById(R.id.tv_qidian);
                        tv_close = (TextView) inflate.findViewById(R.id.tv_close);
                        tv_qidian.setText(distanceTo + " m");
                        /*tv_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                Toast.makeText(MapboxActivity.this, "close", Toast.LENGTH_SHORT).show();
                            }
                        });*/
                        return inflate;
                    }
                });
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_measure_distance;
    }

    @OnClick({R.id.iv_back, tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case tv_setting:
                break;
        }
    }
}
