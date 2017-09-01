package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IBookProgressService {
    /**
    * 保存阅读进度
    */
    @POST("/v1/bookprogress/insert")
    Observable<Response<BookProgressBean>> insertBookProgress(@Body BookProgressBean arg0 );
    /**
    * 查询指定书架上指定书籍的阅读进度
    */
    @GET("/v1/bookprogress/find-book-progress")
    Observable<Response<BookProgressBean>> queryBookProgress(@Query("bookId") String bookId, @Query("shelfId") String shelfId );
    /**
    * 查询某人书架的所有书籍进度
    */
    @GET("/v1/bookprogress/find-books-shelf")
    Observable<Response<List<BookProgressBean>>> queryBooksByShelfId(@Query("shelfId") String shelfId );
}