package com.lingling.anwanfei.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingling.anwanfei.android.R;

public class RegisterActivity extends Activity implements View.OnClickListener {

    private TextView tv_title;
    private ImageView iv_back;
    private TextView tv_register_sendcode;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_useraccount);

        initView();
        initData();
        initListener();
    }

    private void initListener() {
        iv_back.setOnClickListener(this);
        tv_register_sendcode.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_register_sendcode = (TextView) findViewById(R.id.tv_register_sendcode);
        btn_register = (Button) findViewById(R.id.btn_register);
    }

    private void initData() {
        tv_title.setText("注册");
        iv_back.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_register_sendcode:

                break;
            case R.id.btn_register:

                break;
        }
    }
}
