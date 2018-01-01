package com.junhangxintong.chuanzhangtong.common;

import android.app.Application;
import android.content.Context;

import com.junhangxintong.chuanzhangtong.utils.CacheUtils;
import com.junhangxintong.chuanzhangtong.utils.Constants;
import com.mapbox.mapboxsdk.Mapbox;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by anwanfei on 2017/7/4.
 */

public class MyApplication extends Application {
    public static Context appContext;
    public static String token;
    public static String roleID;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        token = CacheUtils.getString(appContext, Constants.TOKEN);
        roleID = CacheUtils.getString(appContext, Constants.ROLEID);

        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        com.zhy.http.okhttp.OkHttpUtils.initClient(okHttpClient);

        //发生异常时候友盟统计
//        CrashHandler.getInstance().init(appContext);

        //mapbox的accessToken
        Mapbox.getInstance(this, Constants.MAPBOX_ACCESS_TOKEN);
    }

    {
        PlatformConfig.setWeixin("wxec631c98fa93466a", "bc54e3d3ed79e7635a407eb5ad146636");
        PlatformConfig.setQQZone("1106363362", "fUtQZDMZqWkrAaJh");
        PlatformConfig.setSinaWeibo("1674896418", "fa81de6ece18c2c79aa9d57f3ddecfce", "http://sns.whalecloud.com");
    }
}
