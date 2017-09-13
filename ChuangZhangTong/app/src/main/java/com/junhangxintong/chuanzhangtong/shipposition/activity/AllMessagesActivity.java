package com.junhangxintong.chuanzhangtong.shipposition.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.shipposition.fragment.ArrivaMessageFragment;
import com.junhangxintong.chuanzhangtong.shipposition.fragment.BerthingMessageFragment;
import com.junhangxintong.chuanzhangtong.shipposition.fragment.LeaveMessageFragment;
import com.junhangxintong.chuanzhangtong.shipposition.fragment.NoonzMessageFragment;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AllMessagesActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tablayout_all_messages)
    public TabLayout tablayoutAllMessages;
    @BindView(R.id.viewpager_all_meesage)
    ViewPager viewpagerAllMeesage;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    private String[] mtitles = {"正午报", "抵港报", "靠泊报", "离港报"};
    List<Fragment> fragments;
    private int tag = 0;
    private boolean isShowPop;
    private PopupWindow popupWindow;
    Class[] arrClass = {WriteNoonMessageActivity.class, WriteArrivalMessageActivity.class, WriteBerthingMessageActivity.class, WriteLeaveMessageActivity.class};
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.all_history_messages));
        ivShare.setVisibility(View.VISIBLE);
        tvSetting.setVisibility(View.VISIBLE);
        ivShare.setImageResource(R.drawable.ic_input_message);
        tvSetting.setText(getResources().getString(R.string.input_message));
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);

        fragments = new ArrayList<>();
        fragments.add(new NoonzMessageFragment());
        fragments.add(new ArrivaMessageFragment());
        fragments.add(new BerthingMessageFragment());
        fragments.add(new LeaveMessageFragment());


        viewpagerAllMeesage.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return mtitles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mtitles[position];
            }
        });
        tablayoutAllMessages.setupWithViewPager(viewpagerAllMeesage);
        tablayoutAllMessages.getTabAt(0).setIcon(R.drawable.iv_zhengwu_message).setTag(0);
        tablayoutAllMessages.getTabAt(1).setIcon(R.drawable.iv_arrival_message).setTag(1);
        tablayoutAllMessages.getTabAt(2).setIcon(R.drawable.iv_kaobo_message).setTag(2);
        tablayoutAllMessages.getTabAt(3).setIcon(R.drawable.iv_leave_meaage).setTag(3);

        tablayoutAllMessages.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tag = (int) tab.getTag();
                Log.e("TAG", "tab===============" + tag);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_all_messages;
    }

    @OnClick({R.id.iv_back, R.id.tv_setting, R.id.iv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_setting:
                ShowHidePop();
                break;
            case R.id.iv_share:
                ShowHidePop();
                break;
        }
    }

    private void ShowHidePop() {
        if (isShowPop) {
            hidePop();
            isShowPop = false;
        } else {
            showPopShipMessage();
            isShowPop = true;
        }
    }
    private void showPopShipMessage() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ship_message, null);
        TextView tv_pop_noon_message = (TextView) view.findViewById(R.id.tv_pop_noon_message);
        TextView tv_pop_arrival_message = (TextView) view.findViewById(R.id.tv_pop_arrival_message);
        TextView tv_pop_berthing = (TextView) view.findViewById(R.id.tv_pop_berthing);
        TextView tv_ship_leave_message = (TextView) view.findViewById(R.id.tv_ship_leave_message);

        popupWindow = new PopupWindow(view, DensityUtil.dp2px(this, 120), LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setContentView(view);

        //点击pop以外的部分消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAsDropDown(tvSetting, 0, 10);

        tv_ship_leave_message.setOnClickListener(this);
        tv_pop_arrival_message.setOnClickListener(this);
        tv_pop_berthing.setOnClickListener(this);
        tv_pop_noon_message.setOnClickListener(this);

    }

    private void hidePop() {
        popupWindow.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pop_noon_message:
                gotoWriteMessageActivity(0);
                break;
            case R.id.tv_pop_arrival_message:
                gotoWriteMessageActivity(1);
                break;
            case R.id.tv_pop_berthing:
                gotoWriteMessageActivity(2);
                break;
            case R.id.tv_ship_leave_message:
                gotoWriteMessageActivity(3);
                break;
        }
    }
    public void gotoWriteMessageActivity(int num) {
        Intent intent = new Intent(this, arrClass[num]);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
        hidePop();
    }
}
