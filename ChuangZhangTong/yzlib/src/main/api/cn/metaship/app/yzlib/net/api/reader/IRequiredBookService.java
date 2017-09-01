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
public interface IRequiredBookService {
    @POST("/v1/requiredbooks/query-jointime")
    Observable<Response<List<SimpleBookDetailsBean>>> findBooksByGradeAndDate(@Body cn.metaship.app.yzlib.net.api.base.Query<RequiredBooksModel> arg0, @Query("page") int page, @Query("size") int size );
    @POST("/v1/requiredbooks")
    Observable<Response<RequiredBooksBean>> insertOneBook(@Body RequiredBooksBean arg0 );
    @POST("/v1/requiredbooks/batch-insert")
    Observable<Response<List<RequiredBooksModel>>> batchInsertBooks(@Body List<RequiredBooksBean> arg0 );
}