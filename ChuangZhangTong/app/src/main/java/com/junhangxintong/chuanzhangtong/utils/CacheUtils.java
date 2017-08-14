package com.junhangxintong.chuanzhangtong.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by anwanfei on 2017/7/10.
 */

public class CacheUtils {
    public static final String SHAREPRENFERENCE_NAME = "junhangxintong";
    private static SharedPreferences sp;

    //保存string
    public static void putString(Context context, String key, String values) {
        sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        sp.edit().putString(key, values).commit();
    }

    //获取string
    public static String getString(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        return sp.getString(key, "");
    }

    //保存boolean
    public static void putBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    //获取boolean
    public static boolean getBoolean(Context context, String key, boolean defult) {
        if (sp == null) {
            sp = context.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE | context.MODE_MULTI_PROCESS);
        }
        return sp.getBoolean(key, defult);
    }
}
