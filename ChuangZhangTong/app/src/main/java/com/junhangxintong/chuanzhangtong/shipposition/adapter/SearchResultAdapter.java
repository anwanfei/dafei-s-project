package com.junhangxintong.chuanzhangtong.shipposition.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.shipposition.activity.OtherShipDetailsActivity;
import com.junhangxintong.chuanzhangtong.shipposition.bean.ShipDetailsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

    public SearchResultAdapter(Context mContext, List<ShipDetailsBean> shipDetailsBeanList) {
        this.mContext = mContext;
        this.shipDetailsBeanList = shipDetailsBeanList;
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
            //创建holder对象
            holder = new ViewHolder();
            //加载条目布局
            convertView = View.inflate(mContext, R.layout.item_ship_position_search, null);

            //找控件
            holder.tvSearchShipName = (TextView) convertView.findViewById(R.id.tv_search_ship_name);
            holder.tvSearchShipMmsi = (TextView) convertView.findViewById(R.id.tv_search_ship_mmsi);
            holder.tvSearchShipDetails = (TextView) convertView.findViewById(R.id.tv_search_ship_details);
            holder.tvSearchFollow = (TextView) convertView.findViewById(R.id.tv_search_follow);

            //保存holder
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //2、获取当前的item数据
        ShipDetailsBean shipDetailsBean = shipDetailsBeanList.get(i);
        String mmsi = shipDetailsBean.getMmsi();
        String shipName = shipDetailsBean.getShipName();

        holder.tvSearchShipName.setText(shipName);
        holder.tvSearchShipMmsi.setText(mContext.getResources().getString(R.string.MMSI_withColon) + mmsi);

        holder.tvSearchShipDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, OtherShipDetailsActivity.class));
            }
        });
        final ViewHolder finalHolder = holder;
        holder.tvSearchFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Long shipId = shipDetailsBeanList.get(i).getShipId();
                String userId = CacheUtils.getString(mContext, Constants.ID);
                String token = CacheUtils.getString(mContext, Constants.TOKEN);

                if (token.equals("")) {
                    mContext.startActivity(new Intent(mContext, LoginRegisterActivity.class));
                } else {
                    if (isFollowed) {//已经关注
                        NetUtils.postWithHeader(mContext,ConstantsUrls.DELETE_FOLLOW_SHIP)
                                .addParams(Constants.IDS,String.valueOf(shipId))
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
                                                finalHolder.tvSearchFollow.setText(mContext.getResources().getString(R.string.followed));
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        ViewHolder() {
        }
    }
}
