package cn.metaship.app.yzlib.net.api.match;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface IExpertAssignEntryService {
    @GET("/v1/expertassignentry/query")
    Observable<Response<List<EntryBean>>> queryBeansByExpertIdAndEntryInfo(@Query("expertUserId") String expertUserId, @Query("fuzzyInfo") String fuzzyInfo, @Query("page") Integer page, @Query("size") Integer size );
}