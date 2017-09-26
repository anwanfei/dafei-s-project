package com.junhangxintong.chuanzhangtong.utils;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.widget.Toast;

import com.junhangxintong.chuanzhangtong.common.MyApplication;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.builder.PostStringBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
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
                .addHeader(Constants.AGENT, getUserAgent())
                .url(baseUrl + url);
    }

    //添加两个参数的post请求，不带token
    public static PostFormBuilder postWithNoHeader(Context context, String url) {
        String baseUrl = CacheUtils.getString(context, Constants.BASE_URL);
        if (baseUrl.equals("")) {
            baseUrl = ConstantsUrls.WWW_TEST_BASE_URL;
        }
        return OkHttpUtils.post().addHeader(Constants.AGENT, getUserAgent()).url(baseUrl + url);
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
                .addHeader(Constants.AGENT, getUserAgent())
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(json)
                .url(baseUrl + url);
    }

    private static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(MyApplication.appContext);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void postWithTest(Context context, String url) {
        String baseUrl = CacheUtils.getString(context, Constants.BASE_URL);
        if (baseUrl.equals("")) {
            baseUrl = ConstantsUrls.WWW_TEST_BASE_URL;
        }
    }

    public abstract static class MyStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            Toast.makeText(MyApplication.appContext, Constants.NETWORK_RETURN_EMPT, Toast.LENGTH_SHORT).show();
        }
    }
}
