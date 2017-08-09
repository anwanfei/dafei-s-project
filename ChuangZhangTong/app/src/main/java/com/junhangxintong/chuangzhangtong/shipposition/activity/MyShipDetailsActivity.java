package com.junhangxintong.chuangzhangtong.shipposition.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.common.BaseFragment;
import com.junhangxintong.chuangzhangtong.shipposition.fragment.ShipCommunicateFragment;
import com.junhangxintong.chuangzhangtong.shipposition.fragment.ShipCrewInfoFragment;
import com.junhangxintong.chuangzhangtong.shipposition.fragment.ShipDetailsFragment;
import com.junhangxintong.chuangzhangtong.shipposition.fragment.ShipRecordMessageFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MyShipDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rb_ship_details)
    RadioButton rbShipDetails;
    @BindView(R.id.rb_message)
    RadioButton rbMessage;
    @BindView(R.id.rb_crew_info)
    RadioButton rbCrewInfo;
    @BindView(R.id.rb_communicate)
    RadioButton rbCommunicate;
    @BindView(R.id.rg_bottom_tag_ship_details)
    RadioGroup rgBottomTagShipDetails;
    @BindView(R.id.ll_titlebar)
    public LinearLayout llTitlebar;
    private int position;
    private ArrayList<BaseFragment> fragments;
    private Fragment fromFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        rgBottomTagShipDetails.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认主页面
        rgBottomTagShipDetails.check(R.id.rb_ship_details);
//        rgBottomTag.check(R.id.rb_other);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.huahai_one));
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();

        fragments.add(new ShipDetailsFragment());
        fragments.add(new ShipRecordMessageFragment());
        fragments.add(new ShipCrewInfoFragment(llTitlebar));
        fragments.add(new ShipCommunicateFragment());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_ship_details;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_ship_details:
                    position = 0;
                    break;
                case R.id.rb_message:
                    position = 1;
                    break;
                case R.id.rb_crew_info:
                    position = 2;
                    break;
                case R.id.rb_communicate:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }

            BaseFragment toFragment = getFragment();
            switchFragment(fromFragment, toFragment);
        }
    }

    public void switchFragment(Fragment from, Fragment to) {

        if (from != to) {
            fromFragment = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                // 先判断是否被add过
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null) {
                    transaction.add(R.id.fl_content, to).commit();
                }

            } else {
                if (from != null) {
                    transaction.hide(from);
                }
                if (to != null) {
                    transaction.show(to).commit();
                }
            }
        }
    }

    private BaseFragment getFragment() {
        if (fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }
}
