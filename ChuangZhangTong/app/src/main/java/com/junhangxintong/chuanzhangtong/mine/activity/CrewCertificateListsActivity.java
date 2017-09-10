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
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.adapter.CrewCertificateAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewCeretificateBean;
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
    String[] arrCrewCertificates = {"适任证书", "海员证", "健康证", "高级消防培训合格证书", "船员服务证"};
    private String id;
    private List<CrewCeretificateBean.DataBean.ArrayBean> crewCertificatesLists;
    private CrewCertificateAdapter crewCertificateAdapter;

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
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);

        netGetCrewCertificateLists(id);

        certificates = new ArrayList<>();

        for (int i = 0; i < arrCrewCertificates.length; i++) {
            certificates.add(arrCrewCertificates[i]);
        }

        updataGridview();

        gvCertificate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CrewCertificateListsActivity.this, CertificateIndetailsActivity.class);
                int id = crewCertificatesLists.get(i).getId();
                intent.putExtra(Constants.ID, String.valueOf(id));
                intent.putExtra(Constants.CERTIFICATE_TYPE,1);
                startActivity(intent);
            }
        });
    }

    private void netGetCrewCertificateLists(String id) {
        NetUtils.postWithHeader(this, ConstantsUrls.CREW_CERTIFICATE_LISTS)
                .addParams(Constants.PAGE,"1")
                .addParams(Constants.PAGE_SIZE,"100")
                .addParams(Constants.ID,id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(CrewCertificateListsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(CrewCertificateListsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                CrewCeretificateBean crewCeretificateBean = new Gson().fromJson(response, CrewCeretificateBean.class);

                                crewCertificatesLists = crewCeretificateBean.getData().getArray();

                                updataGridview();
                                crewCertificateAdapter = new CrewCertificateAdapter(CrewCertificateListsActivity.this, crewCertificatesLists);
                                gvCertificate.setAdapter(crewCertificateAdapter);
                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(CrewCertificateListsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(CrewCertificateListsActivity.this, LoginRegisterActivity.class));
                            } else if (code.equals("404")) {
//                                crewLists = new ArrayList<CrewServeBean.DataBean.ArrayBean>();
//                                updateCrewsList();
                            } else {
                                Toast.makeText(CrewCertificateListsActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        updataGridview();
        netGetCrewCertificateLists(id);
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
        Intent intent = new Intent(CrewCertificateListsActivity.this, AddCertificateActivity.class);
        intent.putExtra(Constants.CREW_CERTIFICATE, 1);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
    }

 /*   @Override
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
                    crewCertificateAdapter.notifyDataSetChanged();
                    break;
            }
        }

    }*/
}
