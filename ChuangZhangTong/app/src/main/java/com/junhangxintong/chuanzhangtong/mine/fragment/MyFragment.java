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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.mine.activity.AccoutSettingActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.CrewCertificateListsActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.CrewManagementActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.FeedbackActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.MyCertificateActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.MyFleetListActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.MyFollowFleetActivity;
import com.junhangxintong.chuanzhangtong.mine.activity.PersonalInfoActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.LoginResultBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.CircleImageView;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import org.apache.commons.lang.StringUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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
    @BindView(R.id.rl_my_certificate)
    RelativeLayout rlMyCertificate;
    @BindView(R.id.view_my_fleet)
    View viewMyFleet;
    @BindView(R.id.view_line2)
    View viewLine2;
    @BindView(R.id.tv_my_fleet)
    TextView tvMyFleet;
    private LoginResultBean loginResult;
    private String token;
    private String roleId;


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
        roleId = CacheUtils.getString(getActivity(), Constants.ROLEID);

        if (StringUtils.isEmpty(token)) {
            startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
            getActivity().finish();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        if (StringUtils.isNotBlank(token)) {
//            getPersonalInfoFromNet();
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        ivUserinfoBg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
                return false;
            }
        });
    }

    private void getPersonalInfoFromNet() {
        String userId = CacheUtils.getString(getActivity(), Constants.ID);
        NetUtils.postWithHeader(getActivity(), ConstantsUrls.GET_USER_INFO)
                .addParams(Constants.USER_ID, userId)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        loginResult = new Gson().fromJson(response, LoginResultBean.class);
                        String headImgUrl = loginResult.getData().getObject().getHeadImgUrl();
                        String personName = loginResult.getData().getObject().getPersonName();
                        String roleName = loginResult.getData().getObject().getRoleName();
                        if (StringUtils.isEmpty(personName)) {
                            tvUserName.setText(getResources().getString(R.string.name));
                        } else {
                            tvUserName.setText(personName);
                        }
                        tvIdentity.setText(roleName);

                        if (StringUtils.isNotEmpty(headImgUrl)) {
                            Glide.with(getActivity())
                                    .load(headImgUrl)
                                    .into(ivPhoto);
                        } else {
                            ivPhoto.setBackgroundDrawable(getResources().getDrawable(R.drawable.photo));
                        }

                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflatoe a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        /*if (StringUtils.isNotBlank(token)) {
            rlChuanguan.setVisibility(View.VISIBLE);
        } else {
            startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
        }*/
        if (roleId.equals("4")) {
            rlChuanduiguanli.setVisibility(View.GONE);
            viewMyFleet.setVisibility(View.GONE);
            viewLine2.setVisibility(View.GONE);
            rlChuanyuanguanli.setVisibility(View.GONE);
        }
        if (roleId.equals("3")) {
            tvMyFleet.setText(getResources().getString(R.string.my_ship));
            rlChuanyuanguanli.setVisibility(View.GONE);
            viewLine2.setVisibility(View.GONE);
        }

        if (roleId.equals("2")) {
            tvMyFleet.setText(getResources().getString(R.string.my_ship));
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
        token = CacheUtils.getString(getActivity(), Constants.TOKEN);

        if (StringUtils.isNotBlank(token)) {
            getPersonalInfoFromNet();

            String userName = CacheUtils.getString(getActivity(), Constants.USER_NAME);
            String roleId = CacheUtils.getString(getActivity(), Constants.ROLEID);
            String personNameFromNet = "";
            String headImgUrl = "";
            if (loginResult != null) {
                headImgUrl = loginResult.getData().getObject().getHeadImgUrl();
                personNameFromNet = loginResult.getData().getObject().getPersonName();
            }
            //获取拍的照片
            String path = Environment.getExternalStorageDirectory() + Constants.PHONE_PATH;
            File file = new File(path);
            if (file.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                ivPhoto.setImageBitmap(bitmap);
            } else if (StringUtils.isNotEmpty(headImgUrl)) {
                Glide.with(getActivity())
                        .load(headImgUrl)
                        .into(ivPhoto);
            } else {
                ivPhoto.setBackgroundDrawable(getResources().getDrawable(R.drawable.photo));
            }
            if (StringUtils.isNotEmpty(userName)) {
                tvUserName.setText(userName);
            } else if (StringUtils.isNotEmpty(personNameFromNet)) {
                tvUserName.setText(personNameFromNet);
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
    }

    @OnClick({R.id.iv_userinfo_bg, R.id.iv_photo, R.id.tv_user_name, R.id.tv_identity, R.id.rl_user_info, R.id.rl_feed_back,
            R.id.rl_chuanduiguanli, R.id.rl_follew_fleet, R.id.rl_chuanyuanguanli, R.id.rl_account_setting, R.id.rl_chuanguan, R.id.rl_my_certificate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_userinfo_bg:

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
            case R.id.rl_my_certificate:
                startActivity(new Intent(getActivity(), MyCertificateActivity.class));
                break;
        }
    }

    private void gotoCertificateActivity() {
        Intent intent = new Intent(getActivity(), CrewCertificateListsActivity.class);
        intent.putExtra(Constants.FROM_MY,true);
        startActivity(intent);
    }

    private void gotoPersonalInfoActivity() {
        Intent intent = new Intent(getActivity(), PersonalInfoActivity.class);
        intent.putExtra(Constants.USER_INFO, loginResult);
        startActivity(intent);
    }
}
