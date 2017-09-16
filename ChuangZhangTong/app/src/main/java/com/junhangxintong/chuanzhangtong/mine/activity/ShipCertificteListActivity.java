package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.adapter.ShipCertificateInsuranceAdapter;
import com.junhangxintong.chuanzhangtong.mine.adapter.ShipCertificatesInsrancesAdapter;
import com.junhangxintong.chuanzhangtong.mine.adapter.ShipInsrancesAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.CustomCertificateBean;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipCertificateInsuranceListsBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.DensityUtil;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;


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
    @BindView(R.id.gv_certificate)
    GridView gvCertificate;
    @BindView(R.id.gv_insurance)
    GridView gvInsurance;
    @BindView(R.id.ll_certificate)
    LinearLayout llCertificate;
    @BindView(R.id.ll_insurance)
    LinearLayout llInsurance;
    private PopupWindow popupWindow;
    private boolean isShowPop;
    private String id;
    private List<ShipCertificateInsuranceListsBean.DataBean.ArrayBean> shipCertificateInsuranceLists;
    private ShipCertificateInsuranceAdapter shipCertificateInsuranceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_certificate));
        tvSetting.setVisibility(View.VISIBLE);
        ivShare.setVisibility(View.VISIBLE);
        ivShare.setBackgroundResource(R.drawable.add_certificate);
        tvSetting.setText(getResources().getString(R.string.add));
        ivNothing.setImageResource(R.drawable.iv_no_certificate);
        tvNothing.setText(getResources().getString(R.string.add_first_certificate));
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
        netGetCertificateOrInsurances();

    }

    private void netGetCertificateOrInsurances() {
        NetUtils.postWithHeader(this, ConstantsUrls.SHIP_CERTIFICATE_LIST)
                .addParams(Constants.PAGE_SIZE, "100")
                .addParams(Constants.PAGE, "1")
                .addParams(Constants.SHIP_ID, id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ShipCertificteListActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ShipCertificteListActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                final List<CustomCertificateBean> shipCertificates = new ArrayList<CustomCertificateBean>();
                                final List<CustomCertificateBean> shipInsurances = new ArrayList<CustomCertificateBean>();
                                ShipCertificateInsuranceListsBean shipCertificateInsuranceListsBean = new Gson().fromJson(response, ShipCertificateInsuranceListsBean.class);
                                shipCertificateInsuranceLists = shipCertificateInsuranceListsBean.getData().getArray();
                                CustomCertificateBean customCertificateBean = null;
                                for (int i = 0; i < shipCertificateInsuranceLists.size(); i++) {
                                    customCertificateBean = new CustomCertificateBean();
                                    int certifType = shipCertificateInsuranceLists.get(i).getCertifType();

                                    String name = shipCertificateInsuranceLists.get(i).getName();
                                    String certifNo = shipCertificateInsuranceLists.get(i).getCertifNo();
                                    String issueOrganization = shipCertificateInsuranceLists.get(i).getIssueOrganization();
                                    String validDate = shipCertificateInsuranceLists.get(i).getValidDate();
                                    int certifId = shipCertificateInsuranceLists.get(i).getId();
                                    String shipName = shipCertificateInsuranceLists.get(i).getShipName();

                                    customCertificateBean.setBinahao(certifNo);
                                    customCertificateBean.setId(String.valueOf(certifId));
                                    customCertificateBean.setIssueArgument(issueOrganization);
                                    customCertificateBean.setName(name);
                                    customCertificateBean.setValidDate(validDate);
                                    customCertificateBean.setShipName(shipName);

                                    if (certifType == 1) {
                                        shipCertificates.add(customCertificateBean);
                                    } else if (certifType == 2) {
                                        shipInsurances.add(customCertificateBean);
                                    }
                                }

                                if (shipCertificates.size() > 0) {
                                    llCertificate.setVisibility(View.VISIBLE);
                                } else {
                                    llCertificate.setVisibility(View.GONE);
                                }

                                if (shipInsurances.size() > 0) {
                                    llInsurance.setVisibility(View.VISIBLE);
                                } else {
                                    llInsurance.setVisibility(View.GONE);
                                }

                                updata();

                                ShipCertificatesInsrancesAdapter shipCertificatesAdapter = new ShipCertificatesInsrancesAdapter(ShipCertificteListActivity.this, shipCertificates);
                                ShipInsrancesAdapter shipInsrancesAdapter = new ShipInsrancesAdapter(ShipCertificteListActivity.this, shipInsurances);
                                gvCertificate.setAdapter(shipCertificatesAdapter);
                                gvInsurance.setAdapter(shipInsrancesAdapter);

                                gvInsurance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(ShipCertificteListActivity.this, InstranceDetailsActivity.class);
                                        intent.putExtra(Constants.ID, shipInsurances.get(i).getId());
                                        startActivity(intent);
                                    }
                                });

                                gvCertificate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        Intent intent = new Intent(ShipCertificteListActivity.this, CertificateIndetailsActivity.class);
                                        intent.putExtra(Constants.ID, shipCertificates.get(i).getId());
                                        startActivity(intent);
                                    }
                                });


                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ShipCertificteListActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ShipCertificteListActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else if (code.equals("404")) {
                                shipCertificateInsuranceLists = new ArrayList<ShipCertificateInsuranceListsBean.DataBean.ArrayBean>();
                                updata();
                            } else {
                                Toast.makeText(ShipCertificteListActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void gotoShipCertificateDetailsActivity(int position) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        netGetCertificateOrInsurances();
//        shipCertificateInsuranceAdapter.notifyDataSetChanged();
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
                if (isShowPop) {
                    hidePop();
                    isShowPop = false;
                } else {
                    showPopOfAddType();
                    isShowPop = true;
                }
                break;
            case R.id.ll_no_fleet:
                break;
        }
    }

    private void updata() {
        if (shipCertificateInsuranceLists.size() > 0) {
            rvCertificate.setVisibility(View.VISIBLE);
            llNoFleet.setVisibility(View.GONE);
            tvShare.setVisibility(View.VISIBLE);
        } else {
            rvCertificate.setVisibility(View.GONE);
            llNoFleet.setVisibility(View.VISIBLE);
            tvShare.setVisibility(View.GONE);
            llInsurance.setVisibility(View.GONE);
            llCertificate.setVisibility(View.GONE);
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

        popupWindow.showAsDropDown(tvSetting, 0, 0);

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
                goAddInsuranceActivity();
                hidePop();
                break;
            case R.id.tv_add_certificate:
                gotoAddCertificateActivity();
                hidePop();
                break;
        }
    }

    private void gotoAddCertificateActivity() {
        Intent intent = new Intent(ShipCertificteListActivity.this, AddShipCertificateActivity.class);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
    }

    private void goAddInsuranceActivity() {
        Intent intent = new Intent(ShipCertificteListActivity.this, AddInsuranceActivity.class);
        intent.putExtra(Constants.ID, id);
        startActivity(intent);
    }
}
