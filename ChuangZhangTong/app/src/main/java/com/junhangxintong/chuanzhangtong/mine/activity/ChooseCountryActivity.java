package com.junhangxintong.chuanzhangtong.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.junhangxintong.chuanzhangtong.R;
import com.junhangxintong.chuanzhangtong.common.BaseActivity;
import com.junhangxintong.chuanzhangtong.mine.bean.CountriesBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.getJsonFromAssets;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChooseCountryActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listview_choose_country)
    ListView listviewChooseCountry;
    private List<String> countriesName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        tvTitle.setText(getResources().getString(R.string.choose_country));
        ivBack.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        countriesName = new ArrayList<>();
        String json = getJsonFromAssets.getJson("countries.json", this);
        Type type = new TypeToken<ArrayList<CountriesBean>>() {
        }.getType();
        ArrayList<CountriesBean> countries = new Gson().fromJson(json, type);
        for (int i = 0; i < countries.size(); i++) {
            countriesName.add(countries.get(i).getName());
        }
        listviewChooseCountry.setAdapter(new ArrayAdapter(this, R.layout.item_choose_duty, R.id.tv_duty_name, countriesName));
        listviewChooseCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = getIntent();
                intent.putExtra(Constants.COUNTRY, countriesName.get(i));
                setResult(Constants.REQUEST_CODE5, intent);
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_choose_country;
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
