package com.junhangxintong.chuanzhangtong.shipposition.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.dbmanager.DaoManager;
import com.junhangxintong.chuanzhangtong.dbmanager.ShipDetailsDaoUtil;
import com.junhangxintong.chuanzhangtong.mine.activity.FeedbackActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipListBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.MeasureDistanceActivity;
import com.junhangxintong.chuanzhangtong.shipposition.activity.MyShipDetailsActivity;
import com.junhangxintong.chuanzhangtong.shipposition.adapter.SearchResultAdapter;
import com.junhangxintong.chuanzhangtong.shipposition.bean.ShipDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

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

import static android.content.Context.SENSOR_SERVICE;
import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;
import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by anwanfei on 2017/7/5.
 */
public class ShipPositionFragment extends BaseFragment implements View.OnClickListener, SensorEventListener {
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
    @BindView(R.id.rl_search)
    RelativeLayout rlSearch;
    @BindView(R.id.mapbox_mapView)
    com.mapbox.mapboxsdk.maps.MapView mapboxMapView;
    @BindView(R.id.iv_ship_position_zoom_big)
    ImageView ivShipPositionZoomBig;
    @BindView(R.id.iv_ship_position_zoom_small)
    ImageView ivShipPositionZoomSmall;

    private PopupWindow popupWindow;
    String inputContent;
    private int mapStyle;


    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    boolean isFirstLoc = true; // 是否首次定位
    private SensorManager mSensorManager;

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
    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapboxMapView.onStart();
    }

    @Override
    protected View initView() {

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_ship_position, null);
        setMapCustomFile(getActivity(), PATH);

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
    }

    @Override
    protected void initData() {
        super.initData();
        IconFactory instance = IconFactory.getInstance(getActivity());
        final Icon icon = instance.fromResource(R.drawable.ic_my_ship_run);

        mapboxMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
// TODO: 2017/10/23
                List<LatLng> latLngs = new ArrayList<LatLng>();
                latLngs.add(new LatLng(41, 116));
                latLngs.add(new LatLng(42, 117));
                latLngs.add(new LatLng(43, 120));
                latLngs.add(new LatLng(44, 130));

                for (int i = 0; i < latLngs.size(); i++) {
                    mapboxMap.addMarker(new MarkerOptions().position(latLngs.get(i)).icon(icon));
                }

                mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(@NonNull Marker marker) {
                        startActivity(new Intent(getActivity(), FeedbackActivity.class));
                        return false;
                    }
                });
            }
        });

        userId = CacheUtils.getString(getActivity(), Constants.ID);
        //数据库初始化
        shipDetailsDaoUtil = new ShipDetailsDaoUtil(getActivity());
        if (shipDetailsDaoUtil.queryAllShipDetailsBean().size() < 6) {
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 1, "君航号", "中国", "mmsi001", "超大型货船"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 2, "中国号", "中国", "mmsi002", "大船"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 3, "辽宁号航空母舰", "中国", "mmsi003", "航空母舰"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 4, "远洋号", "中国", "mmsi004", "客船"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 5, "长征号", "中国", "mmsi005", "货船"));
            shipDetailsDaoUtil.insertShipDetailsBean(new ShipDetailsBean((long) 6, "华海号", "中国", "mmsi006", "货船"));
        }

        // 关注界面带搜索关键字跳转过来
        boolean isFromFollow = CacheUtils.getBoolean(getActivity(), Constants.SEARCHSHIPNAME, false);
        if (isFromFollow) {
            //清除在关注界面的搜索记录
            getActivity().getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().remove(Constants.SEARCHSHIPNAME).commit();
            Intent intent = getActivity().getIntent();
            String searchShipNameFromFollowShip = intent.getStringExtra(Constants.SEARCHSHIPNAME);
            etSearch.setText("");
//            searchResultByInput(etSearch.getText().toString());
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            etSearch.setFocusableInTouchMode(true);
            etSearch.requestFocus();
            imm.showSoftInput(etSearch, InputMethodManager.SHOW_FORCED);

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

        //搜索框聚焦监听
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

        mapboxMapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                initSearchView();
                return false;
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

    private void initSearchView() {
        etSearch.setText("");
        llSearch.setVisibility(View.GONE);
        etSearch.clearFocus();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            etSearch.setText("");
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
            SearchResultAdapter searchResultAdapter = new SearchResultAdapter(getActivity(), shipDetailsLists, llSearch, etSearch);
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


    private void showDialogClearBuffer() {
        View inflate = View.inflate(getActivity(), R.layout.dialog_one_button, null);
        TextView tv_ok_clear_butter = (TextView) inflate.findViewById(R.id.tv_ok_clear_butter);

        tv_ok_clear_butter.setOnClickListener(this);

        dialog = new Dialog(getActivity(), R.style.style_dialog);
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


        mSensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);

        mapboxMapView.setStyleUrl(ConstantsUrls.SEA_MAP);

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
//                        LatLng latLng = new LatLng(39.9,116.3);
//                        mapboxMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        final String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
                        PackageManager packageManager = getActivity().getPackageManager();
                        boolean permission = PackageManager.PERMISSION_GRANTED == packageManager.checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, "com.junhangxintong.chuanzhangtong");
                        if (permission) {
                            mapboxMap.setMyLocationEnabled(true);
                            double latitude = mapboxMap.getMyLocation().getLatitude();
                            double longitude = mapboxMap.getMyLocation().getLongitude();
                            LatLng latLng = new LatLng(latitude, longitude);
                            mapboxMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                            mapboxMap.setZoom(16);
                        } else {
                           /* Toast.makeText(getActivity(), getResources().getString(R.string.location_failure), Toast.LENGTH_SHORT).show();
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
                            });*/
                            showDialogClearBuffer();
                        }
                    }
                });
            }
        });
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
    }

    @Override
    public void onResume() {
        super.onResume();

        mapboxMapView.onResume();

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
        if(mapboxMapView!=null) {
            mapboxMapView.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mapboxMapView != null) {
            mapboxMapView.onStop();
        }
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(mapboxMapView!=null) {
            mapboxMapView.onLowMemory();
        }
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
                gotoMeasureDistanceActivity();
                break;
            case R.id.tv_clear_history:
                historyLists.clear();
                historyListAdapter.notifyDataSetChanged();
                llHistory.setVisibility(View.GONE);
                break;
        }
    }

    private void gotoMeasureDistanceActivity() {
        Intent intent = new Intent(getActivity(), MeasureDistanceActivity.class);
        intent.putExtra(Constants.MAP_STYLE, mapStyle);
        startActivity(intent);
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
                mapStyle = 1;
                mapboxMapView.setStyleUrl(ConstantsUrls.SEA_MAP);
                popupWindow.dismiss();
                tvShipPositionTu.setBackgroundResource(R.drawable.iv_haitu);
                break;
            case R.id.ll_land_map:
                mapStyle = 2;
                mapboxMapView.setStyleUrl(ConstantsUrls.GOOGLE_MAP);
                popupWindow.dismiss();
                tvShipPositionTu.setBackgroundResource(R.drawable.iv_ditu);
                break;
            case R.id.ll_satellite_map:
                mapStyle = 3;
                mapboxMapView.setStyleUrl(ConstantsUrls.SATELLITE_MAP);
                popupWindow.dismiss();
                tvShipPositionTu.setBackgroundResource(R.drawable.iv_weixingtu);
                break;
            case R.id.tv_ok_clear_butter:
                getAppDetailSettingIntent(getActivity());
                dialog.dismiss();
                break;
        }
    }

    @OnClick(R.id.iv_ship_position_location)
    public void onViewClicked() {
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private void netGetShipLists(String shipName) {
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.MY_SHIP_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.SHIP_NAME, shipName)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onDataEmpty(String message) {
                        super.onDataEmpty(message);
                        Toast.makeText(getActivity(), getResources().getString(R.string.no_ship), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onSuccess(String response, String message) {
                        ShipListBean shipListBean = new Gson().fromJson(response, ShipListBean.class);
                        shipLists = shipListBean.getData().getArray();

                        showPopMyFleet(shipLists);
                    }
                });
    }
}
