package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.mine.adapter.ShowPhotoAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipCertificateOrInsuranceInfoBean;
import com.junhangxintong.chuanzhangtong.mine.bean.UrlBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.junhangxintong.chuanzhangtong.view.MyGridview;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.R.id.et_certificate_type;
import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class ShipCertificateDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_input_certificate_name)
    TextView tvInputCertificateName;
    @BindView(R.id.tv_certificate_number)
    TextView tvCertificateNumber;
    @BindView(et_certificate_type)
    TextView etCertificateType;
    @BindView(R.id.rl_certificate_type)
    RelativeLayout rlCertificateType;
    @BindView(R.id.tv_issuing_authority)
    TextView tvIssuingAuthority;
    @BindView(R.id.rl_issuing_authority)
    RelativeLayout rlIssuingAuthority;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_warning_days)
    TextView tvWarningDays;
    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.iv_certificate_phone)
    ImageView ivCertificatePhone;
    @BindView(R.id.rl_warning_days)
    RelativeLayout rlWarningDays;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.gv_certificate_photo)
    MyGridview gvCertificatePhoto;
    private ShipCertificateOrInsuranceInfoBean shipCertificateOrInsuranceInfoBean;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.certificate_details));
        tvSetting.setVisibility(View.VISIBLE);
        tvSetting.setText(getResources().getString(R.string.edit));
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);

        netGetShipCertificateDetails();
    }

    private void netGetShipCertificateDetails() {
        NetUtils.postWithHeader(this, ConstantsUrls.SHIP_CERTIFICATE_INFO)
                .addParams(Constants.ID, id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ShipCertificateDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ShipCertificateDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrort = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                shipCertificateOrInsuranceInfoBean = new Gson().fromJson(response, ShipCertificateOrInsuranceInfoBean.class);
                                ShipCertificateOrInsuranceInfoBean.DataBean.ObjectBean shipCertificateOrInsuranceInfo = shipCertificateOrInsuranceInfoBean.getData().getObject();

                                tvInputCertificateName.setText(shipCertificateOrInsuranceInfo.getName());
                                tvCertificateNumber.setText(String.valueOf(shipCertificateOrInsuranceInfo.getCertifNo()));
                                etCertificateType.setText(getResources().getString(R.string.certificate));
                                tvIssuingAuthority.setText(shipCertificateOrInsuranceInfo.getIssueOrganization());
                                tvEndDate.setText(shipCertificateOrInsuranceInfo.getValidDate());
                                tvWarningDays.setText(String.valueOf(shipCertificateOrInsuranceInfo.getAdvanceWarnDay()));
                                if (shipCertificateOrInsuranceInfo.getIsUse() == 1) {
                                    tvCommon.setText(getResources().getString(R.string.yes));
                                } else {
                                    tvCommon.setText(getResources().getString(R.string.no));
                                }

                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        scrollView.scrollTo(0, 0);
                                    }
                                });

                                String imgUrl = shipCertificateOrInsuranceInfo.getImgUrl();
                                if (StringUtils.isNotBlank(imgUrl)) {
                                    Type type = new TypeToken<ArrayList<UrlBean>>() {
                                    }.getType();
                                    ArrayList<UrlBean> urlLists = new Gson().fromJson(imgUrl, type);

                                    ShowPhotoAdapter showPhotoAdapter = new ShowPhotoAdapter(ShipCertificateDetailsActivity.this, urlLists, shipCertificateOrInsuranceInfo.getDomain());
                                    gvCertificatePhoto.setAdapter(showPhotoAdapter);
                                }

                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ShipCertificateDetailsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ShipCertificateDetailsActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else {
                                Toast.makeText(ShipCertificateDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certificate_indetails;
    }

    @OnClick({R.id.iv_back, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_setting:
                Intent intent = new Intent(ShipCertificateDetailsActivity.this, ModifyShipCertificateActivity.class);
                intent.putExtra(Constants.SHIP_CERTIFICATE_DETAILS_BEAN, shipCertificateOrInsuranceInfoBean);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        netGetShipCertificateDetails();

    }
}
