package com.junhangxintong.chuanzhangtong.mine.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.SendVerifyCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.MultiVerify;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.commons.lang.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class ModifyPhoneActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_phone)
    EditText etInputPhone;
    @BindView(R.id.et_input_verify_code)
    EditText etInputVerifyCode;
    @BindView(R.id.tv_send_verify_code)
    TextView tvSendVerifyCode;
    @BindView(R.id.et_input_pwd)
    EditText etInputPwd;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private String oldPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.change_phone_num));
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        oldPhoneNum = intent.getStringExtra(Constants.PHONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_phone;
    }

    @OnClick({R.id.iv_back, R.id.tv_send_verify_code, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_send_verify_code:
                netSendVerifyCode();
                break;
            case R.id.tv_save:
                netConfirmChangePhone();
                break;
        }
    }

    private void netConfirmChangePhone() {
        String newPhoneNum = etInputPhone.getText().toString();

        if (newPhoneNum.equals("")) {
            Toast.makeText(ModifyPhoneActivity.this, getResources().getString(R.string.input_phone), Toast.LENGTH_SHORT).show();
            return;
        }
        if (oldPhoneNum.equals("")) {
            Toast.makeText(ModifyPhoneActivity.this, getResources().getString(R.string.no_get_cur_phone), Toast.LENGTH_SHORT).show();
            return;
        }

        if (newPhoneNum.equals(oldPhoneNum)) {
            View view = LinearLayout.inflate(this, R.layout.dialog_hint, null);
            TextView tv_ok = (TextView) view.findViewById(R.id.tv_ok);

            AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.style_dialog);
            dialog.setView(view);
            final AlertDialog show = dialog.show();

            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    show.dismiss();
                }
            });
        } else {
            netChangePhoneNum();
        }
    }

    private void netChangePhoneNum() {
        final String newPhone = etInputPhone.getText().toString();
        String userId = CacheUtils.getString(this, Constants.ID);
        String verifyCode = etInputVerifyCode.getText().toString();

        if(StringUtils.isEmpty(newPhone)) {
            Toast.makeText(ModifyPhoneActivity.this, getResources().getString(R.string.input_new_phone), Toast.LENGTH_SHORT).show();
            return;
        }

        if(verifyCode.equals("")){
            Toast.makeText(ModifyPhoneActivity.this, getResources().getString(R.string.input_verify_code), Toast.LENGTH_SHORT).show();
        } else {
            NetUtils.postWithHeader(this, ConstantsUrls.COMMIT_MODIFY_PHONE)
                    .addParams(Constants.PHONE, newPhone)
                    .addParams(Constants.VCODE, verifyCode)
                    .addParams(Constants.USER_ID, userId)
                    .build()
                    .execute(new NetUtils.MyStringCallback() {
                        @Override
                        protected void onSuccess(String response, String message) {
                            Intent intent = getIntent();
                            intent.putExtra(Constants.CONTACT_PHONE,newPhone );
                            setResult(Constants.REQUEST_CODE1, intent);
                            finish();
                        }
                    });
        }

    }


    private void netSendVerifyCode() {

        String PhoneNum = etInputPhone.getText().toString();
        boolean mobile = MultiVerify.isMobile(PhoneNum);
        if (mobile) {
            NetUtils.postWithNoHeader(this, ConstantsUrls.MODIFY_PHONE_SENDSMS)
                    .addParams(Constants.PHONE, PhoneNum)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(ModifyPhoneActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if (response == null || response.equals("") || response.equals("null")) {
                                Toast.makeText(ModifyPhoneActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                            } else {
                                SendVerifyCodeBean sendVerifyCode = new Gson().fromJson(response, SendVerifyCodeBean.class);
                                String message = sendVerifyCode.getMessage();
                                Toast.makeText(ModifyPhoneActivity.this, message, Toast.LENGTH_SHORT).show();

                                new CountDownTimer(Constants.COUNTDOWN_TIME, Constants.COUNTDOWN_TIME_SPEED) {
                                    @Override
                                    public void onTick(long l) {
                                        tvSendVerifyCode.setEnabled(false);
                                        tvSendVerifyCode.setText(Constants.HAS_SENDED + "(" + l / Constants.COUNTDOWN_TIME_SPEED + ")");
                                    }

                                    @Override
                                    public void onFinish() {
                                        tvSendVerifyCode.setEnabled(true);
                                        tvSendVerifyCode.setText(Constants.GET_VERIFYCODE_AGAIN);
                                    }
                                }.start();
                            }
                        }
                    });
        } else {
            Toast.makeText(ModifyPhoneActivity.this, getResources().getString(R.string.phone_cannot_empty), Toast.LENGTH_SHORT).show();
        }
    }
}
