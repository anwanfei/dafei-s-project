package cn.metaship.app.yzlib.net.api.match;

import com.jakewharton.retrofit2.adapter.rxjava2.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by live106 on 2017/5/31 17:04
 */

public interface IMatchService {
    @GET(value = "/v1/match/get-matchinfo")
    Observable<Response<SimpleMatchInfoBean>> getMatchInfo();

    @GET(value = "/v1/match/get-match-companys")
    Observable<Response<List<MatchCompanyBean>>> getMatchCompanies(
            @Query("matchId") String matchId,
            @Query("page") Integer page,
            @Query("size") Integer size);

    @GET(value = "/v1/match/flowchart/get")
    Observable<Result<List<String>>> getMatchProgressState(
            @Query("matchId") String matchId,
            @Query("groupCode") String groupCode);
}
