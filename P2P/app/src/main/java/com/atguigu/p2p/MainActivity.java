package com.atguigu.p2p;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.p2p.common.BaseActivity;
import com.atguigu.p2p.fragment.HomeFragment;
import com.atguigu.p2p.fragment.InvestFragment;
import com.atguigu.p2p.fragment.MeFragment;
import com.atguigu.p2p.fragment.MoreFragment;
import com.atguigu.p2p.util.UIUtils;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.iv_main_home)
    ImageView ivMainHome;
    @Bind(R.id.tv_main_home)
    TextView tvMainHome;
    @Bind(R.id.ll_main_home)
    LinearLayout llMainHome;
    @Bind(R.id.iv_main_invest)
    ImageView ivMainInvest;
    @Bind(R.id.tv_main_invest)
    TextView tvMainInvest;
    @Bind(R.id.ll_main_invest)
    LinearLayout llMainInvest;
    @Bind(R.id.iv_main_me)
    ImageView ivMainMe;
    @Bind(R.id.tv_main_me)
    TextView tvMainMe;
    @Bind(R.id.ll_main_me)
    LinearLayout llMainMe;
    @Bind(R.id.iv_main_more)
    ImageView ivMainMore;
    @Bind(R.id.tv_main_more)
    TextView tvMainMore;
    @Bind(R.id.ll_main_more)
    LinearLayout llMainMore;


    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;
    private FragmentTransaction transaction;


    @Override
    protected void initTitle() {

    }

    public void initData() {
        getSingInfo();

        selectTab(0);

        //可能会发生异常的代码
        String str = null;

        //try {
        //char c = str.charAt(0);
        //} catch (Exception e) {
            //e.printStackTrace();
        //}

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.ll_main_home, R.id.ll_main_invest, R.id.ll_main_me, R.id.ll_main_more})
    public void changeTab(View view) {
//        Toast.makeText(MainActivity.this, "hello!", Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.ll_main_home:
                selectTab(0);
                break;
            case R.id.ll_main_invest:
                selectTab(1);
                break;
            case R.id.ll_main_me:
                selectTab(2);
                break;
            case R.id.ll_main_more:
                selectTab(3);
                break;
        }
    }

    private void selectTab(int i) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        hideFragment();

        reSetTab();

        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_main, homeFragment);
                }

                //修改ImageView和TextView的显示
                ivMainHome.setImageResource(R.drawable.bid01);
                tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                transaction.show(homeFragment);

                //不可以在此处调用LoadingPage,因为其还没有初始化
//                homeFragment.showLoadingPage();

                break;
            case 1:
                if (investFragment == null) {
                    investFragment = new InvestFragment();
                    transaction.add(R.id.fl_main, investFragment);
                }
                //修改ImageView和TextView的显示
                ivMainInvest.setImageResource(R.drawable.bid03);
                tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                transaction.show(investFragment);
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.fl_main, meFragment);
                }
                //修改ImageView和TextView的显示
                ivMainMe.setImageResource(R.drawable.bid05);
                tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                transaction.show(meFragment);
                break;
            case 3:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.fl_main, moreFragment);
                }
                //修改ImageView和TextView的显示
                ivMainMore.setImageResource(R.drawable.bid07);
                tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_selected));
                transaction.show(moreFragment);
                break;
        }
        //提交 (只有当提交数据以后，才会调用Fragment的生命周期方法）
        transaction.commit();


    }

    /**
     *
     * 重置选中中的所有的ImageView和TextView
     */
    private void reSetTab() {
        ivMainHome.setImageResource(R.drawable.bid02);
        ivMainInvest.setImageResource(R.drawable.bid04);
        ivMainMe.setImageResource(R.drawable.bid06);
        ivMainMore.setImageResource(R.drawable.bid08);

        tvMainHome.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainInvest.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMe.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
        tvMainMore.setTextColor(UIUtils.getColor(R.color.home_back_unselected));
    }

    /**
     * 隐藏已经显示的Fragment
     */
    private void hideFragment() {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (investFragment != null) {
            transaction.hide(investFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
    }

    public void getSingInfo() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    "com.atguigu.p2p", PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];
            parseSignature(sign.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void parseSignature(byte[] signature) {
        try {
            CertificateFactory certFactory = CertificateFactory
                    .getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory
                    .generateCertificate(new ByteArrayInputStream(signature));
            String pubKey = cert.getPublicKey().toString();
            String signNumber = cert.getSerialNumber().toString();
            Log.e("TAG", "pubKey:" + pubKey);
            Log.e("TAG", "signNumber:" + signNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
