package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.adapter.CertificateAdapter;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CrewCertificateListsActivity extends BaseActivity {

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
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.ll_no_fleet)
    LinearLayout llNoFleet;

    private List<String> certificates;
    private CertificateAdapter certificateAdapter;
    String[] arrCrewCertificates = {"适任证书", "海员证", "健康证", "高级消防培训合格证书", "船员服务证"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.crew_certificate));
        ivShare.setBackgroundResource(R.drawable.add_certificate);
        tvSetting.setText(getResources().getString(R.string.add));
        ivNothing.setImageResource(R.drawable.iv_no_certificate);
        tvNothing.setText(getResources().getString(R.string.add_first_certificate));
    }

    @Override
    protected void initData() {

        certificates = new ArrayList<>();

        for (int i = 0; i < arrCrewCertificates.length; i++) {
            certificates.add(arrCrewCertificates[i]);
        }

        updataGridview();

        gvCertificate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(CrewCertificateListsActivity.this, CertificateIndetailsActivity.class));
            }
        });
    }

    private void updataGridview() {
        if (certificates.size() > 0) {
            gvCertificate.setVisibility(View.VISIBLE);
            llNoFleet.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            gvCertificate.setVisibility(View.GONE);
            llNoFleet.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
        }

        certificateAdapter = new CertificateAdapter(this, certificates);
        gvCertificate.setAdapter(certificateAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updataGridview();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certificate_lists;
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_setting, R.id.tv_add_ship})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                gotoAddCertificateActivity();
                break;
            case R.id.tv_setting:
                gotoAddCertificateActivity();
                break;
            case R.id.tv_add_ship:
                gotoAddCertificateActivity();
                break;
        }
    }

    private void gotoAddCertificateActivity() {
        startActivityForResult(new Intent(CrewCertificateListsActivity.this, AddCertificateActivity.class), Constants.REQUEST_CODE0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case Constants.REQUEST_CODE0:
                    String certificate_name = data.getStringExtra(Constants.CERTIFICATE_NAME);
                    String certificatenum = data.getStringExtra(Constants.CERTIFICATENUM);
                    String certificatetype = data.getStringExtra(Constants.CERTIFICATETYPE);
                    String issuingauthory = data.getStringExtra(Constants.ISSUINGAUTHORY);

                    certificates.add(certificate_name);
                    certificateAdapter.notifyDataSetChanged();
                    break;
            }
        }

    }
}
