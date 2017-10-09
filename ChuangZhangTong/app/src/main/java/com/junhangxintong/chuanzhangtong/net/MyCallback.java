package com.junhangxintong.chuanzhangtong.net;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.common.MyApplication;
import com.junhangxintong.chuanzhangtong.mine.activity.LoginRegisterActivity;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.junhangxintong.chuanzhangtong.utils.CacheUtils.SHAREPRENFERENCE_NAME;

/**
 * Created by anwanfei on 2017/9/27.
 */

public abstract class MyCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        int code = response.raw().code();
        String message = response.raw().message();
        if (code == 200) {
            onSuccess(response);
        } else if (code == 601) {
            //清除了sp存储
            MyApplication.appContext.getSharedPreferences(SHAREPRENFERENCE_NAME, Context.MODE_PRIVATE).edit().clear().commit();
            //保存获取权限的sp
            CacheUtils.putBoolean(MyApplication.appContext, Constants.IS_NEED_CHECK_PERMISSION, false);
            MyApplication.appContext.startActivity(new Intent(MyApplication.appContext, LoginRegisterActivity.class));
        } else if (code == 404) {
            onDataEmpty(message);
        } else {
            Toast.makeText(MyApplication.appContext, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        /*if (t instanceof RuntimeException) {

        } else if (t instanceof SocketTimeoutException) {

        } else if (t instanceof ConnectException) {

        }*/
        Toast.makeText(MyApplication.appContext, Constants.NETWORK_CONNECTION_ERROR, Toast.LENGTH_SHORT).show();
    }

    public abstract void onSuccess(Response<T> response);

    public abstract void onDataEmpty(String message);


}
