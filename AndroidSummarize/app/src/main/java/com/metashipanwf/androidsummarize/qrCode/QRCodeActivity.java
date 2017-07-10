package com.metashipanwf.androidsummarize.qrCode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.metashipanwf.androidsummarize.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QRCodeActivity extends Activity {

    @BindView(R.id.btn_qr)
    Button btnQr;
    @BindView(R.id.tv_show_qr)
    TextView tvShowQr;
    @BindView(R.id.activity_qrcode)
    LinearLayout activityQrcode;
    @BindView(R.id.wv_show_qr)
    WebView wvShowQr;
    @BindView(R.id.btn_qr_visit)
    Button btnQrVisit;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            btnQrVisit.setVisibility(View.VISIBLE);
            url = data.getStringExtra("text");
            tvShowQr.setText("您扫描到的网址：\n" + url); // 显示识别到的文字
        }
    }

    @OnClick({R.id.btn_qr, R.id.btn_qr_visit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qr:
                startActivityForResult(new Intent(this, ScannerActivity.class), 1);
                break;
            case R.id.btn_qr_visit:
                if (!url.equals(null)) {
                    WebSettings webSettings  = wvShowQr.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    wvShowQr.loadUrl(url);// 将识别的内容当作网址加载到WebView
                } else {
                    Toast.makeText(QRCodeActivity.this, "网址不合法或者为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
