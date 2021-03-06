package com.junhangxintong.chuanzhangtong.dynamic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseFragment;
import com.junhangxintong.chuanzhangtong.dynamic.adapter.MessageFragmentAdapter;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.RoleEnum;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by anwanfei on 2017/7/5.
 */

public class DynamicRemindFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tablayout_message)
    TabLayout tablayoutMessage;
    @BindView(R.id.viewpager_message)
    ViewPager viewpagerMessage;
    private List<Fragment> fragments;
    private AllMessageFragment allMessageFragment;
    private CrewCertificateFragment crewCertificateFragment;
    private ShipCertificateFragment shipCertificateFragment;
    private MessageRecoedFragment messageRecoedFragment;
    private ShipDynamicFragment shipDynamicFragment;
    private List<String> mtitles;

    @Override
    protected View initView() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_message, null);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        // 根据是否登录判断是否要进入登录界面
        String token = CacheUtils.getString(getActivity(), Constants.TOKEN);

        if (StringUtils.isBlank(token)) {
            startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
            getActivity().finish();
        }
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();

        mtitles = new ArrayList<>();

        allMessageFragment = new AllMessageFragment();
        crewCertificateFragment = new CrewCertificateFragment();
        shipDynamicFragment = new ShipDynamicFragment();
        shipCertificateFragment = new ShipCertificateFragment();
        messageRecoedFragment = new MessageRecoedFragment();

        fragments = new ArrayList<>();
        fragments.add(allMessageFragment);
        fragments.add(shipDynamicFragment);
        fragments.add(crewCertificateFragment);

        mtitles.add("所有消息");
        mtitles.add("船舶动态");
        mtitles.add("船员证书");

        String roleID = CacheUtils.getString(getActivity(), Constants.ROLEID);
        if (!roleID.equals(String.valueOf(RoleEnum.SHIPMEMBER.getCode()))) {
            fragments.clear();

            fragments.add(allMessageFragment);
            fragments.add(shipDynamicFragment);
            fragments.add(messageRecoedFragment);
            fragments.add(shipCertificateFragment);
            fragments.add(crewCertificateFragment);

            mtitles.clear();

            mtitles.add("所有消息");
            mtitles.add("船舶动态");
            mtitles.add("报文记录");
            mtitles.add("船舶证书");
            mtitles.add("船员证书");
        }

        tvTitle.setText(getResources().getString(R.string.message));
        viewpagerMessage.setAdapter(new MessageFragmentAdapter(getFragmentManager(), fragments, mtitles));
        tablayoutMessage.setupWithViewPager(viewpagerMessage);
        viewpagerMessage.setOffscreenPageLimit(1);

        tablayoutMessage.getTabAt(0).setIcon(R.drawable.iv_all_messags);
        tablayoutMessage.getTabAt(1).setIcon(R.drawable.iv_ship_dynamic);
        tablayoutMessage.getTabAt(2).setIcon(R.drawable.ic_message_record);

        if (!CacheUtils.getString(getActivity(), Constants.ROLEID).equals(String.valueOf(RoleEnum.SHIPMEMBER.getCode()))) {
            tablayoutMessage.getTabAt(3).setIcon(R.drawable.iv_ship_certificate);
            tablayoutMessage.getTabAt(4).setIcon(R.drawable.iv_crew_certificate);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
