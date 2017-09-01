package com.junhangxintong.chuanzhangtong.utils;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import static com.junhangxintong.chuanzhangtong.utils.Constants.TOKEN;

/**
 * Created by anwanfei on 2017/8/31.
 */

public class NetUtils {

    /**
     * okhttp3的post请求方法，带有添加的参数、
     *
     * @param url
     * @return
     */
    //添加两个参数的post请求,带有token
    public static PostFormBuilder postWithHeader(Context context, String url) {
        String token = CacheUtils.getString(context, TOKEN);
        return OkHttpUtils
                .post()
                .addHeader(TOKEN, token)
                .url(url);
    }

    //添加两个参数的post请求，不带token
    public static PostFormBuilder postWithNoHeader(Context context, String url) {
        return OkHttpUtils.post().url(url);
    }
}
