package com.junhangxintong.chuanzhangtong.utils;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;

import okhttp3.MediaType;

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
        String userId = CacheUtils.getString(context, Constants.ID);
        String baseUrl = CacheUtils.getString(context, Constants.BASE_URL);
        if (baseUrl.equals("")) {
            baseUrl = ConstantsUrls.WWW_TEST_BASE_URL;
        }
        return OkHttpUtils
                .post()
                .addHeader(Constants.TOKEN, token)
                .addHeader(Constants.USER_ID, userId)
                .url(baseUrl + url);
    }

    //添加两个参数的post请求，不带token
    public static PostFormBuilder postWithNoHeader(Context context, String url) {
        String baseUrl = CacheUtils.getString(context, Constants.BASE_URL);
        if (baseUrl.equals("")) {
            baseUrl = ConstantsUrls.WWW_TEST_BASE_URL;
        }
        return OkHttpUtils.post().url(baseUrl + url);
    }

    //post方法，带header
    public static PostStringBuilder postStringWithHeader(Context context, String url, String json) {
        String token = CacheUtils.getString(context, TOKEN);
        String userId = CacheUtils.getString(context, Constants.ID);
        String baseUrl = CacheUtils.getString(context, Constants.BASE_URL);
        if (baseUrl.equals("")) {
            baseUrl = ConstantsUrls.WWW_TEST_BASE_URL;
        }
        return OkHttpUtils
                .postString()
                .addHeader(TOKEN, token)
                .addHeader(Constants.USER_ID, userId)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .url(baseUrl + url);
    }
}
