package com.metashipanwf.androidsummarize.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.metashipanwf.androidsummarize.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VibratorActivity extends Activity {

    @BindView(R.id.btn_short)
    Button btnShort;
    @BindView(R.id.btn_long)
    Button btnLong;
    @BindView(R.id.btn_rhythm)
    Button btnRhythm;
    @BindView(R.id.activity_vibrator)
    LinearLayout activityVibrator;
    private Vibrator vibrator;
    private boolean isChecked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrator);
        ButterKnife.bind(this);

        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);


    }

    @OnClick({R.id.btn_short, R.id.btn_long, R.id.btn_rhythm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_short:
                shortVibrator();
                break;
            case R.id.btn_long:
                longVibrator();
                break;
            case R.id.btn_rhythm:
                rtythmVibrator();
                break;
        }
    }

    private void rtythmVibrator() {
        if (isChecked) {
            // 设置震动周期
            vibrator.vibrate(new long[]{1000, 10, 1000, 10, 1000}, 0);
            isChecked = false;
            showToast("OK");
        } else {
            // 取消震动
            vibrator.cancel();
            isChecked = true;
            showToast("CANCEL");
        }
    }

    // 第一个参数为设置震动的效果的数组，第一个参数为等待指定时间后开始震动，震动时间为第二个参数。后边的参数依次为等待震动时间和震动的时间
    // 第二个参数为 -1表示只震动一次，为0则震动会一直持续。
    private void longVibrator() {

        if (isChecked) {
            // 设置震动周期
            vibrator.vibrate(new long[]{1, 1000, 1, 1000}, 0);
            isChecked = false;
            showToast("OK");
        } else {
            // 取消震动
            vibrator.cancel();
            isChecked = true;
            showToast("CANCEL");
        }
    }

    private void shortVibrator() {
        if (isChecked) {
            // 设置震动周期
            vibrator.vibrate(new long[]{1000, 10, 100, 1000}, -1);
            isChecked = false;
            showToast("OK");
        } else {
            // 取消震动
            vibrator.cancel();
            isChecked = true;
            showToast("CANCEL");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //四个参数，groupid, itemid, orderid, title
        menu.add(0, 0, 0, "详细做法");
        menu.add(0, 1, 1, "菜单加载布局");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                showDetails();
                break;
            case 1:
                showMenu();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMenu() {
        new AlertDialog.Builder(this)
                .setTitle("菜单加载布局")
                .setMessage("1.动态加载：\n" +
                        "onCreateOptionsMenu(Menu menu) {\n" +
                        "        menu.add(groupid,itemid,orderid,title);\n" +
                        "        return true;\n" +
                        "    });\n" +
                        "2.布局加载\n" +
                        "①MenuInflater inflater = this.getMenuInflater();\n" +
                        "inflater.inflate(R.menu.menu, menu);\n" +
                        "②menu布局如下：\n" +
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<menu xmlns:android=\"http://schemas.android.com/apk/res/android\">\n" +
                        "    <item\n" +
                        "        android:id=\"@+id/utf8\"\n" +
                        "        android:title=\"@string/about\" />\n" +
                        "</menu>\n" +
                        "3.点击监听：" +
                        "onOptionsItemSelected(MenuItem item) {\n" +
                        "        switch (item.getItemId()) {\n" +
                        "            case 0:\n" +
                        "                break;\n" +
                        "            case 1:\n" +
                        "                break;\n" +
                        "        return ture;\n" +
                        "    }")
                .setPositiveButton("学会了", null)
                .show();
    }

    private void showDetails() {
        new AlertDialog.Builder(this)
                .setTitle("详细做法")
                .setMessage("1.添加震动权限\n" +
                        "2.振动器实例化：vibrator=(Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);\n" +
                        "3.设置震动周期：vibrator.vibrate(new long[]{等待震动时间, 震动时间, 等待震动时间, 震动时间...}, 0或-1);0表示一直震，-1表示震一次\n" +
                        "4.取消震动：vibrator.cancel();\n")
                .setPositiveButton("学会了", null)
                .show();
    }
}
