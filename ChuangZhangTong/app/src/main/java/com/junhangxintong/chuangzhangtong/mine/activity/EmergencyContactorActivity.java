package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class EmergencyContactorActivity extends BaseActivity {

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
        tvTitle.setText(getResources().getString(R.string.emergency_contactor));
    }

    @Override
    protected void initData() {
        etInputName.setHint(getResources().getString(R.string.input_emergency_contactor));
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
                String contact_phone = etInputName.getText().toString();
                Intent intent = getIntent();
                intent.putExtra(Constants.EMERGENCY_CONTACTOR, contact_phone);
                setResult(Constants.REQUEST_CODE2, intent);
                finish();
                break;
        }
    }
}
