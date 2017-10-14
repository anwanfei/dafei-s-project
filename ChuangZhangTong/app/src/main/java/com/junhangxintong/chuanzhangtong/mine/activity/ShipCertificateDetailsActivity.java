package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
import com.junhangxintong.chuanzhangtong.view.MyGridview;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.junhangxintong.chuanzhangtong.R.id.et_certificate_type;

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
                .execute(new NetUtils.MyStringCallback() {
                    @Override
                    protected void onSuccess(String response, String message) {
                        shipCertificateOrInsuranceInfoBean = new Gson().fromJson(response, ShipCertificateOrInsuranceInfoBean.class);
                        ShipCertificateOrInsuranceInfoBean.DataBean.ObjectBean shipCertificateOrInsuranceInfo = shipCertificateOrInsuranceInfoBean.getData().getObject();

                        tvInputCertificateName.setText(shipCertificateOrInsuranceInfo.getName());
                        tvCertificateNumber.setText(String.valueOf(shipCertificateOrInsuranceInfo.getCertifNo()));
                        etCertificateType.setText(shipCertificateOrInsuranceInfo.getCertifCategory());
                        tvIssuingAuthority.setText(shipCertificateOrInsuranceInfo.getIssueOrganization());

                        String validDate = shipCertificateOrInsuranceInfo.getValidDate();
                        int isValid = shipCertificateOrInsuranceInfo.getIsValid();
                        if (isValid == 1) {
                            tvEndDate.setText(getResources().getString(R.string.permanent_effective));
                            rlWarningDays.setVisibility(View.GONE);
                        } else {
                            tvEndDate.setText(validDate);
                            tvWarningDays.setText(String.valueOf(shipCertificateOrInsuranceInfo.getAdvanceWarnDay()));
                            rlWarningDays.setVisibility(View.VISIBLE);
                        }
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
