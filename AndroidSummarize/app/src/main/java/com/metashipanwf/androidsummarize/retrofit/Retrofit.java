package com.metashipanwf.androidsummarize.retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author:AnWanfei
 * time:2017/3/2.
 * Email:anwanfei_sp@163.com
 * function:
 */

public class Retrofit {
    private RequestServes service;

    /**
     * 获取Retrofit实例
     * @return
     */
    public static Retrofit getRetrofit(){
        return new Retrofit();
    }

    /**
     * 获取RequestServes实例
     * @return
     */
    public RequestServes getService(){
        return service;
    }

    private Retrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://www.tngou.net/api/food/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RequestServes.class);
    }


}
