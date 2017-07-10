package com.atguigu.contexttest;

import android.app.Application;
import android.util.Log;

/**
 * Created by shkstart on 2016/8/15 0015.
 */
public class MyApplication extends Application {

    public MyApplication(){
        Log.e("TAG", "MyApplication()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG", "application onCreate()");
    }
}
