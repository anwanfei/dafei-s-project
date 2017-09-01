package com.metaship.wenjiang.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.metaship.app.yzlib.net.HttpUtils;

/**
 * author:AnWanfei
 * time:2016/11/26.
 * Email:anwanfei_sp@163.com
 * function:
 */
public class CacheUtils {

    public static final String SHAREPRENFERENCE_NAME = "metaship";
    private static SharedPreferences sp;

    //保存参数
    public static void putString(Context context, String key, String values) {
        SharedPreferences sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        sp.edit().putString(key, values).commit();

        // add by live106 @date 2017-06-01
        // 监听用户token变化，赋值给HttpUtils方便使用
        if (key.equals(Constants.TOKEN)) {
            HttpUtils.getDefault().setUserToken(values);
        }
    }

    public static String getUserId(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        return sp.getString(Constants.USERID, "");
    }

    //获取参数
    public static String getString(Context context, String key) {

        SharedPreferences sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        return sp.getString(key, "");
    }

    //存值
    public static void putBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    //取值
    public static boolean getBoolean(Context context, String key, boolean defult) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        }
        return sp.getBoolean(key, defult);
    }


}
