package com.junhangxintong.chuanzhangtong.net;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edz on 2017/7/19.
 */

public class Retrofit {

    private RequestService service;

    public RequestService getService() {
        return service;
    }

    public static Retrofit getRetrofit() {
        return new Retrofit();
    }

    private Retrofit() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://www.tngou.net/api/food/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(RequestService.class);
    }
}
