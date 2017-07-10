package com.junhangxintong.chuangzhangtong.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.jaeger.library.StatusBarUtil;
import com.junhangxintong.chuangzhangtong.R;

import butterknife.ButterKnife;

/**
 * Created by edz on 2017/7/4.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        StatusBarUtil.setColor(BaseActivity.this, getResources().getColor(R.color.blue));
        ButterKnife.bind(this);

        initView();
        //默认情况下显示“首页”数据
        initData();

    }

    protected abstract void initView();

    protected abstract void initData();

    public abstract int getLayoutId();

    //启动一个新的activity
    public void goToActivity(Class activity,Bundle bundle){
        Intent intent = new Intent(this,activity);
        if(bundle != null){
            intent.putExtra("data",bundle);
        }
        startActivity(intent);
    }
    //设置背景透明度
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

}
