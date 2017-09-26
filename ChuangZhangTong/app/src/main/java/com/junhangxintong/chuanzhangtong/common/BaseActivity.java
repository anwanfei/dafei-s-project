package com.junhangxintong.chuanzhangtong.common;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.junhangxintong.chuanzhangtong.R;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.ButterKnife;

/**
 * Created by edz on 2017/7/4.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());

        StatusBarUtil.setColor(BaseActivity.this, getResources().getColor(R.color.blue));
        ButterKnife.bind(this);

        /*//友盟场景接口设置
        MobclickAgent.setDebugMode(true);
        MobclickAgent.openActivityDurationTrack(false);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);*/

        initView();
        //默认情况下显示“首页”数据
        initData();
    }

    protected abstract void initView();

    protected abstract void initData();

    public abstract int getLayoutId();

    //启动一个新的activity
    public void goToActivity(Class activity, Bundle bundle) {
        Intent intent = new Intent(this, activity);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        startActivity(intent);
    }

    //设置背景透明度
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    //进度对话框
    public ProgressDialog getProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在上传中...");
        progressDialog.show();
        return progressDialog;
    }

    //然后再重写三个方法
    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ///对于好多应用，会在程序中杀死 进程，这样会导致我们统计不到此时Activity结束的信息，
            ///对于这种情况需要调用 'MobclickAgent.onKillProcess( Context )'
            ///方法，保存一些页面调用的数据。正常的应用是不需要调用此方法的。
            MobclickAgent.onKillProcess(this);
            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
