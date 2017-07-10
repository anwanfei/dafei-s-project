package com.atguigu.p2p.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.p2p.MainActivity;
import com.atguigu.p2p.R;
import com.atguigu.p2p.bean.Login;
import com.atguigu.p2p.common.AppManager;
import com.atguigu.p2p.common.AppNetConfig;
import com.atguigu.p2p.common.BaseActivity;
import com.atguigu.p2p.util.MD5Utils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.iv_common_back)
    ImageView ivCommonBack;
    @Bind(R.id.tv_common_title)
    TextView tvCommonTitle;
    @Bind(R.id.iv_common_setting)
    ImageView ivCommonSetting;
    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.log_ed_mob)
    EditText logEdMob;
    @Bind(R.id.about_com)
    RelativeLayout aboutCom;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.log_ed_pad)
    EditText logEdPad;
    @Bind(R.id.log_log_btn)
    Button logLogBtn;

    @Override
    protected void initTitle() {
        ivCommonBack.setVisibility(View.VISIBLE);
        ivCommonSetting.setVisibility(View.INVISIBLE);
        tvCommonTitle.setText("用户登录");
    }

    @Override
    protected void initData() {

    }

    //单击“登录”按钮的回调
    @OnClick(R.id.log_log_btn)
    public void login(View view){
        String userName = logEdMob.getText().toString().trim();
        String password = logEdPad.getText().toString().trim();
        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)){
            //联网操作，校验用户名和密码
            RequestParams params = new RequestParams();
            //提供请求参数
            params.put("username",userName);
            params.put("password", MD5Utils.MD5(password));
            client.post(AppNetConfig.LOGIN,params,new AsyncHttpResponseHandler(){

                @Override
                public void onSuccess(String content) {
                    JSONObject jsonObject = JSON.parseObject(content);

                    if(jsonObject.getBoolean("success")){
                        String data = jsonObject.getString("data");
                        Login login = JSON.parseObject(data, Login.class);

                        //保存登录数据
                        saveLogin(login);
                        //退出登录界面
                        AppManager.getInstance().removeAll();
                        //启动新的activity
                        goToActivity(MainActivity.class,null);

                    }else{
                        Toast.makeText(LoginActivity.this, "用户名或密码不正确", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Throwable error, String content) {
                    Toast.makeText(LoginActivity.this, "联网失败", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.iv_common_back)
    public void back(View view){
        closeCurrentActivity();
    }


}
