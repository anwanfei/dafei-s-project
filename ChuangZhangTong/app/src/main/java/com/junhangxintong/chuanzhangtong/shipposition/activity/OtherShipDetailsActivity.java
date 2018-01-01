package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.shipposition.bean.FollowShipDetailsBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.MyShipInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.utils.ShareUtils;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.junhangxintong.chuanzhangtong.R.id.tv_follow_ship;
import static com.junhangxintong.chuanzhangtong.R.id.tv_share_ship;

public class OtherShipDetailsActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
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
    @BindView(R.id.mapbox_mapView)
    MapView mapboxMapView;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_re_sail)
    TextView tvReSail;
    @BindView(R.id.ll_hide_trajactory)
    LinearLayout llHideTrajactory;
    private boolean isShowShipOthorDetails = true;
    private boolean isShowShareAndFollow = true;
    private PopupWindow popupWindow;
    private boolean isFollow;
    private String shipId = "";
    private String userId;
    private boolean isFollowed = true;
    private AlertDialog show;

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_detils));
        ivShare.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.iv_menu);
    }

    @Override
    protected void initData() {

        mapboxMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                curShipIcon(mapboxMap);
            }
        });


        userId = CacheUtils.getString(this, Constants.ID);
        Intent intent = getIntent();
        String idd = intent.getStringExtra(Constants.ID);
        shipId = intent.getStringExtra(Constants.SHIP_ID);
        isFollow = intent.getBooleanExtra(Constants.FOLLOW_SHIP, false);

        if (!isFollow) {
            NetUtils.postWithHeader(this, ConstantsUrls.MY_SHIP_INFO)
                    .addParams(Constants.ID, idd)
                    .build()
                    .execute(new NetUtils.MyStringCallback() {
                        @Override
                        protected void onSuccess(String response, String message) {
                            MyShipInfoBean myShipInfoBean = new Gson().fromJson(response, MyShipInfoBean.class);
                            MyShipInfoBean.DataBean.ObjectBean shipInfo = myShipInfoBean.getData().getObject();
                            tvTitle.setText(shipInfo.getShipName());
                            tvShipName.setText(shipInfo.getShipName());
                            tvChuanji.setText(shipInfo.getNation());
                            tvMmsi.setText(shipInfo.getMmsi());
                            tvShipHuhao.setText(shipInfo.getCallSign());
                            tvShipImo.setText(shipInfo.getImo());
                            tvShipType.setText(shipInfo.getType());
                            tvShipZize.setText((shipInfo.getShipSize() / 100) + "/" + (shipInfo.getShipWidth() / 100));
//                                    tvUpdateTime.setText(shipInfo.getModifyDate());
                            tvUpdateTime.setText(DateUtil.getCurrentTimeYMDHMS());
                        }
                    });
        } else {
            NetUtils.postWithHeader(this, ConstantsUrls.FOLLOW_SHIP_INFO)
                    .addParams(Constants.ID, idd)
                    .build()
                    .execute(new NetUtils.MyStringCallback() {
                        @Override
                        protected void onSuccess(String response, String message) {
                            FollowShipDetailsBean followShipDetailsBean = new Gson().fromJson(response, FollowShipDetailsBean.class);
                            FollowShipDetailsBean.DataBean.ObjectBean followShipDetails = followShipDetailsBean.getData().getObject();
                            tvShipName.setText(followShipDetails.getShipName());
                            tvShipImo.setText(followShipDetails.getImo());
                            tvShipZize.setText(followShipDetails.getShipSize() / 100 + "/" + followShipDetails.getShipWidth() / 100);
                            tvUpdateTime.setText(followShipDetails.getModifyDate());
                            tvMmsi.setText(followShipDetails.getMmsi());
                            tvShipType.setText(followShipDetails.getType());
                            tvChuanji.setText(followShipDetails.getNation());
                            tvShipHuhao.setText(followShipDetails.getCallSign());
                        }
                    });
        }
    }

    private void curShipIcon(MapboxMap mapboxMap) {
        IconFactory instance = IconFactory.getInstance(OtherShipDetailsActivity.this);
        Icon icon = instance.fromResource(R.drawable.ic_my_ship_run);
        LatLng latLng = new LatLng(40, 116);
        mapboxMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mapboxMap.addMarker(new MarkerOptions().position(latLng).icon(icon));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                showShipTrajactory();
                break;
            case R.id.tv_back:
                ivTrajactory.setVisibility(View.VISIBLE);
                llHideTrajactory.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void showShipTrajactory() {
        ivTrajactory.setVisibility(View.INVISIBLE);
        llHideTrajactory.setVisibility(View.VISIBLE);
        mapboxMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {

                List<LatLng> latLngs = new ArrayList<LatLng>();
                latLngs.add(new LatLng(41, 116));
                latLngs.add(new LatLng(42, 117));
                latLngs.add(new LatLng(43, 120));
                latLngs.add(new LatLng(44, 150));

                for (int i = 0; i < latLngs.size(); i++) {
                    mapboxMap.addMarker(new MarkerOptions().position(latLngs.get(i)));
                }
                if (latLngs.size() > 1) {
                    mapboxMap.addPolyline(new PolylineOptions().addAll(latLngs).width(2).color(Color.argb(255, 255, 92, 92)));
                } else {
                    Toast.makeText(OtherShipDetailsActivity.this, "船舶没有轨迹", Toast.LENGTH_SHORT).show();
                }

                tvBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ivTrajactory.setVisibility(View.VISIBLE);
                        llHideTrajactory.setVisibility(View.INVISIBLE);

                        mapboxMap.getMarkers().clear();
                        mapboxMap.clear();
                        curShipIcon(mapboxMap);
                    }
                });
            }
        });
    }

    private void showShareAndFollowShipPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ship_details_menu, null);
        final TextView tv_follow_ship = (TextView) view.findViewById(R.id.tv_follow_ship);
        TextView tv_share_ship = (TextView) view.findViewById(R.id.tv_share_ship);
        if (isFollow) {
            tv_follow_ship.setText(getResources().getString(R.string.cancel_follow));
        }

        popupWindow = new PopupWindow(view, DensityUtil.dp2px(this, 120), LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setContentView(view);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(ivShare, 0, 0);

        tv_follow_ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                isShowShareAndFollow = true;
                if (isFollow) {
                    showDialogCnncelFollowShip(tv_follow_ship);
                } else {
                    netFollowShip(tv_follow_ship);
                }
            }
        });
        tv_share_ship.setOnClickListener(this);
    }


    private void showDialogCnncelFollowShip(final TextView tv_follow_ship) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_clear_butter, null);
        TextView tv_cancel_clear_buffer = (TextView) view.findViewById(R.id.tv_cancel_clear_buffer);
        TextView tv_ok_clear_butter = (TextView) view.findViewById(R.id.tv_ok_clear_butter);
        TextView tv_dialog_title = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_message = (TextView) view.findViewById(R.id.tv_dialog_message);

        tv_dialog_title.setText(getResources().getString(R.string.conform_cancel_follow_the_ship));
        tv_dialog_message.setVisibility(View.GONE);

        tv_cancel_clear_buffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
            }
        });
        tv_ok_clear_butter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                netCannelFollowShip(tv_follow_ship);
                show.dismiss();
            }
        });

        AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.style_dialog);
        dialog.setView(view);
        show = dialog.show();
    }

    private void netCannelFollowShip(final TextView tv_follow_ship) {
        NetUtils.postWithHeader(OtherShipDetailsActivity.this, ConstantsUrls.CANCEL_FOLLOW_SHIP)
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.USER_ID, userId)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        isFollow = false;
                        tv_follow_ship.setText(OtherShipDetailsActivity.this.getResources().getString(R.string.follow_ship));
                        Toast.makeText(OtherShipDetailsActivity.this, getResources().getString(R.string.cancel_follow), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case tv_follow_ship:
                // TODO: 2017/8/8
                break;

            case tv_share_ship:
                popupWindow.dismiss();
                isShowShareAndFollow = true;
                share();

                break;
        }
    }

    private void share() {
        UMImage image = new UMImage(OtherShipDetailsActivity.this, R.drawable.wx108);//资源文件

        UMWeb web = new UMWeb(Constants.SHARE_URL);
        web.setTitle(getResources().getString(R.string.app_name));//标题
        web.setThumb(image);  //缩略图
        web.setDescription(getResources().getString(R.string.app_description));//描述

        ShareUtils.share(OtherShipDetailsActivity.this, web);
    }

    private void netFollowShip(final TextView textView) {
        NetUtils.postWithHeader(this, ConstantsUrls.ADD_FOLLOW_SHIP)
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.USER_ID, userId)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        isFollow = true;
                        textView.setTextColor(OtherShipDetailsActivity.this.getResources().getColor(R.color.gray_identity));
                        textView.setText(OtherShipDetailsActivity.this.getResources().getString(R.string.cancel_follow));
                        Toast.makeText(OtherShipDetailsActivity.this, getResources().getString(R.string.follow_sucess), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
