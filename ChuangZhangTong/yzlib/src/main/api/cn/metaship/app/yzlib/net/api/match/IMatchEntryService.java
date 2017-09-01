package cn.metaship.app.yzlib.net.api.match;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IMatchEntryService {
    /**
    * 根据作品id更新草稿箱的作品
    */
    @POST("/v1/match/entry/mod-entry-draft/{entryId}")
    Observable<Response<String>> modEntryToDraft(@Path("entryId") String entryId, @Body SimpleEntryBean arg1 );
    /**
    * 保存到作品库
    */
    @POST("/v1/match/entry/add-entry-lib")
    Observable<Response<String>> addEntryToLib(@Body SimpleEntryBean arg0 );
    /**
    * 保存到作品库
    */
    @POST("/v1/match/entry/add-entry-lib/{entryId}")
    void addEntryToLib(@Path("entryId") String entryId );
    /**
    * 保存到草稿箱
    */
    @POST("/v1/match/entry/add-entry-draft")
    Observable<Response<String>> addEntryToDraft(@Body SimpleEntryBean arg0 );
    /**
    * 提交到作品库并参赛
    */
    @POST("/v1/match/entry/submit-entry")
    Observable<Response<String>> submitEntry(@Body SimpleEntryBean arg0 );
    /**
    * 从草稿箱或作品库中提交作品参加大赛
    */
    @POST("/v1/match/entry/submit-entry/{entryId}")
    Observable<Response<String>> submitEntry(@Path("entryId") String entryId );
    /**
    * 上传作品图片
    */
    @POST("/v1/match/entry/upload-pic")
    Observable<Response<String>> uploadPic(@Body okhttp3.MultipartBody pic );
    /**
    * 对大赛作品进行投票
    */
    @POST("/v1/match/entry/post-vote")
    void doEntryVote(@Body SimpleVoteLogBean arg0 );
    /**
    * 获取作品排行榜
    */
    @GET("/v1/match/entry/get-rankinglist-top/{matchId}")
    Observable<Response<List<EntryBean>>> getRankingList(@Path("matchId") String matchId, @Query("zoneCode") String zoneCode, @Query("stageCode") String stageCode, @Query("groupCode") String groupCode, @Query("unitCode") String unitCode );
    /**
    * 获取作品排行榜
    */
    @GET("/v1/match/entry/get-rankinglist/{matchId}")
    Observable<Response<List<EntryBean>>> getRankingList(@Path("matchId") String matchId, @Query("page") Integer page, @Query("size") Integer size, @Query("zoneCode") String zoneCode, @Query("stageCode") String stageCode, @Query("groupCode") String groupCode, @Query("unitCode") String unitCode );
    /**
    * 对大赛作品进行点赞
    */
    @POST("/v1/match/entry/post-like")
    void doEntryLiked(@Body SimpleLikedLogBean arg0 );
    /**
    * 对大赛作品进行举报
    */
    @POST("/v1/match/entry/post-report")
    void doEntryReport(@Body SimpleReportLogBean arg0 );
    /**
    * 对大赛作品进行浏览
    */
    @POST("/v1/match/entry/post-browser")
    void doEntryBrowser(@Body SimpleBrowserLogBean arg0 );
    /**
    * 查询作品详情
    */
    @GET("/v1/match/entry/get-entry-info/{entryId}")
    Observable<Response<EntryBean>> getEntryById(@Path("entryId") String entryId );
    /**
    * 查询我的作品库作品
    */
    @GET("/v1/match/entry/get-user-entry-list/{userId}")
    Observable<Response<List<EntryBean>>> getEntryByUserId(@Path("userId") String userId, @Query("unitCode") String unitCode, @Query("isJoin") Integer isJoin, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 对大赛作品进行分享
    */
    @POST("/v1/match/entry/post-share")
    void doEntryShare(@Body SimpleShareLogBean arg0 );
    /**
    * 对大赛作品进行踩
    */
    @POST("/v1/match/entry/post-notlike")
    void doEntryNotliked(@Body SimpleNotlikedLogBean arg0 );
    /**
    * 查询对作品（或者评论）是否点赞
    */
    @GET("/v1/match/entry/get-entry-like")
    Observable<Response<LikedLogBean>> findLikeLog(@Query("userId") String userId, @Query("entryId") String entryId, @Query("commentId") String commentId );
    /**
    * 从作品库中选择作品参赛
    */
    @POST("/v1/match/entry/choose-lib-entry")
    Observable<Response<EntryModel>> chooseLibEntryJoinMatch(@Body LibEntryJoinMatchBean arg0 );
    /**
    * 添加作品点评
    */
    @POST("/v1/match/entry/add-expert-comment")
    void addExpertEntryComment(@Body ExpertCommentBean arg0 );
    /**
    * 查询我的草稿箱作品
    */
    @GET("/v1/match/entry/get-draft-entry-list/{userId}")
    Observable<Response<List<EntryBean>>> getDraftEntryListByUserId(@Path("userId") String userId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 根据大赛查询作品列表，根据大赛时间倒序排序
    */
    @GET("/v1/match/entry/get-entry-list/{matchId}")
    Observable<Response<List<EntryBean>>> getEntryByMatchId(@Path("matchId") String matchId, @Query("unitCode") String unitCode, @Query("groupCode") String groupCode, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 查询专家作品点评列表
    */
    @GET("/v1/match/entry/get-expertcomment-list/{expertId}")
    Observable<Response<List<ExpertCommentEntryBean>>> getEntryByExpertId(@Path("expertId") String expertId, @Query("state") Integer state, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 查询专家作品点评明细
    */
    @GET("/v1/match/entry/get-expertcomment-detail/{entryId}")
    Observable<Response<ExpertCommentEntryBean>> getEntryCommentDetail(@Path("entryId") String entryId );
}