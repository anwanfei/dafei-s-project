package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.content.Context;
import android.content.Intent;
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
import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.FollowShipDetailsBean;
import com.junhangxintong.chuanzhangtong.shipposition.bean.MyShipInfoBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DateUtil;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.R.id.tv_follow_ship;
import static com.junhangxintong.chuanzhangtong.R.id.tv_share_ship;
import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

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
    private boolean isFollow;
    private String shipId = "";
    private String userId;
    private boolean isFollowed = true;
    private String id;

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_detils));
        ivShare.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.iv_menu);
    }

    @Override
    protected void initData() {
        userId = CacheUtils.getString(this, Constants.ID);
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
        isFollow = intent.getBooleanExtra(Constants.FOLLOW_SHIP, false);
        shipId = intent.getStringExtra(Constants.SHIP_ID);

        if (!isFollow) {
            NetUtils.postWithHeader(this, ConstantsUrls.MY_SHIP_INFO)
                    .addParams(Constants.ID, id)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(OtherShipDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(OtherShipDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                                String message = netServiceErrort.getMessage();
                                String code = netServiceErrort.getCode();
                                if (code.equals("200")) {
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
                                } else if (code.equals("601")) {
                                    //清除了sp存储
                                    getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                    //保存获取权限的sp
                                    CacheUtils.putBoolean(OtherShipDetailsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                    startActivity(new Intent(OtherShipDetailsActivity.this, LoginRegisterActivity.class));
                                    finish();
                                } else {
//                                    Toast.makeText(OtherShipDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        } else {
            NetUtils.postWithHeader(this, ConstantsUrls.FOLLOW_SHIP_INFO)
                    .addParams(Constants.ID, id)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(OtherShipDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(OtherShipDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                                String message = netServiceErrort.getMessage();
                                String code = netServiceErrort.getCode();
                                if (code.equals("200")) {
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

                                } else if (code.equals("601")) {
                                    //清除了sp存储
                                    getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                    //保存获取权限的sp
                                    CacheUtils.putBoolean(OtherShipDetailsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                    startActivity(new Intent(OtherShipDetailsActivity.this, LoginRegisterActivity.class));
                                    finish();
                                } else {
//                                    Toast.makeText(OtherShipDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }
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
                Toast.makeText(OtherShipDetailsActivity.this, getResources().getString(R.string.developing), Toast.LENGTH_SHORT).show();
                break;
        }
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
        popupWindow.showAsDropDown(ivShare, 0, 0);

        tv_follow_ship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                isShowShareAndFollow = true;
                if (isFollow) {
                    netCannelFollowShip(tv_follow_ship);
                } else {
                    netFollowShip(tv_follow_ship);
                }
            }
        });
        tv_share_ship.setOnClickListener(this);
    }

    private void netCannelFollowShip(final TextView tv_follow_ship) {
        NetUtils.postWithHeader(OtherShipDetailsActivity.this, ConstantsUrls.CANCEL_FOLLOW_SHIP)
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.USER_ID, userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(OtherShipDetailsActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(OtherShipDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(OtherShipDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                OtherShipDetailsActivity.this.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(OtherShipDetailsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                OtherShipDetailsActivity.this.startActivity(new Intent(OtherShipDetailsActivity.this, LoginRegisterActivity.class));
                            }
                            if (code.equals("200")) {
                                isFollow = false;
                                tv_follow_ship.setText(OtherShipDetailsActivity.this.getResources().getString(R.string.follow));
                            }
                        }
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
                Toast.makeText(OtherShipDetailsActivity.this, getResources().getString(R.string.developing), Toast.LENGTH_SHORT).show();
                // TODO: 2017/8/8

                break;
        }
    }

    private void netFollowShip(final TextView textView) {
        NetUtils.postWithHeader(this, ConstantsUrls.ADD_FOLLOW_SHIP)
                .addParams(Constants.SHIP_ID, shipId)
                .addParams(Constants.USER_ID, userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(OtherShipDetailsActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(OtherShipDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                            String message = sendVerifyCode.getMessage();
                            String code = sendVerifyCode.getCode();
                            Toast.makeText(OtherShipDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                OtherShipDetailsActivity.this.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(OtherShipDetailsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                OtherShipDetailsActivity.this.startActivity(new Intent(OtherShipDetailsActivity.this, LoginRegisterActivity.class));
                            }
                            if (code.equals("200")) {
                                isFollow = true;
                                textView.setText(OtherShipDetailsActivity.this.getResources().getString(R.string.cancel_follow));
//                                textView.setBackgroundResource(R.drawable.tv_frame_gray_bg);
                                textView.setTextColor(OtherShipDetailsActivity.this.getResources().getColor(R.color.gray_identity));
                            }
                        }
                    }
                });
    }


}
