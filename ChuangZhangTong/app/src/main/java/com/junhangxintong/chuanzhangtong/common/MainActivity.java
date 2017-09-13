package com.junhangxintong.chuanzhangtong.common;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.dynamic.fragment.DynamicRemindFragment;
import com.junhangxintong.chuanzhangtong.mine.fragment.MyFragment;
import com.junhangxintong.chuanzhangtong.news.fragment.NewsFragment;
import com.junhangxintong.chuanzhangtong.shipposition.fragment.ShipPositionFragment;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rb_common_frame)
    public RadioButton rbCommonFrame;
    @BindView(R.id.rb_thirdparty)
    RadioButton rbThirdparty;
    @BindView(R.id.rb_custom)
    RadioButton rbCustom;
    @BindView(R.id.rb_other)
    RadioButton rbOther;
    @BindView(R.id.rg_bottom_tag)
    public RadioGroup rgBottomTag;
    private ArrayList<BaseFragment> fragments;
    private int position;
    private Fragment fromFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    @Override
    protected void initView() {
    }

    private void initListener() {
        rgBottomTag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认主页面
        rgBottomTag.check(R.id.rb_common_frame);
//        rgBottomTag.check(R.id.rb_other);
    }


    @Override
    protected void initData() {

        fragments = new ArrayList<>();

        fragments.add(new ShipPositionFragment());
        fragments.add(new DynamicRemindFragment());
        fragments.add(new NewsFragment());
        fragments.add(new MyFragment());

    }

    @Override
    protected void onResume() {
        super.onResume();
        String roleId = CacheUtils.getString(this, Constants.ROLEID);
        if (roleId.equals("4")) {
            rbThirdparty.setVisibility(View.GONE);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.rb_common_frame:
                    position = 0;
                    break;
                case R.id.rb_thirdparty:
                    position = 1;
                    break;
                case R.id.rb_custom:
                    position = 2;
                    break;
                case R.id.rb_other:
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

    private BaseFragment getFragment() {
        if (fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
