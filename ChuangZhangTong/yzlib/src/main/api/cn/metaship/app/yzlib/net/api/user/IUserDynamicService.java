package cn.metaship.app.yzlib.net.api.user;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IUserDynamicService {
    /**
    * 查看好友动态
    */
    @GET("/v1/userdynamic/query-friends-dynamic")
    Observable<Response<List<PersonalDynamicBean>>> queryFriendsDynamic(@Query("page") Integer page, @Query("size") Integer size, @Query("userId") String userId );
    /**
    * 穿越查询
    */
    @GET("/v1/userdynamic/query-across-dynamic")
    Observable<Response<List<UserDynamicResultBean>>> queryAcrossDynamic(@Query("userId") String userId, @Query("size") Integer size );
    @POST("/v1/userdynamic/test")
    void activeHotTask();
    @GET("/v1/userdynamic/query-hot-dynamic")
    Observable<Response<List<UserDynamicResultBean>>> queryHotDynamic(@Query("userId") String userId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 对用户动态进行转发
    */
    @POST("/v1/userdynamic/forward/add")
    Observable<Response<String>> insertForward(@Body UserDynamicForwardBean arg0 );
    /**
    * 查看自己的个人动态
    */
    @GET("/v1/userdynamic/query-self-dynamic")
    Observable<Response<List<PersonalDynamicBean>>> querySelfDynamic(@Query("page") Integer page, @Query("size") Integer size, @Query("userId") String userId );
    /**
    * 查询用户最新动态
    */
    @GET("/v1/userdynamic/query-dynamic")
    Observable<Response<List<UserDynamicResultBean>>> queryDynamic(@Query("userId") String userId, @Query("page") Integer page, @Query("size") Integer size );
}