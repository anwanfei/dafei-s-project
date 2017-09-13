package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.mine.adapter.ShowPhotoAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.ShipCertificateOrInsuranceInfoBean;
import com.junhangxintong.chuanzhangtong.mine.bean.UrlBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class InstranceDetailsActivity extends BaseActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.ship_insturance));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra(Constants.ID);
        NetUtils.postWithHeader(this, ConstantsUrls.SHIP_CERTIFICATE_INFO)
                .addParams(Constants.ID, id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(InstranceDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(InstranceDetailsActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceErrortBean netServiceErrort = new Gson().fromJson(response, NetServiceErrortBean.class);
                            String message = netServiceErrort.getMessage();
                            String code = netServiceErrort.getCode();
                            if (code.equals("200")) {
                                ShipCertificateOrInsuranceInfoBean shipCertificateOrInsuranceInfoBean = new Gson().fromJson(response, ShipCertificateOrInsuranceInfoBean.class);
                                ShipCertificateOrInsuranceInfoBean.DataBean.ObjectBean shipCertificateOrInsuranceInfo = shipCertificateOrInsuranceInfoBean.getData().getObject();

                                tvInsuranceType.setText(shipCertificateOrInsuranceInfo.getName());
                                tvShipName.setText(shipCertificateOrInsuranceInfo.getShipName());
                                tvShipBianhao.setText(String.valueOf(shipCertificateOrInsuranceInfo.getCertifNo()));
                                tvShipImo.setText(shipCertificateOrInsuranceInfo.getImoNo());
                                tvShipNationalityHarbor.setText(shipCertificateOrInsuranceInfo.getShipNationaPort());
                                tvNameAddressOfShip.setText(shipCertificateOrInsuranceInfo.getShipNameOrAddress());
                                tvGuranteeType.setText(shipCertificateOrInsuranceInfo.getAssureType());
                                tvIssueDate.setText(shipCertificateOrInsuranceInfo.getIssueDate());
                                tvIssuingAuthority.setText(shipCertificateOrInsuranceInfo.getIssueOrganization());
                                tvIssueAddress.setText(shipCertificateOrInsuranceInfo.getIssueAddress());
                                int isValid = shipCertificateOrInsuranceInfo.getIsValid();
                                if (isValid == 1) {
                                    tvIsPermanentEffective.setText(getResources().getString(R.string.yes));
                                } else {
                                    tvIsPermanentEffective.setText(getResources().getString(R.string.no));
                                }

                                tvEffectiveDate.setText(shipCertificateOrInsuranceInfo.getValidDate());
                                tvWarningDays.setText(String.valueOf(shipCertificateOrInsuranceInfo.getAdvanceWarnDay()));
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

                                    ShowPhotoAdapter showPhotoAdapter = new ShowPhotoAdapter(InstranceDetailsActivity.this, urlLists, shipCertificateOrInsuranceInfo.getDomain());
                                    gvCertificatePhoto.setAdapter(showPhotoAdapter);
                                }
                            } else if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(InstranceDetailsActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(InstranceDetailsActivity.this, LoginRegisterActivity.class));
                                finish();
                            } else {
                                Toast.makeText(InstranceDetailsActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


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
                break;
        }
    }
}
