package com.junhangxintong.chuanzhangtong.shipposition.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.FollowShipListBean;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.OtherShipDetailsActivity;
import com.junhangxintong.chuanzhangtong.shipposition.bean.ShipDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

/**
 * Created by anwanfei on 2017/8/4.
 */

public class SearchResultAdapter extends BaseAdapter {
    private Context mContext;
    private List<ShipDetailsBean> shipDetailsBeanList;
    private boolean isFollowed;
    private String userId = "";
    private ArrayList<String> followShipIds = new ArrayList<String>();
    private String shipName;

    public SearchResultAdapter(Context mContext, List<ShipDetailsBean> shipDetailsBeanList) {
        this.mContext = mContext;
        this.shipDetailsBeanList = shipDetailsBeanList;
        userId = CacheUtils.getString(mContext, Constants.ID);
    }

    @Override
    public int getCount() {
        return shipDetailsBeanList.size();
    }

    @Override
    public Object getItem(int i) {
        return shipDetailsBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        //1、创建或获取viewHolder
        ViewHolder holder = null;
        if (convertView == null) {

            //加载条目布局
            convertView = View.inflate(mContext, R.layout.item_ship_position_search, null);
            //创建holder对象
            holder = new ViewHolder(convertView);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        ShipDetailsBean shipDetailsBean = shipDetailsBeanList.get(i);
        String mmsi = shipDetailsBean.getMmsi();
        shipName = shipDetailsBean.getShipName();
        String shipId = String.valueOf(shipDetailsBean.getShipId());

        netGetFollowFleetList(shipName, shipId, holder.tvSearchFollow, holder.tvSearchShipDetails, holder.rlSearchResult, i);

        holder.tvSearchShipName.setText(shipName);
        holder.tvSearchShipMmsi.setText(mContext.getResources().getString(R.string.MMSI_withColon) + mmsi);

        final ViewHolder finalHolder = holder;
        holder.tvSearchFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Long shipId = shipDetailsBeanList.get(i).getShipId();

                String token = CacheUtils.getString(mContext, Constants.TOKEN);

                if (token.equals("")) {
                    mContext.startActivity(new Intent(mContext, LoginRegisterActivity.class));
                } else {
                    if (isFollowed) {//已经关注
                        NetUtils.postWithHeader(mContext, ConstantsUrls.CANCEL_FOLLOW_SHIP)
                                .addParams(Constants.SHIP_ID, String.valueOf(shipId))
                                .addParams(Constants.USER_ID, userId)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Toast.makeText(mContext, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        if (response == null || response.equals("") || response.equals("null")) {
                                            Toast.makeText(mContext, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                                        } else {
                                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                                            String message = sendVerifyCode.getMessage();
                                            String code = sendVerifyCode.getCode();
                                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                            if (code.equals("601")) {
                                                //清除了sp存储
                                                mContext.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                                //保存获取权限的sp
                                                CacheUtils.putBoolean(mContext, Constants.IS_NEED_CHECK_PERMISSION, false);
                                                mContext.startActivity(new Intent(mContext, LoginRegisterActivity.class));
                                            }
                                            if (code.equals("200")) {
                                                isFollowed = false;
                                                finalHolder.tvSearchFollow.setText(mContext.getResources().getString(R.string.follow));
                                                finalHolder.tvSearchFollow.setBackgroundResource(R.drawable.tv_frame_blue_bg);
                                                finalHolder.tvSearchFollow.setTextColor(mContext.getResources().getColor(R.color.blue));
                                            }
                                        }
                                    }
                                });

                    } else {//没有关注
                        NetUtils.postWithHeader(mContext, ConstantsUrls.ADD_FOLLOW_SHIP)
                                .addParams(Constants.SHIP_ID, String.valueOf(shipId))
                                .addParams(Constants.USER_ID, userId)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Toast.makeText(mContext, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        if (response == null || response.equals("") || response.equals("null")) {
                                            Toast.makeText(mContext, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                                        } else {
                                            SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                                            String message = sendVerifyCode.getMessage();
                                            String code = sendVerifyCode.getCode();
                                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                            if (code.equals("601")) {
                                                //清除了sp存储
                                                mContext.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                                //保存获取权限的sp
                                                CacheUtils.putBoolean(mContext, Constants.IS_NEED_CHECK_PERMISSION, false);
                                                mContext.startActivity(new Intent(mContext, LoginRegisterActivity.class));
                                            }
                                            if (code.equals("200")) {
                                                isFollowed = true;
                                                finalHolder.tvSearchFollow.setText(mContext.getResources().getString(R.string.cancel_follow));
                                                finalHolder.tvSearchFollow.setBackgroundResource(R.drawable.tv_frame_gray_bg);
                                                finalHolder.tvSearchFollow.setTextColor(mContext.getResources().getColor(R.color.gray_identity));
                                            }
                                        }
                                    }
                                });
                    }
                }
            }
        });

        //返回view
        return convertView;
    }

    private void netGetFollowFleetList(String shipName, final String shipId, final TextView tvSearchFollow, final TextView tvSearchShipDetails, final RelativeLayout rlSearchResult, final int i) {
        NetUtils.postWithHeader(mContext, ConstantsUrls.FOLLOW_SHIP_LIST)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.SHIP_NAME, shipName)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(mContext, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(mContext, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                FollowShipListBean followShipListBean = new Gson().fromJson(response, FollowShipListBean.class);
                                List<FollowShipListBean.DataBean.ArrayBean> followShipLists = followShipListBean.getData().getArray();

                                for (int i = 0; i < followShipLists.size(); i++) {
                                    followShipIds.add(String.valueOf(followShipLists.get(i).getShipId()));
                                }
                                if (followShipIds.size() > 0) {
                                    if (followShipIds.contains(shipId)) {
                                        tvSearchFollow.setText(mContext.getResources().getString(R.string.cancel_follow));
                                        tvSearchFollow.setBackgroundResource(R.drawable.tv_frame_gray_bg);
                                        tvSearchFollow.setTextColor(mContext.getResources().getColor(R.color.gray_identity));
                                        isFollowed = true;
                                    }
                                }

                            } else if (code.equals("601")) {
                            } else if (code.equals("404")) {
                            } else {
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                            }
                        }

                        tvSearchShipDetails.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String shipId = String.valueOf(shipDetailsBeanList.get(i).getShipId());
                                Intent intent = new Intent(mContext, OtherShipDetailsActivity.class);
                                intent.putExtra(Constants.ID, shipId);
                                if (followShipIds.size() > 0) {
                                    if (followShipIds.contains(shipId)) {
                                        intent.putExtra(Constants.FOLLOW_SHIP, true);
                                    }
                                }
                                intent.putExtra(Constants.SHIP_ID, shipId);
                                mContext.startActivity(intent);
                            }
                        });
                        rlSearchResult.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String shipId = String.valueOf(shipDetailsBeanList.get(i).getShipId());
                                Intent intent = new Intent(mContext, OtherShipDetailsActivity.class);
                                intent.putExtra(Constants.ID, shipId);
                                if (followShipIds.size() > 0) {
                                    if (followShipIds.contains(shipId)) {
                                        intent.putExtra(Constants.FOLLOW_SHIP, true);
                                    }
                                }
                                intent.putExtra(Constants.SHIP_ID, shipId);
                                mContext.startActivity(intent);
                            }
                        });
                    }
                });
    }

    static class ViewHolder {
        @BindView(R.id.tv_search_ship_name)
        TextView tvSearchShipName;
        @BindView(R.id.tv_search_ship_mmsi)
        TextView tvSearchShipMmsi;
        @BindView(R.id.tv_search_update_time)
        TextView tvSearchUpdateTime;
        @BindView(R.id.tv_search_ship_details)
        TextView tvSearchShipDetails;
        @BindView(R.id.tv_search_follow)
        TextView tvSearchFollow;
        @BindView(R.id.rl_search_result)
        RelativeLayout rlSearchResult;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
