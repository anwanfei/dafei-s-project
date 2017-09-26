package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.adapter.CrewCertificateAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewCeretificateRemindBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class MyCertificateActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.iv_nothing)
    ImageView ivNothing;
    @BindView(R.id.tv_nothing)
    TextView tvNothing;
    @BindView(R.id.tv_add_ship)
    TextView tvAddShip;
    @BindView(R.id.ll_no_fleet)
    LinearLayout llNoFleet;
    @BindView(R.id.gv_certificate)
    GridView gvCertificate;
    private List<CrewCeretificateRemindBean.DataBean.ArrayBean> myCertificatesLists;
    private CrewCertificateAdapter crewCertificateAdapter;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvSetting.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.mycertificate));
        tvSetting.setText(getResources().getString(R.string.add));
        ivNothing.setImageResource(R.drawable.iv_no_certificate);
        tvNothing.setText(getResources().getString(R.string.add_first_certificate));
    }

    @Override
    protected void initData() {

        userId = CacheUtils.getString(this, Constants.ID);
        netGetcertificate(userId);
    }

    private void netGetcertificate(String userId) {
        NetUtils.postWithHeader(this, ConstantsUrls.CREW_CERTIFICATE_LISTS)
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.ID, userId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MyCertificateActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(MyCertificateActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                CrewCeretificateRemindBean crewCeretificateBean = new Gson().fromJson(response, CrewCeretificateRemindBean.class);

                                myCertificatesLists = crewCeretificateBean.getData().getArray();

                                updataGridview();
                                crewCertificateAdapter = new CrewCertificateAdapter(MyCertificateActivity.this, myCertificatesLists);
                                gvCertificate.setAdapter(crewCertificateAdapter);

                                gvCertificate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(MyCertificateActivity.this, CrewCertificateDetailsActivity.class);
                                        int id = myCertificatesLists.get(i).getId();
                                        intent.putExtra(Constants.ID, String.valueOf(id));
                                        intent.putExtra(Constants.CERTIFICATE_TYPE, 1);
                                        startActivity(intent);
                                    }
                                });

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(MyCertificateActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(MyCertificateActivity.this, LoginRegisterActivity.class));
                            } else if (code.equals("404")) {
                                myCertificatesLists = new ArrayList<CrewCeretificateRemindBean.DataBean.ArrayBean>();
                                updataGridview();
                            } else {
                                Toast.makeText(MyCertificateActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void updataGridview() {
        if (myCertificatesLists.size() > 0) {
            gvCertificate.setVisibility(View.VISIBLE);
            llNoFleet.setVisibility(View.GONE);
        } else {
            gvCertificate.setVisibility(View.GONE);
            llNoFleet.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_certificate;
    }

    @OnClick({R.id.iv_back, R.id.tv_add_ship, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_ship:
                gotoAddCertificateActivity();
                break;
            case R.id.tv_setting:
                gotoAddCertificateActivity();
                break;
        }
    }

    private void gotoAddCertificateActivity() {
        Intent intent = new Intent(MyCertificateActivity.this, AddCrewCertificateActivity.class);
        intent.putExtra(Constants.CREW_CERTIFICATE, 1);
        intent.putExtra(Constants.ID, userId);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        netGetcertificate(userId);
    }
}
