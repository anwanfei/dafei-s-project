package com.junhangxintong.chuanzhangtong.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.junhangxintong.chuanzhangtong.R;

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

    //格式化
    public static String formatDate(int arrive_year, int arrive_month, int arrive_day) {
        return arrive_year + "-" + arrive_month + "-" + arrive_day;
    }

    public static String formatTime(int arrive_hour, int arrive_min) {
        return "  " + arrive_hour + ":" + arrive_min;
    }

    //时间、日期选择器
    public static void getDateTime(Context mContext, final TextView textView) {
        View date_time_picker = View.inflate(mContext, R.layout.date_time_picker, null);
        final DatePicker datePicker = (DatePicker) date_time_picker.findViewById(R.id.data_picker);
        final TimePicker timePicker = (TimePicker) date_time_picker.findViewById(R.id.timer_picker);

        timePicker.setIs24HourView(true);
        //   Build   DateTimeDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(date_time_picker);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String dateStr = datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth();
                int currentMinute = timePicker.getCurrentMinute();
                String curMinute = "";
                if (currentMinute < 10) {
                    curMinute = "0" + currentMinute;
                } else {
                    curMinute = String.valueOf(currentMinute);
                }
                String timeStr = timePicker.getCurrentHour() + ":" + curMinute;
                textView.setText(dateStr + "  " + timeStr);
            }
        });
        builder.show();
    }
}
