package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import butterknife.BindView;

public class ChooseCertificateTypeActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listview_choose_certificate_type)
    ListView listviewChooseCertificateType;
    String[] arrCertificates = {"身份证", "护照", "回乡证", "台胞证", "港澳通行证"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.certificate_type));
    }

    @Override
    protected void initData() {
        listviewChooseCertificateType.setAdapter(new ArrayAdapter(this, R.layout.item_choose_duty, R.id.tv_duty_name, arrCertificates));

        listviewChooseCertificateType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = getIntent();
                intent.putExtra(Constants.CERTIFICATE_TYPE, arrCertificates[i]);
                setResult(Constants.REQUEST_CODE6, intent);
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_certificate_type;
    }
}
