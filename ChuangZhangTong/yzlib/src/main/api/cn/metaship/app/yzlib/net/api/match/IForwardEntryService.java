package cn.metaship.app.yzlib.net.api.match;

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
public interface IForwardEntryService {
    /**
    *   分页查询作品转发记录的评论列表
    */
    @GET("/v1/forwardentry/find-forward-comment")
    Observable<Response<List<ForwardEntryCommentResultBean>>> queryComment(@Query("forwardEntryId") String forwardEntryId, @Query("userId") String userId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 对转发评论进行点赞或取消点赞
    */
    @POST("/v1/forwardentry/insert-comment-like")
    void insertCommentLikeLog(@Body EntryForwardCommentLikeLogBean arg0 );
    /**
    * 对转发评论进行回复
    */
    @POST("/v1/forwardentry/add-comment-reply")
    Observable<Response<ForwardEntryCommentModel>> insertForwardEntryCommentReply(@Body SimpleForwardEntryCommentBean arg0 );
    /**
    * 对转发进行点赞/取消点赞操作  1-点赞，2-取消点赞
    */
    @POST("/v1/forwardentry/add-like")
    Observable<Response<ForwardEntryLikeBean>> insertForwardEntryLikeBean(@Body ForwardEntryLikeBean arg0 );
    /**
    * 新建一条转发记录
    */
    @POST("/v1/match/entrty/forward/add")
    Observable<Response<String>> insertForwardEntry(@Body EntryForwardParamBean arg0 );
    /**
    * 新建一条转发记录的转发
    */
    @POST("/v1/match/entry/forward/forward/add")
    Observable<Response<String>> insertForwardEntry(@Body EntrySecondForwardParamBean arg0 );
    /**
    * 对转发进行取消点赞操作
    */
    @POST("/v1/forwardentry/cancel-like")
    Observable<Response<ForwardEntryLikeBean>> cancelForwardEntryLike(@Body ForwardEntryCancelLikeBean arg0 );
    /**
    * 分页查询作品转发记录的评论的回复列表
    */
    @GET("/v1/forwardentry/find-forward-comment-reply")
    Observable<Response<List<ForwardEntryCommentReplyBean>>> queryCommentReply(@Query("forwardEntryId") String forwardEntryId, @Query("forwardEntryCommentId") String forwardEntryCommentId, @Query("userId") String userId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 查询用户是否对转发评论进行点赞
    */
    @GET("/v1/forwardentry/query-comment-like")
    Observable<Response<EntryForwardCommentLikeLogBean>> queryEntryForwardCommentLike(@Query("userId") String userId, @Query("commentId") String commentId, @Query("forwardEntryId") String forwardEntryId );
    /**
    * 对转发进行评论操作
    */
    @POST("/v1/forwardentry/add-comment")
    Observable<Response<ForwardEntryCommentModel>> insertForwardEntryCommentBean(@Body SimpleForwardEntryCommentBean arg0 );
    /**
    *  查询作品转发记录详情
    */
    @GET("/v1/forwardentry/find-forward-details")
    Observable<Response<EntryForwardDetailsBean>> queryOneEntryForwardDetailsBean(@Query("forwardId") String forwardId );
}