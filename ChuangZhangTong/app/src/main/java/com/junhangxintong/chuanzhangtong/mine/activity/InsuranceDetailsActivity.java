package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.adapter.ShowPhotoAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipCertificateOrInsuranceInfoBean;
import com.junhangxintong.chuanzhangtong.mine.bean.UrlBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class InsuranceDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_insurance_type)
    TextView tvInsuranceType;
    @BindView(R.id.tv_ship_name)
    TextView tvShipName;
    @BindView(R.id.tv_ship_bianhao)
    TextView tvShipBianhao;
    @BindView(R.id.tv_ship_imo)
    TextView tvShipImo;
    @BindView(R.id.rl_certificate_type)
    RelativeLayout rlCertificateType;
    @BindView(R.id.tv_ship_nationality_harbor)
    TextView tvShipNationalityHarbor;
    @BindView(R.id.tv_name_address_of_ship)
    TextView tvNameAddressOfShip;
    @BindView(R.id.tv_gurantee_type)
    TextView tvGuranteeType;
    @BindView(R.id.tv_issue_date)
    TextView tvIssueDate;
    @BindView(R.id.tv_issuing_authority)
    TextView tvIssuingAuthority;
    @BindView(R.id.tv_issue_address)
    TextView tvIssueAddress;
    @BindView(R.id.rl_issuing_authority)
    RelativeLayout rlIssuingAuthority;
    @BindView(R.id.tv_is_permanent_effective)
    TextView tvIsPermanentEffective;
    @BindView(R.id.tv_effective_date)
    TextView tvEffectiveDate;
    @BindView(R.id.rl_effective_date)
    RelativeLayout rlEffectiveDate;
    @BindView(R.id.tv_warning_days)
    TextView tvWarningDays;
    @BindView(R.id.rl_re_warning_days)
    RelativeLayout rlReWarningDays;
    @BindView(R.id.tv_common)
    TextView tvCommon;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.iv_certificate_phone)
    ImageView ivCertificatePhone;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.gv_certificate_photo)
    GridView gvCertificatePhoto;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private ShipCertificateOrInsuranceInfoBean shipCertificateOrInsuranceInfoBean;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_insturance));
        tvSetting.setVisibility(View.VISIBLE);
        tvSetting.setText(getResources().getString(R.string.edit));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Constants.ID);
        netGetShipInsuranceInfo();
    }

    private void netGetShipInsuranceInfo() {
        NetUtils.postWithHeader(this, ConstantsUrls.SHIP_CERTIFICATE_INFO)
                .addParams(Constants.ID, id)
                .build()
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        shipCertificateOrInsuranceInfoBean = new Gson().fromJson(response, ShipCertificateOrInsuranceInfoBean.class);
                        ShipCertificateOrInsuranceInfoBean.DataBean.ObjectBean shipCertificateOrInsuranceInfo = shipCertificateOrInsuranceInfoBean.getData().getObject();

                        tvInsuranceType.setText(shipCertificateOrInsuranceInfo.getName());
                        tvShipName.setText(shipCertificateOrInsuranceInfo.getShipName());
                        tvShipBianhao.setText(String.valueOf(shipCertificateOrInsuranceInfo.getShipCall()));
                        tvShipImo.setText(shipCertificateOrInsuranceInfo.getImoNo());
                        tvShipNationalityHarbor.setText(shipCertificateOrInsuranceInfo.getShipNationaPort());
                        tvNameAddressOfShip.setText(shipCertificateOrInsuranceInfo.getShipNameOrAddress());
                        tvGuranteeType.setText(shipCertificateOrInsuranceInfo.getAssureType());
                        tvIssueDate.setText(shipCertificateOrInsuranceInfo.getIssueDate());
                        tvIssuingAuthority.setText(shipCertificateOrInsuranceInfo.getIssueOrganization());
                        tvIssueAddress.setText(shipCertificateOrInsuranceInfo.getIssueAddress());
                        int isValid = shipCertificateOrInsuranceInfo.getIsValid();
                        if (isValid == 1) {
                            tvEffectiveDate.setText(getResources().getString(R.string.permanent_effective));
                            rlReWarningDays.setVisibility(View.GONE);
                            tvIsPermanentEffective.setText(getResources().getString(R.string.yes));
                        } else {
                            tvEffectiveDate.setText(shipCertificateOrInsuranceInfo.getValidDate());
                            tvWarningDays.setText(String.valueOf(shipCertificateOrInsuranceInfo.getAdvanceWarnDay()));
                            rlReWarningDays.setVisibility(View.VISIBLE);
                            tvIsPermanentEffective.setText(getResources().getString(R.string.no));
                        }

                        if (shipCertificateOrInsuranceInfo.getIsUse() == 1) {
                            tvCommon.setText(getResources().getString(R.string.yes));
                        } else {
                            tvCommon.setText(getResources().getString(R.string.no));
                        }

                        String imgUrl = shipCertificateOrInsuranceInfo.getImgUrl();
                        if (StringUtils.isNotBlank(imgUrl)) {
                            Type type = new TypeToken<ArrayList<UrlBean>>() {
                            }.getType();
                            ArrayList<UrlBean> urlLists = new Gson().fromJson(imgUrl, type);

                            ShowPhotoAdapter showPhotoAdapter = new ShowPhotoAdapter(InsuranceDetailsActivity.this, urlLists, shipCertificateOrInsuranceInfo.getDomain());
                            gvCertificatePhoto.setAdapter(showPhotoAdapter);

                            //scrollview数据处理
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    scrollView.scrollTo(0, 0);
                                }
                            });
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        netGetShipInsuranceInfo();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_instrance_details;
    }

    @OnClick({R.id.iv_back, R.id.iv_share, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                break;
            case R.id.tv_setting:
                Intent intent = new Intent(InsuranceDetailsActivity.this, ModifyShipInsuranceActivity.class);
                intent.putExtra(Constants.SHIP_INSURANCE_DETAILS_BEAN, shipCertificateOrInsuranceInfoBean);
                startActivity(intent);
                break;
        }
    }
}
