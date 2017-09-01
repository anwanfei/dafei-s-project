package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IRecommendBookService {
    @POST("/v1/recommendbooks/batch")
    Observable<Response<List<RecommendBooksModel>>> batchInsertRecommendBooks(@Body List<RecommendBooksBean> arg0 );
    @POST("/v1/recommendbooks")
    Observable<Response<RecommendBooksBean>> insertRecommendBook(@Body RecommendBooksBean arg0 );
    @POST("/v1/recommendbooks/query-jointime")
    Observable<Response<List<SimpleBookDetailsBean>>> findBooksByGradeAndDate(@Body cn.metaship.app.yzlib.net.api.base.Query<RecommendBooksModel> arg0, @Query("page") int page, @Query("size") int size );
}