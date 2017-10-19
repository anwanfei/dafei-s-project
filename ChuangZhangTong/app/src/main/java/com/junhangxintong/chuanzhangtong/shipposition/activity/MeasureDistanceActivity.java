package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
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
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.style.sources.Source;
import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.FeatureCollection;
import com.mapbox.services.commons.geojson.Point;
import com.mapbox.services.commons.models.Position;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.junhangxintong.chuanzhangtong.R.id.tv_setting;

public class MeasureDistanceActivity extends BaseActivity implements View.OnClickListener {

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
    private String distanceTo;
    private TextView tv_qidian;
    private TextView tv_close;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.choose_first_point));
        tvSetting.setTextColor(getResources().getColor(R.color.light_blue_two));
        tvSetting.setBackgroundResource(R.drawable.bg_tv_frame_gray_radius8);
        tvSetting.setText(getResources().getString(R.string.clear));
        tvSetting.setPadding(DensityUtil.dp2px(this, 10), DensityUtil.dp2px(this, 5), DensityUtil.dp2px(this, 10), DensityUtil.dp2px(this, 5));
    }

    private void showDialogClearBuffer() {
        View inflate = View.inflate(this, R.layout.dialog_one_button, null);
        TextView tv_ok_clear_butter = (TextView) inflate.findViewById(R.id.tv_ok_clear_butter);

        tv_ok_clear_butter.setOnClickListener(this);

        dialog = new Dialog(this, R.style.style_dialog);
        dialog.setContentView(inflate);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void getAppDetailSettingIntent(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        startActivity(intent);
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

               /* List<Feature> featureList = new ArrayList<>();
                featureList.add(Feature.fromGeometry(
                        Point.fromCoordinates(
                                Position.fromCoordinates(106.124621, 29.123654))));
                featureList.add(Feature.fromGeometry(
                        Point.fromCoordinates(
                                Position.fromCoordinates(106.135491, 29.121623))));
                featureList.add(Feature.fromGeometry(
                        Point.fromCoordinates(
                                Position.fromCoordinates(106.130192, 29.193052))));

                FeatureCollection featureCollection = FeatureCollection.fromFeatures(featureList);
                Source source = new GeoJsonSource("marker-source", featureCollection);

                mapboxMap.addSource(source);

                Bitmap icon_follow_run = BitmapFactory.decodeResource(getResources(), R.drawable.ic_follow_run);
                Bitmap icon_follow_stop = BitmapFactory.decodeResource(getResources(), R.drawable.ic_follow_stop);
                Bitmap icon_ship_stop = BitmapFactory.decodeResource(getResources(), R.drawable.ic_my_ship_stop);
                Bitmap icon_my_ship_run = BitmapFactory.decodeResource(getResources(), R.drawable.ic_my_ship_run);
                mapboxMap.addImage("my_marker_follow_run", icon_follow_run);
                mapboxMap.addImage("my_marker_follow_stop", icon_follow_stop);
                mapboxMap.addImage("my_marker_ship_stop", icon_ship_stop);
                mapboxMap.addImage("my_marker_ship_run", icon_my_ship_run);

                //设置layer  id，并绑定资源
                SymbolLayer markers_follow_stop = new SymbolLayer("marker-layer", "marker-source");
                markers_follow_stop.withProperties(PropertyFactory.iconImage("my_marker_follow_stop"));//从配置里面找到刚刚存入的图标
                markers_follow_stop.withProperties(PropertyFactory.iconImage("my_marker_ship_stop"));//从配置里面找到刚刚存入的图标
                mapboxMap.addLayer(markers_follow_stop);

                SymbolLayer markers_follow_run = new SymbolLayer("marker-layer", "marker-source")
                        .withProperties(PropertyFactory.iconImage("my_marker_follow_run"));//从配置里面找到刚刚存入的图标
                mapboxMap.addLayer(markers_follow_run);

                SymbolLayer markers_ship_stop = new SymbolLayer("marker-layer", "marker-source")
                        .withProperties(PropertyFactory.iconImage("my_marker_ship_stop"));//从配置里面找到刚刚存入的图标
                mapboxMap.addLayer(markers_ship_stop);

                SymbolLayer markers_ship_run = new SymbolLayer("marker-layer", "marker-source")
                        .withProperties(PropertyFactory.iconImage("my_marker_ship_run"));//从配置里面找到刚刚存入的图标
                mapboxMap.addLayer(markers_ship_run);*/


                //地圖放大
                ivShipPositionZoomBig.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CameraPosition cameraPosition = mapboxMap.getCameraPosition();
                        double zoom = cameraPosition.zoom;
                        mapboxMap.setZoom((++zoom));

                    }
                });

                //地图縮小
                ivShipPositionZoomSmall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CameraPosition cameraPosition = mapboxMap.getCameraPosition();
                        double zoom = cameraPosition.zoom;
                        mapboxMap.setZoom((--zoom));
                    }
                });

                //地图定位
                ivShipPositionLocation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
                        PackageManager packageManager = getPackageManager();
                        boolean permission = PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, "com.junhangxintong.chuanzhangtong");
                        if (permission) {
                            mapboxMap.setMyLocationEnabled(true);
                            if (mapboxMap.getMyLocation() != null) {
                                double latitude = mapboxMap.getMyLocation().getLatitude();
                                double longitude = mapboxMap.getMyLocation().getLongitude();
                                LatLng latLng = new LatLng(latitude, longitude);
                                mapboxMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                mapboxMap.setZoom(16);
                            }
                        } else {
                            /*Toast.makeText(MeasureDistanceActivity.this, getResources().getString(R.string.location_failure), Toast.LENGTH_SHORT).show();
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    for (String per : permissions) {
                                        int isPermission = ContextCompat.checkSelfPermission(MeasureDistanceActivity.this, per);
                                        if (isPermission != PackageManager.PERMISSION_GRANTED) {
                                            ActivityCompat.requestPermissions(MeasureDistanceActivity.this, permissions, Constants.REQUEST_CODE0);
                                            return;
                                        }
                                    }
                                }
                            });*/

                            showDialogClearBuffer();
                        }
                    }
                });

                final List<Marker> markers = new ArrayList<Marker>();
                //点击地图
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

                        markers.add(position.getMarker());

                        //获得所有的markers
//                        List<Marker> markers = mapboxMap.getMarkers();

                        //点击marker显示距离
                        mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
                            @Nullable
                            @Override
                            public View getInfoWindow(@NonNull final Marker marker) {
                                View inflate = LayoutInflater.from(MeasureDistanceActivity.this).inflate(R.layout.item_ceju, null);
                                tv_qidian = (TextView) inflate.findViewById(R.id.tv_qidian);
                                tv_close = (TextView) inflate.findViewById(R.id.tv_close);
                                tv_close.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (markers.size() > 1) {
                                            for (int i = 0; i < markers.size(); i++) {
                                                markers.get(i).hideInfoWindow();
                                            }

                                            mapboxMap.getMarkers().get(mapboxMap.getMarkers().size() - 1).remove();
                                            mapboxMap.getPolylines().get(mapboxMap.getPolylines().size() - 1).remove();
                                            markers.remove(markers.size() - 1);
//                                            mapboxMap.getPolylines().remove(mapboxMap.getPolylines().size()-1);
                                            latLngs.remove(latLngs.get(latLngs.size() - 1));
                                        } else {
                                            mapboxMap.getSelectedMarkers().get(0).hideInfoWindow();
                                        }
                                    }
                                });
                                if (markers.size() > 1) {
                                    tv_qidian.setText(distanceTo + " km");
                                } else {
                                    tv_qidian.setText(getResources().getString(R.string.start_point));
                                }
                                return inflate;
                            }
                        });

                        //根据markers的数量显示不同的题目
                        if (mapboxMap.getMarkers().size() > 0) {
                            tvTitle.setText(getResources().getString(R.string.choose_cext_measure_point));
                        } else {
                            tvTitle.setText(getResources().getString(R.string.choose_first_point));
                        }

                        //如果坐标集合大于1，绘制折线并测距
                        if (latLngs.size() > 1) {

                            //绘制折线
                            mapboxMap.addPolyline(new PolylineOptions().addAll(latLngs).width(2).color(Color.argb(255, 255, 92, 92)));

                            //最后一个maker与上一个的距离
                            for (int i = 0; i < markers.size(); i++) {
                                if (i > 0) {
                                    DecimalFormat df = new DecimalFormat("########0.00");
//                                    distanceTo = Math.round(markers.get(i).getPosition().distanceTo(latLngs.get(i - 1)) / 1000 * 10 / 10.0);
                                    distanceTo = df.format(markers.get(i).getPosition().distanceTo(latLngs.get(i - 1)) / 1000);
                                }
                                markers.get(i).hideInfoWindow();
                            }
                            markers.get(markers.size() - 1).showInfoWindow(mapboxMap, mapboxMapView);

                            //点击marker操作，这里做测距
                            mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(@NonNull Marker marker) {

                                    if (latLngs.size() > 1) {
                                        LatLng curLstlng = marker.getPosition();
                                        for (int i = 0; i < latLngs.size(); i++) {
                                            if (curLstlng.getLatitude() == latLngs.get(i).getLatitude() && curLstlng.getLongitude() == latLngs.get(i).getLongitude()) {
                                                if (i > 0) {
                                                    DecimalFormat df = new DecimalFormat("########0.00");
                                                    distanceTo = df.format(markers.get(i).getPosition().distanceTo(latLngs.get(i - 1)) / 1000);
                                                }
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
                        //隐藏markers
                        if (markers.size() > 0) {
                            markers.get(markers.size() - 1).hideInfoWindow();
                        }
                        //清除经纬度集合
                        latLngs.clear();

                        //
                        if (mapboxMap.getMarkers() != null) {
                            mapboxMap.clear();
                        }

                        //清除marke
                        markers.clear();

                        //清除经纬度和所有折线之后重新修改
                        tvTitle.setText(getResources().getString(R.string.choose_first_point));

                        //在地图上显示船舶（关注的我的船）
//                        addShipMarker(mapboxMap);

                        distanceTo = "0";
                    }
                });

                //在地图上显示船舶（关注的我的船）
//                addShipMarker(mapboxMap);
            }
        });
    }

    List<Marker> shipMarkers = new ArrayList<Marker>();

    private void addShipMarker(MapboxMap mapboxMap) {

        IconFactory instance = IconFactory.getInstance(MeasureDistanceActivity.this);
        Icon icon = instance.fromResource(R.drawable.ic_follow_stop);
        MarkerOptions markerOptions = new MarkerOptions();
        mapboxMap.addMarker(markerOptions.position(new LatLng(40, 116)).setIcon(icon).title(getResources().getString(R.string.junhang)).snippet("Message detail"));
       /* shipMarkers.add(markerOptions.getMarker());

        mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
            @Nullable
            @Override
            public View getInfoWindow(@NonNull Marker marker) {
                View inflate = LayoutInflater.from(MeasureDistanceActivity.this).inflate(R.layout.item_myfleet_crew, null);
                return inflate;
            }
        });
        mapboxMap.setOnInfoWindowCloseListener(new MapboxMap.OnInfoWindowCloseListener() {
            @Override
            public void onInfoWindowClose(Marker marker) {
                Toast.makeText(MeasureDistanceActivity.this, "close", Toast.LENGTH_SHORT).show();
            }
        });*/

       /* List<LatLng> polygon = new ArrayList<>();
        polygon.add(new LatLng(26.1564854, 103.156741));
        polygon.add(new LatLng(24.1255854, 108.254741));
        polygon.add(new LatLng(29.1114854, 102.241741));
        polygon.add(new LatLng(26.5764854, 107.272741));
        polygon.add(new LatLng(21.7874854, 104.278741));
        polygon.add(new LatLng(25.0044854, 106.782741));
        polygon.add(new LatLng(20.7174854, 106.014741));
        polygon.add(new LatLng(33.7684854, 103.520741));
        polygon.add(new LatLng(30.1274854, 108.104741));
        polygon.add(new LatLng(28.4174854, 101.000741));
        mapboxMap.addPolygon(new PolygonOptions()
                .addAll(polygon)
                .fillColor(ContextCompat.getColor(MeasureDistanceActivity.this, R.color.red))
                .alpha(0.5f)
                .strokeColor(ContextCompat.getColor(MeasureDistanceActivity.this, R.color.black))
        );*/

        List<Feature> featureList = new ArrayList<>();
        featureList.add(Feature.fromGeometry(
                Point.fromCoordinates(
                        Position.fromCoordinates(106.124621, 29.123654))));
        featureList.add(Feature.fromGeometry(
                Point.fromCoordinates(
                        Position.fromCoordinates(106.135491, 29.121623))));
        featureList.add(Feature.fromGeometry(
                Point.fromCoordinates(
                        Position.fromCoordinates(106.130192, 29.193052))));

        FeatureCollection featureCollection = FeatureCollection.fromFeatures(featureList);
        Source source = new GeoJsonSource("marker-source", featureCollection);

        mapboxMap.addSource(source);

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

    @Override
    public void onClick(View view) {
        getAppDetailSettingIntent(MeasureDistanceActivity.this);
        dialog.dismiss();
    }
}
