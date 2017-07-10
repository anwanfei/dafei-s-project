package com.atguigu.contexttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context applicationContext = this.getApplicationContext();

        Application application = this.getApplication();

        Log.e("TAG", (applicationContext == application) + "");
        
        new AlertDialog.Builder(this.getApplicationContext())

                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
    }
}
