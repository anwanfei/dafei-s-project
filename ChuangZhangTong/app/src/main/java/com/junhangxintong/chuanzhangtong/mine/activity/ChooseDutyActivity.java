package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.common.NetServiceCodeBean;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.ConstantsUrls;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

public class ChooseDutyActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_input_duty)
    EditText etInputDuty;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private String[] arrDuties;
    private String userDuty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.duty));
    }

    @Override
    protected void initData() {
        arrDuties = new String[]{"船长", "大副", "二副", "三副", "水手长", "木匠", "大厨", "水手", "实习生"};
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_duty;
    }


    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                userDuty = etInputDuty.getText().toString();
                if (userDuty.isEmpty()) {
                    Toast.makeText(ChooseDutyActivity.this, getResources().getString(R.string.no_empty), Toast.LENGTH_SHORT).show();
                } else {
                    netCommitUserDuty();
                }
                netCommitUserDuty();
                break;
        }
    }

    private void netCommitUserDuty() {

        String userId = CacheUtils.getString(this, Constants.ID);

        NetUtils.postWithHeader(this, ConstantsUrls.MODIFY_USER_INFO)
                .addParams(Constants.USER_ID, userId)
                .addParams(Constants.POST_NAME, userDuty)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(ChooseDutyActivity.this, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response == null || response.equals("") || response.equals("null")) {
                            Toast.makeText(ChooseDutyActivity.this, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
                        } else {
                            NetServiceCodeBean netServiceErrortBean = new Gson().fromJson(response, NetServiceCodeBean.class);
                            String code = netServiceErrortBean.getCode();
                            Toast.makeText(ChooseDutyActivity.this, netServiceErrortBean.getMessage(), Toast.LENGTH_SHORT).show();
                            if (code.equals("601")) {
                                //清除了sp存储
                                getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
                                //保存获取权限的sp
                                CacheUtils.putBoolean(ChooseDutyActivity.this, Constants.IS_NEED_CHECK_PERMISSION, false);
                                startActivity(new Intent(ChooseDutyActivity.this, LoginRegisterActivity.class));
                                finish();
                            }
                            if (code.equals("200")) {
                                Intent intent = getIntent();
                                intent.putExtra(Constants.DUTY, userDuty);
                                setResult(Constants.REQUEST_CODE4, intent);
                                finish();
                            }
                        }
                    }
                });
    }
}
