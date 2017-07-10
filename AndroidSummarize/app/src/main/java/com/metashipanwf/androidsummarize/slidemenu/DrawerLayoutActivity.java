package com.metashipanwf.androidsummarize.slidemenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.metashipanwf.androidsummarize.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerLayoutActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.content_frame)
    FrameLayout contentFrame;
    @BindView(R.id.left_drawer)
    ListView leftDrawer;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private String[] mPlanetTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        ButterKnife.bind(this);

        initData();
        initListener();
    }

    private void initListener() {

        tvBack.setOnClickListener(this);
        leftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int position, long id) {
            }
        });
    }

    private void initData() {
        tvBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("侧滑菜单");

        //绑定listview
        mPlanetTitles = getResources().getStringArray(R.array.anim_type);
        leftDrawer.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mPlanetTitles));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back :
                finish();
                break;
        }
    }

}
