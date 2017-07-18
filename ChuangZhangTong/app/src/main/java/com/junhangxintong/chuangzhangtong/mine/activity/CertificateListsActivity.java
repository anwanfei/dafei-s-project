package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.mine.adapter.CertificateAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertificateListsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.gv_certificate)
    GridView gvCertificate;

    private List<String> certificates;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_certificate_lists));
        ivShare.setBackgroundResource(R.drawable.add_certificate);
        tvSetting.setText(getResources().getString(R.string.add_certificate));
    }

    @Override
    protected void initData() {

        certificates = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            certificates.add("航海家");
            certificates.add("大厨");
            certificates.add("二管");
        }

        CertificateAdapter certificateAdapter = new CertificateAdapter(this, certificates);
        gvCertificate.setAdapter(certificateAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certificate_lists;
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                startActivity(new Intent(CertificateListsActivity.this, AddCertificateActivity.class));
                break;
            case R.id.tv_setting:
                startActivity(new Intent(CertificateListsActivity.this, AddCertificateActivity.class));
                break;
        }
    }
}
