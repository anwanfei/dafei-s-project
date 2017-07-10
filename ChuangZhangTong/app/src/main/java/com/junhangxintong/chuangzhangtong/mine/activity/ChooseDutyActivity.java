package com.junhangxintong.chuangzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;
import com.junhangxintong.chuangzhangtong.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseDutyActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listview_choose_duty)
    ListView listviewChooseDuty;
    private String[] arrDuties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setText(getResources().getString(R.string.duty));
    }

    @Override
    protected void initData() {
        arrDuties = new String[]{"船长", "大副", "二副", "三副", "水手长", "木匠", "大厨", "水手", "实习生"};

        listviewChooseDuty.setAdapter(new ArrayAdapter(this, R.layout.item_choose_duty,R.id.tv_duty_name, arrDuties));

        listviewChooseDuty.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = getIntent();
                intent.putExtra(Constants.DUTY, arrDuties[i]);
                setResult(Constants.REQUEST_CODE4, intent);
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_duty;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
