package cn.metaship.app.yzlib.net.api.user;

import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import java.util.List;
import java.util.Map;

import cn.metaship.app.yzlib.net.api.base.AjaxResult;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.*;

public interface UserInterface {
    // 用例开始
    @GET("/v1/user/{id}")
    Observable<Result<UserBean>> getUser(@Path("id") Long id);

    @POST("/v1/user")
    Observable<Result<UserBean>> addUser(@Body UserBean userBean);

    @POST("/v1/user")
    Observable<Result<List<UserBean>>> queryUser(@Body cn.metaship.app.yzlib.net.api.base.Query<UserModel> query, @Query("page") Integer page, @Query("size") Integer size);

    @POST("/v1/user")
    Observable<Result<List<UserBean>>> queryUser0(@Body cn.metaship.app.yzlib.net.api.base.Query<UserModel> query, @QueryMap Map<String, String> params);

    @PUT("/v1/user")
    Observable<Result<UserBean>> modifyUser(@Body UserBean userBean);

    @DELETE("/v1/user/{id}")
    Observable<Result<AjaxResult>> deleteUser(@Path("id") Long id);

    @PATCH("/v1/user/{id}")
    Observable<Result<UserBean>> updateUser(@Path("id") Long id, @Query("name") String name);

    @POST("/v1/user/{id}/avatar/")
    Observable<Result<AjaxResult>> updateAvatar(@Path("id") Long id, @Body MultipartBody multipartBody);
    // 用例结束

    // @ApiOperation(value = "查询用户动态", notes = "查询用户动态", produces = "application/json")
    @GET(value = "/v1/userdynamic/query-dynamic")
    public Response<List<UserDynamicResultBean>> queryDynamic(@Query("userId") String userId,
                                                             @Query("page") Integer page,
                                                             @Query("size") Integer size);
}
