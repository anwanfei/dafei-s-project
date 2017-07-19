package com.junhangxintong.chuangzhangtong.net;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.junhangxintong.chuangzhangtong.R;
import com.junhangxintong.chuangzhangtong.common.BaseActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodMenuActivity extends BaseActivity {

    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_query_synchronous)
    Button btQuerySynchronous;
    @BindView(R.id.bt_query_asynchronous)
    Button btQueryAsynchronous;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private Retrofit retrofit;
    private RequestService service;
    private String message;
    private String keywords;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        retrofit = Retrofit.getRetrofit();
        service = retrofit.getService();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_food_menu;
    }

    @OnClick({R.id.bt_query_synchronous, R.id.bt_query_asynchronous})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_query_synchronous:
                querySynchronous();
                break;
            case R.id.bt_query_asynchronous:
                queryAsynchronous();
                break;
        }
    }

    private void querySynchronous() {
        String etInputNum = etInput.getText().toString();
        if (etInputNum.isEmpty()) {
            Toast.makeText(FoodMenuActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        final Call<Bean> call = service.getMenuById(etInputNum);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Bean body = call.execute().body();
                    message = body.getMessage();
                    name = body.getName();
                    keywords = body.getKeywords();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv2.setText("名称：" + name + "\n关键字：" + keywords + "\n详情：" + message);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void queryAsynchronous() {
        String etInputNum = etInput.getText().toString();
        if (etInputNum.isEmpty()) {
            Toast.makeText(FoodMenuActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Bean> call = service.getMenuById(etInputNum);
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Bean body = response.body();
                name = body.getName();
                keywords = body.getKeywords();
                message = body.getMessage();
                tv2.setText("名称：" + name + "\n关键字：" + keywords + "\n详情：" + message);
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                Log.e("TAG", "call===========" + call.toString());
                t.printStackTrace();
                Log.e("TAG", "t============" + t.getMessage().toString());
            }
        });
    }
}
