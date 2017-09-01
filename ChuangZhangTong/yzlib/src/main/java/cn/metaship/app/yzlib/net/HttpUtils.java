package cn.metaship.app.yzlib.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.metaship.wenjiang.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by live106 on 2017/5/31 9:50
 */

public class HttpUtils {

    private final static HttpUtils instance = new HttpUtils();

    private String userToken = "";

    private HttpUtils() {

    }

    public static HttpUtils getDefault() {
        return instance;
    }

    private String getUserToken() {
        return userToken;
    }

    public void setUserToken(String token) {
        this.userToken = token;
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
                    builder.addHeader(Constants.TOKEN, getDefault().getUserToken());
                    return chain.proceed(builder.build());
                }
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            // FIXME 后面根据性能决定是否改用Jackson,解析json效率会高些，更换需要处理一下包冲突问题
//                .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build();

    public <T> T getService(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
