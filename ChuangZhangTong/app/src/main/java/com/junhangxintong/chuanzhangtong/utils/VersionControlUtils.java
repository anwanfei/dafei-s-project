package com.junhangxintong.chuanzhangtong.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.junhangxintong.chuanzhangtong.common.MyApplication;

import static com.umeng.socialize.utils.ContextUtil.getPackageName;

/**
 * Created by anwanfei on 2017/9/23.
 */

public class VersionControlUtils {

    //获取当前程序的版本名
    public static String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = MyApplication.appContext.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        return packInfo.versionName;
    }

    public static int getVersionCode(){
        //获取packagemanager的实例
        PackageManager packageManager = MyApplication.appContext.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionCode;
    }
}
