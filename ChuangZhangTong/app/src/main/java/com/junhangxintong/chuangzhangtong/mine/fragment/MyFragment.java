package com.junhangxintong.chuangzhangtong.mine.fragment;

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

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;
import com.junhangxintong.chuangzhangtong.mine.activity.AccoutSettingActivity;
import com.junhangxintong.chuangzhangtong.mine.activity.CrewManagementActivity;
import com.junhangxintong.chuangzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuangzhangtong.mine.activity.MyFleetActivity;
import com.junhangxintong.chuangzhangtong.mine.activity.PersonalInfoActivity;
import com.junhangxintong.chuangzhangtong.utils.CacheUtils;
import com.junhangxintong.chuangzhangtong.utils.CircleImageView;
import com.junhangxintong.chuangzhangtong.utils.Constants;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by edz on 2017/7/5.
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

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_my, null);
        return view;
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
        //获取拍的照片
        String path = Environment.getExternalStorageDirectory() + Constants.PHONE_PATH;
        File file = new File(path);
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            ivPhoto.setImageBitmap(bitmap);
        }
        if (userName != null) {
            tvUserName.setText(userName);
        }
    }

    @OnClick({R.id.iv_userinfo_bg, R.id.iv_photo, R.id.tv_user_name, R.id.tv_identity, R.id.rl_user_info, R.id.rl_chuanduiguanli, R.id.rl_chuanyuanguanli, R.id.rl_account_setting, R.id.rl_chuanguan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_userinfo_bg:
                break;
            case R.id.iv_photo:
                startActivity(new Intent(getActivity(), PersonalInfoActivity.class));
                break;
            case R.id.tv_user_name:
                break;
            case R.id.tv_identity:
                break;
            case R.id.rl_user_info:
                break;
            case R.id.rl_chuanduiguanli:
                startActivity(new Intent(getActivity(), MyFleetActivity.class));
                break;
            case R.id.rl_chuanyuanguanli:
                startActivity(new Intent(getActivity(), CrewManagementActivity.class));
                break;
            case R.id.rl_account_setting:
                startActivity(new Intent(getActivity(), AccoutSettingActivity.class));
                break;
            case R.id.rl_chuanguan:
                break;
        }
    }
}
