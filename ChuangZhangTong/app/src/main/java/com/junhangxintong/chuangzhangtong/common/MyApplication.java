package com.junhangxintong.chuangzhangtong.common;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by edz on 2017/7/4.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
