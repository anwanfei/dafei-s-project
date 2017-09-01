package cn.metaship.app.yzlib.net.api.match;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IMatchInfoDisplayService {
    /**
    * 查询赛事信息对象列表
    */
    @POST("/v1/matchinfodisplay/query-page")
    Observable<Response<List<MatchInfoDisplayModel>>> queryAllByPage(@Body cn.metaship.app.yzlib.net.api.base.Query<MatchInfoDisplayModel> arg0, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 添加赛事信息对象
    */
    @POST("/v1/matchinfodisplay/add")
    Observable<Response<MatchInfoDisplayBean>> insertMatchInfoDisplay(@Body MatchInfoDisplayBean arg0 );
}