package com.junhangxintong.chuanzhangtong.net;

import com.junhangxintong.chuanzhangtong.mine.bean.CrewServeBean;
import com.junhangxintong.chuanzhangtong.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by anwanfei on 2017/9/26.
 */

public interface ICrewManagementService {
    /**
     * 船员列表展示
     */
    @FormUrlEncoded
    @POST("my/ship/employeeManger/query/list.do")
    Call<CrewServeBean> getCrewList(@Field(Constants.PAGE) String page, @Field(Constants.PAGE_SIZE) String pageSize, @Field(Constants.USER_ID) String userId, @Field(Constants.PERSON_NAME) String personName);
}
