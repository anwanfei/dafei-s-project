package com.metashipanwf.androidsummarize.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author:AnWanfei
 * time:2017/3/2.
 * Email:anwanfei_sp@163.com
 * function:
 */

public interface RequestServes {
    @GET("show")
    Call<Bean> getMenuById(@Query("id") String id);
}
