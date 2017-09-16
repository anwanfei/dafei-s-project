package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.dbmanager.DaoManager;
import com.junhangxintong.chuanzhangtong.dbmanager.ShipDetailsDaoUtil;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipListBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.MyShipDetailsActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.SearchResultAdapter;
import com.junhangxintong.chuanzhangtong.shipposition.bean.ShipDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static android.content.Context.SENSOR_SERVICE;
import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

/**
 * Created by anwanfei on 2017/7/5.
 */

public class ShipPositionFragment extends BaseFragment implements View.OnClickListener, SensorEventListener {
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
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.ll_search_no_result)
    LinearLayout llSearchNoResult;
    @BindView(R.id.lv_search_result)
    ListView lvSearchResult;
    @BindView(R.id.ll_search_result)
    LinearLayout llSearchResult;
    @BindView(R.id.lv_history)
    ListView lvHistory;
    @BindView(R.id.tv_clear_history)
    TextView tvClearHistory;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tv_result_size)
    TextView tvResultSize;
    @BindView(R.id.root)
    RelativeLayout root;

    private PopupWindow popupWindow;
    private BaiduMap baiduMap;
    private LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    String inputContent;


    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    private MyLocationData locData;
    boolean isFirstLoc = true; // 是否首次定位
    private SensorManager mSensorManager;
    private MyLocationConfiguration.LocationMode mCurrentMode;

    private Double lastX = 0.0;

    String[] arrMyfleet = {"HUAHAI1", "HUAHAI2"};
    private static String PATH = "custom_config_dark.txt";
    private ShipDetailsDaoUtil shipDetailsDaoUtil;
    private List<ShipDetailsBean> shipDetailsLists;

    List<String> historyLists = new ArrayList<>();

    private DaoManager mManager;
    private ArrayAdapter historyListAdapter;
    private String userId;
    private List<ShipListBean.DataBean.ArrayBean> shipLists;


    @Override
    protected View initView() {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_ship_position, null);

        setMapCustomFile(getActivity(), PATH);
        mapviewShipPosition.setMapCustomEnable(true);

        return view;
    }

    // 设置个性化地图config文件路径
    private void setMapCustomFile(Context context, String PATH) {
        FileOutputStream out = null;
        InputStream inputStream = null;
        String moduleName = null;
        try {
            inputStream = context.getAssets()
                    .open("customConfigdir/" + PATH);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            moduleName = context.getFilesDir().getAbsolutePath();
            File f = new File(moduleName + "/" + PATH);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();
            out = new FileOutputStream(f);
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        MapView.setCustomMapStylePath(moduleName + "/" + PATH);

    }

    @Override
    protected void initData() {
        super.initData();

        userId = CacheUtils.getString(getActivity(), Constants.ID);
        //数据库初始化
        shipDetailsDaoUtil = new ShipDetailsDaoUtil(getActivity());
        if (shipDetailsDaoUtil.queryAllShipDetailsBean().size() < 5) {
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 1, "君航号", "中国", "mmsi001", "超大型货船"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 2, "中国号", "中国", "mmsi002", "大船"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 3, "辽宁号航空母舰", "中国", "mmsi003", "航空母舰"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 4, "远洋号", "中国", "mmsi004", "客船"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 5, "长征号", "中国", "mmsi005", "货船"));
        }

        // TODO: 2017/8/26 关注界面带搜索关键字跳转过来
        boolean isFromFollow = CacheUtils.getBoolean(getActivity(), Constants.SEARCHSHIPNAME, false);
        if (isFromFollow) {
            //清除在关注界面的搜索记录
            getActivity().getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().remove(Constants.SEARCHSHIPNAME).commit();
            Intent intent = getActivity().getIntent();
            String searchShipNameFromFollowShip = intent.getStringExtra(Constants.SEARCHSHIPNAME);
            etSearch.setText(searchShipNameFromFollowShip);
            searchResultByInput(etSearch.getText().toString());
            etSearch.requestFocus();
            llSearch.setVisibility(View.VISIBLE);
        }

        //在shipPotionFragment界面搜索
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                llSearch.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                inputContent = etSearch.getText().toString();
                if (inputContent.isEmpty()) {
                    // 显示历史记录
                    if (historyLists.size() > 0) {
                        showHistoryLists();
                    }
                    llSearchResult.setVisibility(View.GONE);
                    ivClear.setVisibility(View.GONE);
                } else {
                    searchResultByInput(inputContent);
                }
            }
        });

        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    if (historyLists.size() > 0) {
                        llSearch.setVisibility(View.VISIBLE);
                        showHistoryLists();
                    }
                } else {
                    // 此处为失去焦点时的处理内容
//                    llSearchResult.setVisibility(View.GONE);
                }
            }
        });

        baiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                llSearch.setVisibility(View.GONE);
                etSearch.clearFocus();
            }
        });

        ivClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etSearch.setText("");
                ivClear.setVisibility(View.GONE);
                etSearch.clearFocus();
                llSearch.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            llSearch.setVisibility(View.GONE);
            etSearch.clearFocus();
        }
    }

    //当输入内容不为空时，显示搜索结果
    private void searchResultByInput(String inputContent) {
        ivClear.setVisibility(View.VISIBLE);
        llHistory.setVisibility(View.GONE);
        llSearchResult.setVisibility(View.VISIBLE);

        shipDetailsLists = queryData(inputContent);
        if (shipDetailsLists.size() > 0) {
            llSearchNoResult.setVisibility(View.GONE);
            lvSearchResult.setVisibility(View.VISIBLE);
            SearchResultAdapter searchResultAdapter = new SearchResultAdapter(getActivity(), shipDetailsLists);
            lvSearchResult.setAdapter(searchResultAdapter);
            tvResultSize.setText("搜索到" + shipDetailsLists.size() + "条结果");

            //历史记录添加并去重
            if (!historyLists.contains(inputContent)) {
                historyLists.add(inputContent);
            }
        } else {
            llSearchNoResult.setVisibility(View.VISIBLE);
            lvSearchResult.setVisibility(View.GONE);
            tvResultSize.setText("搜索到0条结果");
        }
    }

    private void showHistoryLists() {
        llHistory.setVisibility(View.VISIBLE);
        Collections.reverse(historyLists);
        historyListAdapter = new ArrayAdapter(getActivity(), R.layout.item_search_history, R.id.tv_search_history_name, historyLists);
        lvHistory.setAdapter(historyListAdapter);
        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                etSearch.setText(historyLists.get(i));
                etSearch.requestFocus();
            }
        });
    }

    private List<ShipDetailsBean> queryData(String shipName) {
        return shipDetailsDaoUtil.getShipsByLike(shipName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        //不显示控制地图大小按钮
        //mapviewShipPosition.showZoomControls(false);
        //不显示比例尺
        mapviewShipPosition.showScaleControl(false);

        //获得地图实例
        baiduMap = mapviewShipPosition.getMap();

        //获取传感器管理服务
        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                baiduMap.setMyLocationEnabled(false);
                Log.e("TAG", "onMapStatusChangeStart================" + mapStatus);
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                // 关闭定位图层
//                baiduMap.setMyLocationEnabled(false);
                Log.e("TAG", "onMapStatusChange================" + mapStatus);
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                // 关闭定位图层
//                baiduMap.setMyLocationEnabled(true);
                Log.e("TAG", "onMapStatusChangeFinish================" + mapStatus);
            }
        });

//        DistanceUtil.getDistance()
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

        if (mLocClient != null) {
            // 退出时销毁定位
            mLocClient.stop();
        }
        if (baiduMap != null) {
            // 关闭定位图层
            baiduMap.setMyLocationEnabled(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapviewShipPosition.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);

        boolean isNeedCheck = CacheUtils.getBoolean(getActivity(), Constants.IS_NEED_CHECK_PERMISSION, true);
        if (isNeedCheck) {
            getPermission();
            CacheUtils.putBoolean(getActivity(), Constants.IS_NEED_CHECK_PERMISSION, false);
        }

    }

    //获取权限的方法
    private void getPermission() {
        final String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION};
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    for (String per : permissions) {
                        int isPermission = ContextCompat.checkSelfPermission(getActivity(), per);
                        if (isPermission != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), permissions, Constants.REQUEST_CODE0);
                            return;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mapviewShipPosition.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
    }

    @OnClick({R.id.tv_ship_position_tu, R.id.tv_ship_position_my_chuandui, R.id.tv_ship_position_ceju, R.id.tv_clear_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ship_position_tu:
                showPopChooseMap();
                break;
            case R.id.tv_ship_position_my_chuandui:
                netGetShipLists("");
                break;
            case R.id.tv_ship_position_ceju:
                break;
            case R.id.tv_clear_history:
                historyLists.clear();
                historyListAdapter.notifyDataSetChanged();
                llHistory.setVisibility(View.GONE);
                break;
        }
    }

    private void showPopMyFleet(final List<ShipListBean.DataBean.ArrayBean> shipLists) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_ship_position_my_fleet, null);
        ListView lv_my_fleet_ship_position = (ListView) view.findViewById(R.id.lv_my_fleet_ship_position);
        popupWindow = new PopupWindow(view, DensityUtil.dp2px(getActivity(), 140), LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(view);

        List<String> shipNames = new ArrayList<>();

        for (int i = 0; i < shipLists.size(); i++) {
            shipNames.add(shipLists.get(i).getShipName());
        }

        lv_my_fleet_ship_position.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.item_ship_position_my_fleet, R.id.tv_ship_position_fleet_name, shipNames));

        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //popupwindown适配4.0以下的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            popupWindow.showAsDropDown(tvShipPositionMyChuandui, DensityUtil.dp2px(getActivity(), 0), DensityUtil.dp2px(getActivity(), 10), Gravity.RIGHT);
        } else {
            popupWindow.showAtLocation(root, Gravity.RIGHT, DensityUtil.dp2px(getActivity(), 10), DensityUtil.dp2px(getActivity(), -76));
        }


        lv_my_fleet_ship_position.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = shipLists.get(i).getId();
                String shipName = shipLists.get(i).getShipName();

                Intent intent = new Intent(mContext, MyShipDetailsActivity.class);
                intent.putExtra(Constants.ID, String.valueOf(id));
                intent.putExtra(Constants.SHIP_NAME, shipName);

                startActivity(intent);
                popupWindow.dismiss();
            }
        });
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
        loateCurrentPosition();
//        mapSettings(39.9,116.3);
    }

    private void loateCurrentPosition() {

        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
        baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, null));
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.overlook(0);
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        // 开启定位图层
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

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            baiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

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
        public void onConnectHotSpotMessage(String var1, int var2) {
        }
    }

    private void mapSettings(double latitude, double longitude) {
        // 不显示缩放比例尺
        mapviewShipPosition.showZoomControls(false);
        mapviewShipPosition.showScaleControl(false);
        //百度地图 获取地图实例
        BaiduMap map = mapviewShipPosition.getMap();
//        mapController(map);
        // 开启定位图层，一定不要少了这句，否则对在地图的设置、绘制定位点将无效
        map.setMyLocationEnabled(true);
        map.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        map.setTrafficEnabled(true);
        LatLng latLng = new LatLng(latitude, longitude);
        map.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(15).build()));
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.back);
        OverlayOptions overlayOptions = new MarkerOptions()
                .position(latLng)
                .icon(bitmap)
                .zIndex(15)
                .draggable(false);
        map.addOverlay(overlayOptions);

        //设定中心点坐标
        //LatLng cenpt = new LatLng(latitude,longitude);
        //定义地图状态        // 改变地图状态，使地图显示在恰当的缩放大小
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(latLng)
                .zoom(15)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        map.setMapStatus(mMapStatusUpdate);
    }

    private void netGetShipLists(String shipName) {
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.MY_SHIP_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_NAME, shipName)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(getActivity(), Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();

                            if (code.equals("200")) {
                                ShipListBean shipListBean = new Gson().fromJson(response, ShipListBean.class);
                                shipLists = shipListBean.getData().getArray();

                                showPopMyFleet(shipLists);

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getActivity().getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(getActivity(), Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                            } else if (code.equals("404")) {
                                Toast.makeText(getActivity(), getResources().getString(R.string.no_ship), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
