package com.junhangxintong.chuanzhangtong.common;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
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
    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

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
    }

    {
        PlatformConfig.setWeixin("wxec631c98fa93466a", "bc54e3d3ed79e7635a407eb5ad146636");
        PlatformConfig.setQQZone("1106363362", "fUtQZDMZqWkrAaJh");
        PlatformConfig.setSinaWeibo("1674896418", "fa81de6ece18c2c79aa9d57f3ddecfce", "http://sns.whalecloud.com");
    }
}
