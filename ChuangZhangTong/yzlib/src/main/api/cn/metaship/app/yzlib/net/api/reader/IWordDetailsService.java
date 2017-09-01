package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IWordDetailsService {
    @POST("/v1/app/wordstatistics")
    Observable<Response<WordStatisticsResultBean>> queryLeaderBoard(@Body cn.metaship.app.yzlib.net.api.base.Query<WordStatisticsModel> arg0, @Query("page") int page, @Query("size") int size, @Query("wordContent") String wordContent );
}