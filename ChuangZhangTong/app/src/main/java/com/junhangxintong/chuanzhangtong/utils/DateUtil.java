package com.junhangxintong.chuanzhangtong.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    //获取日期对话框，格式2017/8/29
    public static void showChooseTimeDialog(Context mContext, final TextView textView) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
            }
        }, year, monthOfYear - 1, dayOfMonth);
        datePickerDialog.show();
    }

}
