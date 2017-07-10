package com.metashipanwf.androidsummarize.retrofit;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.metashipanwf.androidsummarize.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallActivity extends Activity {

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.bt_query_synchronous)
    Button btQuerySynchronous;
    @BindView(R.id.bt_query_asynchronous)
    Button btQueryAsynchronous;
    @BindView(R.id.tv_show_retrfit)
    TextView tvShowRetrfit;
    private Retrofit retrofit;
    private RequestServes service;
    private Bean result;
    private String msg;
    private String name;
    private String keywords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = Retrofit.getRetrofit();
        service = retrofit.getService();
    }

    @OnClick({R.id.bt_query_synchronous, R.id.bt_query_asynchronous})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_query_synchronous:
                querySynchronous();
                break;
            case R.id.bt_query_asynchronous:
                queryAsynchronous();
                break;
        }
    }

    private void queryAsynchronous() {
        String num = etInput.getText().toString();

        if (num.isEmpty()) {
            Toast.makeText(CallActivity.this, "请输入ID", Toast.LENGTH_SHORT).show();
            return;
        }
        final Call<Bean> call = service.getMenuById(num);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = call.execute().body();
                    msg = result.getMessage();
                    name = result.getName();
                    keywords = result.getKeywords();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvShowRetrfit.setText("名称：" + name + "\n关键字：" + keywords + "\n详情：" + msg);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    private void querySynchronous() {
        String num = etInput.getText().toString();

        if (num.isEmpty()) {
            Toast.makeText(CallActivity.this, "请输入ID", Toast.LENGTH_SHORT).show();
            return;
        }
        final Call<Bean> call = service.getMenuById(num);
        //异步请求
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                if (response.isSuccessful()) {
                    Bean result = response.body();//关键
                    if (result != null) {
                        String msg = result.getMessage();
                        String name = result.getName();
                        String keywords = result.getKeywords();
                        tvShowRetrfit.setText("名称：" + name + "\n关键字：" + keywords + "\n详情：" + msg);
                    }
                }
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                Log.e("TAG", "call===========" + call.toString());
                Log.e("TAG", "t============" + t.getLocalizedMessage());
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "详细做法");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("详细做法")
                .setMessage("1.添加网络权限\n" +
                        "2.导依赖包（三个）\n" +
                        "3.定义一个接口：封装URL地址和数据请求方式\n" +
                        "4.通过一个类，提供Retrofit对象和RequestServes接口服务对象\n" +
                        "5.在activity中，获取Retrofit和Requestservice对象，并且得到我们的Call对象请求网络（同步异步）\n" +
                        "注意：同步请求一定要在子线程中")
                .setPositiveButton("学会了",null)
                .show();
        return super.onOptionsItemSelected(item);
    }
}
