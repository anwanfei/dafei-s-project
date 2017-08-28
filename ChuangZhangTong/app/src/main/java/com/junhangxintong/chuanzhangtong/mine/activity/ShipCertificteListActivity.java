package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.adapter.ShipCertificateAdapter;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class ShipCertificteListActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.ll_no_fleet)
    LinearLayout llNoFleet;
    @BindView(R.id.rv_certificate)
    RecyclerView rvCertificate;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    private PopupWindow popupWindow;
    private boolean isShowPop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_certificate));
        ivShare.setBackgroundResource(R.drawable.add_certificate);
        tvSetting.setText(getResources().getString(R.string.add));
        ivNothing.setImageResource(R.drawable.iv_no_certificate);
        tvNothing.setText(getResources().getString(R.string.add_first_certificate));
    }

    @Override
    protected void initData() {
        ShipCertificateAdapter shipCertificateAdapter = new ShipCertificateAdapter(ShipCertificteListActivity.this);
        rvCertificate.setAdapter(shipCertificateAdapter);

        rvCertificate.setLayoutManager(new LinearLayoutManager(ShipCertificteListActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ship_certificte_list;
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_setting, R.id.tv_add_ship, R.id.ll_no_fleet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.tv_setting:
                if (isShowPop) {
                    hidePop();
                    isShowPop = false;
                } else {
                    showPopOfAddType();
                    isShowPop = true;
                }
                break;
            case R.id.tv_add_ship:
                break;
            case R.id.ll_no_fleet:
                break;
        }
    }

    private void showPopOfAddType() {
        View view = LinearLayout.inflate(this, R.layout.pop_ship_certificate_add_type, null);

        TextView tv_add_certificate = (TextView) view.findViewById(R.id.tv_add_certificate);
        TextView tv_add_insurance = (TextView) view.findViewById(R.id.tv_add_insurance);

        popupWindow = new PopupWindow(view, DensityUtil.dp2px(this, 100), LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setContentView(view);

        //点击pop以外的部分消失
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAsDropDown(ivShare, 0, 0);

        tv_add_certificate.setOnClickListener(this);
        tv_add_insurance.setOnClickListener(this);
    }

    private void hidePop() {
        popupWindow.dismiss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_insurance:
                startActivity(new Intent(ShipCertificteListActivity.this, AddInsuranceActivity.class));
                hidePop();
                break;
            case R.id.tv_add_certificate:
                hidePop();
                startActivity(new Intent(ShipCertificteListActivity.this, AddCertificateActivity.class));
                break;
        }
    }
}
