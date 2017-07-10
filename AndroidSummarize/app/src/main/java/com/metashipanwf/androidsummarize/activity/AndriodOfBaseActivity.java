package com.metashipanwf.androidsummarize.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;
import com.metashipanwf.androidsummarize.adapter.MyExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AndriodOfBaseActivity extends Activity implements View.OnClickListener {

    private ExpandableListView expandableListView;
    private TextView tv_title;
    private List<String> groupArray;
    private List<List<String>> childArray;
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
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_andriod_of_base);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        tv_title = (TextView)findViewById(R.id.tv_title);
        tv_back = (TextView)findViewById(R.id.tv_back);
    }

    private void initData() {

        tv_title.setVisibility(View.VISIBLE);
        tv_back.setVisibility(View.VISIBLE);
        tv_title.setText("Android基础");

        groupArray = new ArrayList<String>();
        childArray = new ArrayList<List<String>>();

        groupArray.add("第一行");
        groupArray.add("第二行");

        List<String> tempArray = new ArrayList<String>();
        tempArray.add("1");
        tempArray.add("2");
        tempArray.add("3");

        for (int index = 0; index < groupArray.size(); ++index) {
            childArray.add(tempArray);
        }

        MyExpandableListViewAdapter adapter = new MyExpandableListViewAdapter(AndriodOfBaseActivity.this,childArray,groupArray);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
