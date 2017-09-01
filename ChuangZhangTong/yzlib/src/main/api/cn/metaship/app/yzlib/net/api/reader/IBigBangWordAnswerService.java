package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.PATCH;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import cn.metaship.app.yzlib.net.api.base.AjaxResult;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IBigBangWordAnswerService {
    /**
    * 查看是否有权限回复回答条目
    */
    @GET("/v1/app/bigbangwordanswer-replypermission")
    Observable<Response<AjaxResult>> findPermission(@Query("id") String id, @Query("newTopicUserId") String newTopicUserId );
    /**
    * 新建回答条目
    */
    @POST("/v1/app/bigbangwordanswer")
    Observable<Response<BigBangWordAnswerBean>> insertBigBangWordAnswerBean(@Body BigBangWordAnswerBean arg0 );
    /**
    * 根据问题ID查找回答对象列表（包含回复数量）
    */
    @GET("/v1/app/bigbangwordanswers/{id}")
    Observable<Response<List<BigBangAnswerResultBean>>> queryAllByAskId(@Path("id") String id, @Query("page") int page, @Query("size") int size );
    /**
    * 按时间降序查找回答对象列表
    */
    @POST("/v1/app/bigbangwordanswer/order")
    Observable<Response<List<BigBangWordAnswerModel>>> queryByTime(@Body cn.metaship.app.yzlib.net.api.base.Query<BigBangWordAnswerModel> arg0, @Query("page") int page, @Query("size") int size );
    /**
    * 为回答点赞或点踩
    */
    @PATCH("/v1/app/bigbangwordanswer/patchcondition")
    Observable<Response<BigBangWordAnswerBean>> updateCondition(@Query("userId") String userId, @Query("condition") String condition, @Query("answerId") String answerId );
}