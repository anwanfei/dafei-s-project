package com.junhangxintong.chuanzhangtong.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.junhangxintong.chuanzhangtong.common.MyApplication;
import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.junhangxintong.chuanzhangtong.utils.NetUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.junhangxintong.chuanzhangtong.utils.Constants.TOKEN;

/**
 * Created by anwanfei on 2017/9/26.
 */

public class HttpUtils {
    private final static HttpUtils instance = new HttpUtils();

    private HttpUtils() {
    }

    public static HttpUtils getDefault() {
        return instance;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader(Constants.TOKEN, CacheUtils.getString(MyApplication.appContext, TOKEN))
                            .addHeader(Constants.USER_ID, CacheUtils.getString(MyApplication.appContext, Constants.ID))
                            .addHeader(Constants.AGENT, NetUtils.getUserAgent());
                    return chain.proceed(builder.build());
                }
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private static final retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
            .baseUrl("http://116.62.152.191:8082/")
            .client(httpClient)//为了添加头
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public <T> T getService(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
