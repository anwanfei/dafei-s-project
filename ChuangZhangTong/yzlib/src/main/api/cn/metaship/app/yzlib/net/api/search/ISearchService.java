package cn.metaship.app.yzlib.net.api.search;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ISearchService {
    /**
    * 根据关键字搜索图书、课程、作文、用户信息
    */
    @GET("/v1/search/get-search-info")
    Observable<Response<List<SearchBean>>> getSearchInfo(@Query("keyword") String keyword, @Query("page") Integer page, @Query("size") Integer size );
}