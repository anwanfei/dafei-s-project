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
import retrofit2.http.POST;
import retrofit2.http.Body;
import cn.metaship.app.yzlib.net.api.base.AjaxResult;
public interface IBigBangTopicConditionService {
    /**
    * 根据回复发布时间进行倒序排序查找，按照回答的ID
    */
    @POST("/v1/app/bigbangtopicreply/order")
    Observable<Response<List<BigBangTopicReplyModel>>> queryAllByTime(@Body cn.metaship.app.yzlib.net.api.base.Query<BigBangTopicReplyModel> arg0, @Query("page") int page, @Query("size") int size );
    /**
    * 按照时间将主题回复进行排序、分页
    */
    @POST("/v1/app/bigbangtopicreply/order-answerid")
    Observable<Response<List<BigBangWordReplyResultBean>>> queryAll(@Body cn.metaship.app.yzlib.net.api.base.Query<BigBangTopicReplyModel> arg0, @Query("page") int page, @Query("size") int size );
    /**
    * 添加一条回复
    */
    @POST("/v1/app/bigbangtopicreply")
    Observable<Response<BigBangTopicReplyBean>> insertOneReply(@Body BigBangTopicReplyBean arg0 );
    /**
    * 根据回复的主题id、当前回复的用户id查询是否有回复的权限
    */
    @GET("/v1/app/bigbangtopicreply-replypermission")
    Observable<Response<AjaxResult>> findPermission(@Query("id") String id, @Query("newTopicUserId") String newTopicUserId );
    /**
    * 某人为某个主题点赞或者点踩
    */
    @PATCH("/v1/app/bigbangtopicreply/{id}")
    Observable<Response<BigBangTopicReplyBean>> patchLikeCondition(@Path("id") String id, @Query("userId") String userId, @Query("condition") String condition );
}