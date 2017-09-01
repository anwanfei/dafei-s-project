package cn.metaship.app.yzlib.net.api.user;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface IPersonalCenterService {
    /**
    * 其他人查看用户的信息
    */
    @GET("/v1/personalcenter/check-other-user-info")
    Observable<Response<SimpleOtherUserInfoBean>> othersQueryUserInfo(@Query("beCheckUserId") String beCheckUserId, @Query("checkUserId") String checkUserId );
    /**
    * 查看自己的用户信息
    */
    @GET("/v1/personalcenter/{userId}")
    Observable<Response<SimpleUserInfoBean>> queryByUserId(@Path("userId") String userId );
}