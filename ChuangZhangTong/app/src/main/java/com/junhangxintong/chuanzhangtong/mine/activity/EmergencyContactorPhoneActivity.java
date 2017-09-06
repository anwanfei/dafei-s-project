package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceErrortBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.MultiVerify;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class EmergencyContactorPhoneActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.tv_save)
    TextView tvSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.emergency_contactor_phone));
    }

    @Override
    protected void initData() {
        etInputName.setHint(getResources().getString(R.string.input_emergency_contactor_phone));
        etInputName.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_name;
    }

    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                netCommitEmergencyContactPhone();
                break;
        }
    }

    private void netCommitEmergencyContactPhone() {

        String userId = CacheUtils.getString(this, Constants.ID);
        final String emergencyPhone = etInputName.getText().toString();
        boolean mobile = MultiVerify.isMobile(emergencyPhone);
        if (mobile) {
            NetUtils.postWithHeader(this, ConstantsUrls.MODIFY_USER_INFO)
                    .addParams(Constants.USER_ID, userId)
                    .addParams(Constants.CONTACT_PERSON_PHONE, emergencyPhone)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(EmergencyContactorPhoneActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(EmergencyContactorPhoneActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                NetServiceErrortBean netServiceErrortBean = new Gson().fromJson(response, NetServiceErrortBean.class);
                                String code = netServiceErrortBean.getCode();
                                Toast.makeText(EmergencyContactorPhoneActivity.this, netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                                if (code.equals("601")) {
                                    startActivity(new Intent(EmergencyContactorPhoneActivity.this, LoginRegisterActivity.class));
                                    finish();
                                }
                                if (code.equals("200")) {
                                    Intent intent = getIntent();
                                    intent.putExtra(Constants.EMERGENCY_CONTACTOR_PHONE, emergencyPhone);
                                    setResult(Constants.REQUEST_CODE3, intent);
                                    finish();
                                }
                            }
                        }
                    });
        } else {
            Toast.makeText(EmergencyContactorPhoneActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
        }


    }
}
