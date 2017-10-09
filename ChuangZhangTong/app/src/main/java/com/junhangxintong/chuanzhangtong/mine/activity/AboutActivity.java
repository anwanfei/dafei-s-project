package com.junhangxintong.chuanzhangtong.mine.activity;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.utils.VersionControlUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    private static int arrive_year;
    private static int arrive_month;
    private static int arrive_day;
    private static int arrive_hour;
    private static int arrive_min;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_about_version)
    TextView tvAboutVersion;
    @BindView(R.id.tv_check_updata)
    TextView tvCheckUpdata;
    @BindView(R.id.tv_feed_back)
    TextView tvFeedBack;
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.tv_app_verstion)
    TextView tvAppVerstion;
    // TODO: 2017/9/23 以后从服务器获取
    private int serviceVersionCode = 2;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.about));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @OnClick({R.id.iv_back, R.id.tv_score, R.id.tv_about_version, R.id.tv_check_updata, R.id.tv_feed_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_score:
                Toast.makeText(AboutActivity.this, getResources().getString(R.string.developing), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_about_version:
                break;
            case R.id.tv_check_updata:
                checkVersion();
                break;
            case R.id.tv_feed_back:
                startActivity(new Intent(AboutActivity.this, FeedbackActivity.class));
                break;
        }
    }

    //对比本程序的版本号和最新程序的版本号
    public void checkVersion() {
        //如果检测本程序的版本号小于服务器的版本号，那么提示用户更新
        if (VersionControlUtils.getVersionCode() < serviceVersionCode) {
            //弹出提示版本更新的对话框
            showDialogUpdate();
        } else {
            //否则吐司，说现在是最新的版本
            Toast.makeText(AboutActivity.this, getResources().getString(R.string.newest_version), Toast.LENGTH_SHORT).show();

        }
    }

    private void showDialogUpdate() {
        View inflate = View.inflate(this, R.layout.dialog_clear_butter, null);
        TextView tv_cancel_clear_buffer = (TextView) inflate.findViewById(R.id.tv_cancel_clear_buffer);
        TextView tv_ok_clear_butter = (TextView) inflate.findViewById(R.id.tv_ok_clear_butter);
        TextView tv_dialog_title = (TextView) inflate.findViewById(R.id.tv_dialog_title);
        TextView tv_dialog_message = (TextView) inflate.findViewById(R.id.tv_dialog_message);

        tv_dialog_title.setText(getResources().getString(R.string.version_updata));
        tv_dialog_message.setText(getResources().getString(R.string.please_updata_version));

        tv_cancel_clear_buffer.setOnClickListener(this);
        tv_ok_clear_butter.setOnClickListener(this);
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        dialog = new Dialog(this, R.style.style_dialog);
        dialog.setContentView(inflate);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void loadNewVersionProgress() {
        final String uri = "http://www.apk.anzhi.com/data3/apk/201703/14/4636d7fce23c9460587d602b9dc20714_88002100.apk";
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage(getString(R.string.downloading_new_version));
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Toast.makeText(getApplicationContext(), R.string.download_new_version_fail, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private File getFileFromServer(String uri, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time + "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel_clear_buffer:
                dialog.dismiss();
                break;
            case R.id.tv_ok_clear_butter:
                //Toast.makeText(MainActivity.this, "选择确定哦", 0).show();
                loadNewVersionProgress();//下载最新的版本程序
                dialog.dismiss();
                break;
        }
    }
}
