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
public interface IMatchInfoService {
    /**
    * 根据比赛的ID查询比赛赛区
    */
    @GET("/v1/match/query-zonemodels")
    Observable<Response<List<MatchZoneModel>>> queryMatchZoneModelsByMatchInfoId(@Query("matchId") String matchId );
    /**
    * 分页获取大赛专家列表
    */
    @GET("/v1/match/get-match-experts")
    Observable<Response<List<MatchExpertBean>>> getMatchExperts(@Query("matchId") String matchId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 根据比赛的ID查看公告
    */
    @GET("/v1/match/query-matchnotices-match/{matchId}")
    Observable<Response<List<MatchNoticeModel>>> queryByMatchId(@Path("matchId") String matchId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 大赛报名接口
    */
    @POST("/v1/match/join-match")
    Observable<Response<MatchStageModel>> joinMatch(@Body SimpleJoinMatchBean arg0 );
    /**
    * 测试类添加大赛相关信息接口
    */
    @POST("/v1/match/add-match-info")
    void addMatchInfo();
    /**
    * app端获取大赛各阶段信息列表,当前阶段会特殊标记
    */
    @GET("/v1/match/stage/get")
    Observable<Response<List<SimpleMatchStageBean>>> getStageList(@Query("matchId") String matchId, @Query("groupCode") String groupCode );
    /**
    * 分页获取大赛主办单位信息列表
    */
    @GET("/v1/match/get-match-companys")
    Observable<Response<List<MatchCompanyBean>>> getMatchCompanys(@Query("matchId") String matchId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 判断是否已经报名大赛
    */
    @POST("/v1/match/check-join-match")
    Observable<Response<Integer>> hasJoinMatch(@Query("matchId") String matchId, @Query("groupCode") String groupCode, @Query("userId") String userId );
    /**
    * pc版进入作文大赛后加载当前举办的大赛信息的接口
    */
    @GET("/v1/match/get-matchinfo")
    Observable<Response<SimpleMatchInfoBean>> getPcMatchInfo();
    /**
    * 获取大赛的比赛协议url地址
    */
    @GET("/v1/match/get-match-agreement")
    Observable<Response<MatchAgreementBean>> getMatchAgreement(@Query("matchId") String matchId, @Query("groupCode") String groupCode );
    /**
    * 根据比赛的ID查询比赛单元组
    */
    @GET("/v1/match/query-unitmodels")
    Observable<Response<List<UnitModel>>> queryUnitModelsByMatchInfoId(@Query("matchId") String matchId );
    /**
    * 插入大赛赛区信息
    */
    @POST("/v1/match/add-zonemodels")
    Observable<Response<List<MatchZoneModel>>> insertZoneModelByMatchInfoId(@Body MatchInsertZoneModelBean arg0 );
    /**
    * 批量添加大赛主办单位接口
    */
    @POST("/v1/match/company/add-company-list/{matchId}")
    void addMatchCompanyList(@Path("matchId") String matchId, @Body List<MatchCompanyBean> arg1 );
    /**
    * 根据大赛id、组别编码、大赛阶段获取作文命题描述
    */
    @GET("/v1/match/get-match-article-subject")
    Observable<Response<String>> getMatchArticleSubject(@Query("matchId") String matchId, @Query("groupCode") String groupCode, @Query("stageCode") String stageCode );
    /**
    * 根据比赛的ID查询比赛阶段列表
    */
    @GET("/v1/match/query-stages")
    Observable<Response<List<MatchStageModel>>> queryStageByMatchId(@Query("matchId") String matchId, @Query("groupCode") String groupCode );
    /**
    * 根据比赛的ID添加比赛公告
    */
    @POST("/v1/match/add-matchnotices-match")
    Observable<Response<SimpleMatchInfoBean>> insertMatchNotices(@Body List<MatchNoticeModel> arg0, @Query("matchId") String matchId );
    @POST("/v1/match/add-unitmodels")
    Observable<Response<List<UnitModel>>> insertUnitModelsByMatchInfoId(@Body MatchInsertUnitModelBean arg0 );
    /**
    * 批量添加大赛专家老师接口
    */
    @POST("/v1/match/expert/add-expert-list/{matchId}")
    void addMatchExpertList(@Path("matchId") String matchId, @Query("groupCode") String groupCode, @Body List<MatchExpertBean> arg2 );
    /**
    * 根据专家id获取专家详情
    */
    @GET("/v1/match/get-match-expert/{expertId}")
    Observable<Response<MatchExpertBean>> getMatchExpertById(@Path("expertId") String expertId );
}