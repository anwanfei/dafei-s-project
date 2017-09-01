package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.PUT;
import java.util.List;
import retrofit2.http.PATCH;
import retrofit2.http.GET;
import java.util.Map;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IBookShelfService {
    /**
    * 根据用户ID添加书架
    */
    @POST("/v1/app/bookshelf")
    Observable<Response<BookShelfBean>> insertOne(@Query("userId") String userId );
    /**
    * 根据用户ID、书籍分类查找书籍
    */
    @GET("/v1/app/bookshelf-books-category/{id}")
    Observable<Response<Map<String,BooksBean>>> querybyCategoryId(@Path("id") String id, @Query("categoryId") String categoryId );
    /**
    * 根据用户ID向书架中添加书籍
    */
    @PUT("/v1/app/bookshelf-add/{userId}")
    Observable<Response<BookShelfBean>> addBooks(@Path("userId") String userId, @Body List<String> arg1 );
    /**
    * 根据用户ID、所要删除的书籍列表查询书架
    */
    @PATCH("/v1/app/bookshelf-delete/{userId}")
    Observable<Response<BookShelfBean>> deleteBooks(@Path("userId") String userId, @Body List<String> arg1 );
    /**
    * 根据用户ID查询书架上的所有书
    */
    @GET("/v1/app/bookshelf-books/{id}")
    Observable<Response<List<BooksBean>>> queryAllBooks(@Path("id") String id );
    /**
    * 根据用户ID查询书架
    */
    @GET("/v1/app/bookshelf/{userId}")
    Observable<Response<BookShelfBean>> findOneBook(@Path("userId") String userId );
}