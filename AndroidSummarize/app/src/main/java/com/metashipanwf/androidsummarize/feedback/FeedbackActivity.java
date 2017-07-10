package com.metashipanwf.androidsummarize.feedback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.metashipanwf.androidsummarize.R;

public class FeedbackActivity extends Activity implements View.OnClickListener {

    private EditText et_message;
    private Button btn_sumbit;
    private TextView tv_title;
    private TextView tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initVIew();
        initData();
        initListenr();
    }

    private void initListenr() {
        btn_sumbit.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    private void initData() {
        tv_title.setVisibility(View.VISIBLE);
        tv_back.setVisibility(View.VISIBLE);
        tv_title.setText(getString(R.string.feed_back_title));

    }

    private void initVIew() {
        setContentView(R.layout.activity_feedback);
        et_message = (EditText) findViewById(R.id.et_message);
        btn_sumbit = (Button) findViewById(R.id.btn_sumbit);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_back = (TextView) findViewById(R.id.tv_back);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sumbit:
                sumbit();
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

    private void sumbit() {
        if (et_message.getText().toString().equals("")) {
            Toast.makeText(FeedbackActivity.this, "请输入意见内容再提交，谢谢^_^", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FeedbackActivity.this, "感谢您的意见！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
