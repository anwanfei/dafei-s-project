package com.junhangxintong.chuanzhangtong.mine.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.activity.AccoutSettingActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.CrewManagementActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.FeedbackActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.MyFleetListActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.MyFollowFleetActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.PersonalInfoActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.LoginResultBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.CircleImageView;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by anwanfei on 2017/7/5.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.iv_userinfo_bg)
    ImageView ivUserinfoBg;
    @BindView(R.id.iv_photo)
    CircleImageView ivPhoto;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_identity)
    TextView tvIdentity;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.rl_chuanduiguanli)
    RelativeLayout rlChuanduiguanli;
    @BindView(R.id.rl_chuanyuanguanli)
    RelativeLayout rlChuanyuanguanli;
    @BindView(R.id.rl_account_setting)
    RelativeLayout rlAccountSetting;
    Unbinder unbinder;
    @BindView(R.id.rl_chuanguan)
    RelativeLayout rlChuanguan;
    @BindView(R.id.rl_follew_fleet)
    RelativeLayout rlFollewFleet;
    private LoginResultBean loginResult;
    private String token;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_my, null);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = CacheUtils.getString(getActivity(), Constants.TOKEN);

        if (token.equals("")) {
            startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
            getActivity().finish();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        if (!token.equals("")) {
            getPersonalInfoFromNet();
        }
    }

    private void getPersonalInfoFromNet() {
        String userId = CacheUtils.getString(getActivity(), Constants.ID);
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.GET_USER_INFO)
                .addParams(Constants.USER_ID, userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(getActivity(), Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrortBean = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String code = netServiceErrortBean.getCode();
                            if (code.equals("200")) {
                                loginResult = new Gson().fromJson(response, LoginResultBean.class);
                                String personName = loginResult.getData().getObject().getPersonName();
                                String roleName = loginResult.getData().getObject().getRoleName();
                                if (personName.equals("")) {
                                    tvUserName.setText(loginResult.getData().getObject().getMobilePhone());
                                } else {
                                    tvUserName.setText(personName);
                                }
                                tvIdentity.setText(roleName);
                            } else if (code.equals("601")) {
                                Toast.makeText(getActivity(), netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                            } else {
                                Toast.makeText(getActivity(), netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        if (true) {
            rlChuanguan.setVisibility(View.VISIBLE);
        } else {
            startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        String userName = CacheUtils.getString(getActivity(), Constants.USER_NAME);
        String roleId = CacheUtils.getString(getActivity(), Constants.ROLEID);
        //获取拍的照片
        String path = Environment.getExternalStorageDirectory() + Constants.PHONE_PATH;
        File file = new File(path);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            ivPhoto.setImageBitmap(bitmap);
        } else {
            ivPhoto.setBackgroundDrawable(getResources().getDrawable(R.drawable.photo));
        }
        if (!userName.isEmpty()) {
            tvUserName.setText(userName);
        } else {
            tvUserName.setText(getResources().getString(R.string.name));
        }

        switch (roleId) {
            case "1":
                tvIdentity.setText(Constants.ROLE_NAME[0]);
                break;
            case "2":
                tvIdentity.setText(Constants.ROLE_NAME[1]);
                break;
            case "3":
                tvIdentity.setText(Constants.ROLE_NAME[2]);
                break;
            case "4":
                tvIdentity.setText(Constants.ROLE_NAME[3]);
                break;
        }
    }

    @OnClick({R.id.iv_userinfo_bg, R.id.iv_photo, R.id.tv_user_name, R.id.tv_identity, R.id.rl_user_info, R.id.rl_feed_back,
            R.id.rl_chuanduiguanli, R.id.rl_follew_fleet, R.id.rl_chuanyuanguanli, R.id.rl_account_setting, R.id.rl_chuanguan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_userinfo_bg:
                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                break;
            case R.id.iv_photo:
                gotoPersonalInfoActivity();
                break;
            case R.id.tv_user_name:
                break;
            case R.id.tv_identity:
                break;
            case R.id.rl_user_info:
                break;
            case R.id.rl_chuanduiguanli:
                startActivity(new Intent(getActivity(), MyFleetListActivity.class));
                break;
            case R.id.rl_chuanyuanguanli:
                startActivity(new Intent(getActivity(), CrewManagementActivity.class));
                break;
            case R.id.rl_account_setting:
                startActivity(new Intent(getActivity(), AccoutSettingActivity.class));
                break;
            case R.id.rl_chuanguan:
                break;
            case R.id.rl_follew_fleet:
                startActivity(new Intent(getActivity(), MyFollowFleetActivity.class));
                break;
            case R.id.rl_feed_back:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
        }
    }

    private void gotoPersonalInfoActivity() {
        Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
        intent.putExtra(Constants.USER_INFO, loginResult);
        startActivity(intent);
    }
}
