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
import cn.metaship.app.yzlib.net.api.base.AjaxResult;
import retrofit2.http.Body;
public interface IUserJoinScrambleMatchLogService {
    /**
    * 查询抢票赛动态的评论或者评论的回复
    */
    @GET("/v1/match/scramble/query-comments")
    Observable<Response<List<UserJoinScrambleMatchCommentLogBean>>> findAllComments(@Query("scrambleId") String scrambleId, @Query("beCommentId") String beCommentId, @Query("page") Integer page, @Query("size") Integer size, @Query("userId") String userId );
    /**
    * 抢票赛抽奖接口
    */
    @POST("/v1/match/scramble/lottery")
    Observable<Response<AjaxResult>> lottery(@Body LotteryScrambleBean arg0 );
    /**
    * 根据用户id、抢票赛命题id查询用户参与抢票赛记录表
    */
    @GET("/v1/match/scramble/find-scramble-record")
    Observable<Response<UserJoinScrambleMatchLogBean>> findByUserIdAndPropositionId(@Query("userId") String userId, @Query("propositionId") String propositionId );
    /**
    * 添加抢票赛今日命题内容
    */
    @POST("/v1/match/scramble/insert")
    Observable<Response<MatchVoteTodayPropositionBean>> insertMatchVoteTodayPropositon(@Body MatchVoteTodayPropositionBean arg0 );
    /**
    * 抢票赛填词作品的点赞和取消点赞，1-点赞，2-点踩
    */
    @POST("/v1/match/scramble/add-like")
    Observable<Response<PropositionLikeLogBean>> addLikeProposition(@Body PropositionLikeLogBean arg0 );
    /**
    * 查询抢票赛今日命题
    */
    @GET("/v1/match/scramble/query")
    Observable<Response<MatchVoteTodayPropositionBean>> queryMatchVoteTodayPropositon();
    /**
    * 对抢票赛填词作品进行评论
    */
    @POST("/v1/match/scramble/comment")
    Observable<Response<UserJoinScrambleMatchCommentLogBean>> insertOneScrambleComment(@Body UserJoinScrambleMatchCommentLogBean arg0 );
    /**
    * 对抢票赛填词作品进行转发
    */
    @POST("/v1/match/scramble/forward/add")
    Observable<Response<String>> insertScrambleForward(@Body ScramForwardParamBean arg0 );
    /**
    * 对抢票赛填词作品的转发进行转发
    */
    @POST("/v1/match/scramble/forward/forward/add")
    Observable<Response<String>> insertScrambleForward(@Body ScramSecondForwardParamBean arg0 );
    /**
    * 发布抢票赛填词
    */
    @POST("/v1/match/scramble/publish-proposition")
    Observable<Response<UserJoinScrambleMatchLogBean>> publishProposition(@Body JoinScrambleMatchBean arg0 );
    /**
    * 添加抢票赛填词作品的转发的评论及评论的回复
    */
    @POST("/v1/match/scramble/forward/comment")
    Observable<Response<ScrambleForwardCommentLogBean>> insertScrambleForwardComment(@Body ScrambleForwardCommentLogBean arg0 );
    /**
    * 分页查询抢票赛转发记录评论列表
    */
    @GET("/v1/match/scramble/forward/query-comments")
    Observable<Response<List<ScrambleForwardCommentLogBean>>> queryScrambleForwardComments(@Query("scrambleForwardId") String scrambleForwardId, @Query("beCommentId") String beCommentId, @Query("page") Integer page, @Query("size") Integer size, @Query("userId") String userId );
    /**
    * 抢票赛转发记录的点赞或取消点赞
    */
    @POST("/v1/match/scramble/forward/insert-like")
    Observable<Response<ScrambleForwardLikeLogBean>> insertScrambleForwardLikeLog(@Body ScrambleForwardLikeLogBean arg0 );
    /**
    * 查询用户抢票赛的评论的点赞状态
    */
    @GET("/v1/match/scramble/query-comment-like")
    Observable<Response<UserJoinScrambleMatchCommentLikeLogBean>> queryCommentLikeLog(@Query("userId") String userId, @Query("commentId") String commentId, @Query("scrambleId") String scrambleId );
    /**
    * 查询抢票赛转发的详情
    */
    @GET("/v1/match/scramble/forward/detail")
    Observable<Response<ScrambleForwardBean>> queryScrambleForwardDetail(@Query("scrambleForwardId") String scrambleForwardId );
    /**
    * 对抢票赛的评论的点赞或取消点赞
    */
    @POST("/v1/match/scramble/insert-comment-like")
    void insertScrambleCommentLike(@Body UserJoinScrambleMatchCommentLikeLogBean arg0 );
    /**
    * 查询抢票赛的剩余抽奖次数
    */
    @GET("/v1/match/scramble/remain-lottery-count")
    Observable<Response<Long>> queryRemainLotteryCount(@Query("userId") String userId, @Query("propositionId") String propositionId );
    /**
    * 查询用户参与某命题的详情
    */
    @GET("/v1/match/scramble/proposition-detail")
    Observable<Response<UserJoinScrambleMatchDetail>> findPropositionDetails(@Query("userId") String userId, @Query("propositionId") String propositionId );
    /**
    * 请求获取抢票赛比赛规则的url
    */
    @GET("/v1/matchscrambleticket/query-url")
    void queryMatchRuleUrl();
    @POST("/v1/match/scramble/scan-like-count")
    Observable<Response<List<UserJoinScrambleMatchLogBean>>> findScanBeanByLikeCount();
    /**
    * 查询抢票赛的所有命题内容
    */
    @GET("/v1/match/scramble/find-propositions")
    Observable<Response<List<MatchVoteTodayPropositionBean>>> queryAllPropositions(@Query("userId") String userId );
    /**
    * 抢票赛转发记录的评论的点赞或取消点赞
    */
    @POST("/v1/match/scramble/forward/comment-like")
    Observable<Response<ScrambleForwardCommentLikeLogBean>> insertScrambleForwardCommentLikeLog(@Body ScrambleForwardCommentLikeLogBean arg0 );
    /**
    * 查询抢票赛转发记录的评论的点赞
    */
    @GET("/v1/match/scramble/forward/query-comment-like")
    Observable<Response<ScrambleForwardCommentLikeLogBean>> queryScrambleForwardCommentLikeLog(@Query("userId") String userId, @Query("commentId") String commentId, @Query("scrambleForwardId") String scrambleForwardId );
    /**
    * 根据ID查询抢票赛今日命题
    */
    @GET("/v1/match/scramble/query-by-id")
    Observable<Response<MatchVoteTodayPropositionBean>> queryMatchVoteTodayPropositionById(@Query("id") String id );
}