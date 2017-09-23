package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.adapter.CertificatePhotoAdapter;
import com.junhangxintong.chuanzhangtong.mine.bean.CrewCertificateDetailsBean;
import com.junhangxintong.chuanzhangtong.mine.bean.UrlBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.view.MyGridview;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyCrewCertificateActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_certificate_name)
    EditText etInputCertificateName;
    @BindView(R.id.et_certificate_number)
    EditText etCertificateNumber;
    @BindView(R.id.et_certificate_type)
    EditText etCertificateType;
    @BindView(R.id.rl_certificate_type)
    RelativeLayout rlCertificateType;
    @BindView(R.id.tv_issuing_authority)
    EditText tvIssuingAuthority;
    @BindView(R.id.rl_issuing_authority)
    RelativeLayout rlIssuingAuthority;
    @BindView(R.id.rb_no)
    RadioButton rbNo;
    @BindView(R.id.rb_yes)
    RadioButton rbYes;
    @BindView(R.id.rg_is_effective)
    RadioGroup rgIsEffective;
    @BindView(R.id.tv_effective_date)
    TextView tvEffectiveDate;
    @BindView(R.id.rl_effective_date)
    RelativeLayout rlEffectiveDate;
    @BindView(R.id.rb_time_30_days)
    RadioButton rbTime30Days;
    @BindView(R.id.rb_time_90_days)
    RadioButton rbTime90Days;
    @BindView(R.id.rg_warn_days)
    RadioGroup rgWarnDays;
    @BindView(R.id.rl_reWarning_days)
    RelativeLayout rlReWarningDays;
    @BindView(R.id.rb_commoned)
    RadioButton rbCommoned;
    @BindView(R.id.rb_no_commoned)
    RadioButton rbNoCommoned;
    @BindView(R.id.rg_is_ofton_use)
    RadioGroup rgIsOftonUse;
    @BindView(R.id.rvResultPhoto)
    RecyclerView rvResultPhoto;
    @BindView(R.id.tv_add_certificate_photo)
    TextView tvAddCertificatePhoto;
    @BindView(R.id.tv_crew_info_complete)
    TextView tvCrewInfoComplete;
    @BindView(R.id.rl_arningDays)
    RelativeLayout rlWarningDays;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.gv_certificate_photo)
    MyGridview gvCertificatePhoto;
    private String userId;
    private CrewCertificateDetailsBean.DataBean.ObjectBean crewCertificateDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.modify_certificate_info));
    }

    @Override
    protected void initData() {
        userId = CacheUtils.getString(this, Constants.ID);

        Intent intent = getIntent();
        CrewCertificateDetailsBean crewCertificateDetailsBean = (CrewCertificateDetailsBean) intent.getSerializableExtra(Constants.CREW_DETAILS_BEAN);
        crewCertificateDetails = crewCertificateDetailsBean.getData().getObject();
        int isUse = crewCertificateDetails.getIsUse();
        int isValid = crewCertificateDetails.getIsValid();
        etInputCertificateName.setHint(crewCertificateDetails.getName());
        etCertificateNumber.setHint(crewCertificateDetails.getCertifNo());
        tvIssuingAuthority.setText(crewCertificateDetails.getIssueOrganization());

        if (isValid == 1) {
            tvEffectiveDate.setHint(getResources().getString(R.string.permanent_effective));
        } else if (isValid == 2) {
        }

        String imgUrl = crewCertificateDetails.getImgUrl();
        if (StringUtils.isNotBlank(imgUrl)) {
            Type type = new TypeToken<ArrayList<UrlBean>>() {
            }.getType();
            ArrayList<UrlBean> urlLists = new Gson().fromJson(imgUrl, type);

                           /*          ShowPhotoAdapter showPhotoAdapter = new ShowPhotoAdapter(CrewCertificateDetailsActivity.this, urlLists, crewCertificateDetails.getDomain());
                                    gvCertificatePhoto.setAdapter(showPhotoAdapter);

            ShowPhotoAdapter showPhotoAdapter = new ShowPhotoAdapter(ModifyCrewCertificateActivity.this, urlLists, crewCertificateDetails.getDomain());
            gvCertificatePhoto.setAdapter(showPhotoAdapter);*/

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvResultPhoto.setLayoutManager(gridLayoutManager);
            CertificatePhotoAdapter certificatePhotoAdapter = new CertificatePhotoAdapter(this, urlLists, crewCertificateDetails.getDomain());
            rvResultPhoto.setAdapter(certificatePhotoAdapter);
        }

            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    scrollView.scrollTo(0, 0);
                }
            });
        }

        @Override
        public int getLayoutId () {
            return R.layout.activity_modify_crew_certificate;
        }

        @OnClick({R.id.iv_back, R.id.rg_is_effective, R.id.tv_effective_date, R.id.tv_add_certificate_photo, R.id.tv_crew_info_complete})
        public void onViewClicked (View view){
            switch (view.getId()) {
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.rg_is_effective:
                    break;
                case R.id.tv_effective_date:
                    break;
                case R.id.tv_add_certificate_photo:
                    break;
                case R.id.tv_crew_info_complete:
                    break;
            }
        }
    }
