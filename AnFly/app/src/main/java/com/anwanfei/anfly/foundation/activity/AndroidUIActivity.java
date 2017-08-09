package com.anwanfei.anfly.foundation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.anwanfei.anfly.R;
import com.anwanfei.anfly.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AndroidUIActivity extends BaseActivity {

    @BindView(R.id.lv_ui_list)
    ListView lvUiList;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String[] arrUIList = {"Dialog","CartView"};

    Class[] arrClasses = {DialogActivity.class,CartViewActivity.class};
    private List<String> uiLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.AndroidUI));
    }

    @Override
    protected void initData() {
        uiLists = new ArrayList<>();

        for (int i = 0; i < arrUIList.length; i++) {
            uiLists.add(arrUIList[i]);
        }

        lvUiList.setAdapter(new ArrayAdapter(this, R.layout.item_summary_fragment, R.id.tv_content, uiLists));

        lvUiList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(AndroidUIActivity.this, arrClasses[i]));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_android_ui;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
