package com.junhangxintong.chuanzhangtong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anwanfei on 2017/8/22.
 */

public class DateUtil {
    public static String getCurrentTimeMDHM() {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        Date date = new Date(currentTimeMillis);
        return simpleDateFormat.format(date);
    }
    public static String getCurrentTimeYMDHMS() {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(currentTimeMillis);
        return simpleDateFormat.format(date);
    }

    public static String getCurrentTimeMD() {
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Date date = new Date(currentTimeMillis);
        return simpleDateFormat.format(date);
    }

}
