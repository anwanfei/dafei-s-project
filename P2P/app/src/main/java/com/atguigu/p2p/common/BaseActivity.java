package com.atguigu.p2p.common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.atguigu.p2p.bean.Login;
import com.loopj.android.http.AsyncHttpClient;

import butterknife.ButterKnife;

/**
 * Created by shkstart on 2016/8/18 0018.
 * 作为所有activity的通用父类
 */
public abstract class BaseActivity extends FragmentActivity {

    protected AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        //将当前的activity添加到栈管理器中
        AppManager.getInstance().add(this);

        initTitle();
        //默认情况下显示“首页”数据
        initData();

    }

    protected abstract void initTitle();

    protected abstract void initData();

    public abstract int getLayoutId();

    //保存用户登录的数据
    public void saveLogin(Login login){
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("UF_ACC",login.UF_ACC);
        editor.putString("UF_PHONE",login.UF_PHONE);
        editor.putString("UF_IS_CERT",login.UF_IS_CERT);
        editor.putString("UF_AVATAR_URL",login.UF_AVATAR_URL);

        //提交
        editor.commit();
    }

    //读取用户数据
    public Login getLogin(){
        Login login = new Login();
        SharedPreferences sp = this.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        login.UF_ACC = sp.getString("UF_ACC","");
        login.UF_AVATAR_URL = sp.getString("UF_AVATAR_URL","");
        login.UF_IS_CERT = sp.getString("UF_IS_CERT","");
        login.UF_PHONE = sp.getString("UF_PHONE","");

        return login;
    }

    //启动一个新的activity
    public void goToActivity(Class activity,Bundle bundle){
        Intent intent = new Intent(this,activity);
        if(bundle != null){
            intent.putExtra("data",bundle);
        }
        startActivity(intent);

    }

    //结束当前activity的显示
    public void closeCurrentActivity(){
        AppManager.getInstance().removeCurrent();
    }
}
