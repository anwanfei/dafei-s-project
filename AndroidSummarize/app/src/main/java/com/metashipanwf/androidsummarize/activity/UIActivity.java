package com.metashipanwf.androidsummarize.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.DialogActivity.DialogActivity;
import com.metashipanwf.androidsummarize.R;

/**
 * author:AnWanfei
 * time:2017/2/16.
 * Email:anwanfei_sp@163.com
 * function:
 */

public class UIActivity extends Activity implements View.OnClickListener {

    private ListView listview;
    private String[] data = {"Dialog（对话框）", "View（视图）", "Layout（布局）", "Popwindown", "Menu","TextView(文本视图)","Editext（编辑视图）","Button（按钮）"
            ,"Listview（列表）","Notification（通知栏）","Imageview（图像视图）","GridView（格视图）","CheckBox（复选框）","scrollview（卷轴视图）","Gallery（长廊视图）",
            "ActionBar（导航栏）","RatingBar（评分栏）","TabHost（切换卡）","SeekBar(拖动条)"
            ,"Spinner（分级列表）","SlidingDrawer（抽屉）","SurfaceView","ViewFliper","、Splash","Toast","AlarmManager","webview","wiget","theme和style","多语言",
            "界面特效","","","","","","","","","","","","","",""
            ,"","","","","","","","","","","","",""
            ,"","","","","","","","","","","","",""};
    private TextView tv_title;
    private TextView tv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initData();

        initListener();
    }

    private void initListener() {

        tv_back.setOnClickListener(this);
    }

    private void initView() {
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.summarize_layout);
        listview = (ListView) findViewById(R.id.listview);
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_back = (TextView)findViewById(R.id.tv_back);
        tv_back.setVisibility(View.VISIBLE);
    }

    private void initData() {

        tv_title.setText("用户界面");

        ArrayAdapter<String> adapter = new ArrayAdapter(UIActivity.this, R.layout.item_summarize, data);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String string = data[i];
                switch (string) {
                    case "对话框":
                        startActivity(new Intent(UIActivity.this, com.metashipanwf.androidsummarize.activity.DialogActivity.class));
                        break;
                    case "发送手机验证码":
                        startActivity(new Intent(UIActivity.this, MainActivity.class));
                        break;
                    case "练习界面":
                        startActivity(new Intent(UIActivity.this, DialogActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
