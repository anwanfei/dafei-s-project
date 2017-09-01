package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import cn.metaship.app.yzlib.net.api.base.AjaxResult;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface IBookCollectionService {
    /**
    * 删除书籍收藏
    */
    @DELETE("/v1/app/bookcollection")
    Observable<Response<AjaxResult>> deleteBookCollectionModels(@Body List<BookCollectionModel> arg0 );
    /**
    * 添加书籍收藏
    */
    @POST("/v1/app/bookcollection")
    Observable<Response<BookCollectionBean>> insertCollectionBean(@Body BookCollectionBean arg0 );
    /**
    * TA的书籍收藏
    */
    @GET("/v1/app/bookcollection/{id}")
    Observable<Response<List<BooksBean>>> queryByUserId(@Path("id") String id, @Query("page") int page, @Query("size") int size );
}