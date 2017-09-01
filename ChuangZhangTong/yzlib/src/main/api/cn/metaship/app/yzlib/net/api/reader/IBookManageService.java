package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.PATCH;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IBookManageService {
    /**
    * 添加书评
    */
    @POST("/v1/app/bookcomment")
    Observable<Response<BookCommentBean>> insert(@Body BookCommentBean arg0 );
    /**
    * 根据书评发布的时间对书评进行排序、分页
    */
    @POST("/v1/app/bookcomment-query")
    Observable<Response<List<BookCommentModel>>> queryAllByPage(@Body cn.metaship.app.yzlib.net.api.base.Query<BookCommentModel> arg0, @Query("page") int page, @Query("size") int size );
    /**
    * 根据书的id查询所有章节
    */
    @GET("/v1/app/bookmanage/{id}")
    Observable<Response<List<BookChapterBean>>> queryAll(@Path("id") String id );
    /**
    * 分页搜索书籍
    */
    @GET("/v1/app/bookmanage-search")
    Observable<Response<List<SearchBookBean>>> queryByBookNameAndPage(@Query("fuzzyBookName") String fuzzyBookName, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 根据书的id查找当前书的所有被词汇的领养次数、提问次数
    */
    @POST("/v1/app/bookmanage/adoptword")
    Observable<Response<List<BigBangWordModel>>> queryWordsByBookId(@Body cn.metaship.app.yzlib.net.api.base.Query<BigBangWordModel> arg0, @Query("page") int page, @Query("size") int size );
    /**
    * 根据书名模糊搜索书籍
    */
    @GET("/v1/app/bookmanage")
    Observable<Response<List<BooksBean>>> queryByBookName(@Query("fuzzyBookName") String fuzzyBookName );
    /**
    * 更新书籍热度-PathVariable
    */
    @PATCH("/v1/app/bookmanage/{id}")
    Observable<Response<BooksBean>> patchBookReadHot(@Path("id") String id, @Query("userId") String userId );
    /**
    * 根据书的类型、书的排序规则进行排序
    */
    @POST("/v1/app/bookmanage")
    Observable<Response<List<KnowledgePageBean>>> queryBooksBy(@Body cn.metaship.app.yzlib.net.api.base.Query<BooksModel> arg0, @Query("page") int page, @Query("size") int size );
    /**
    * 根据书的id查询书的详情
    */
    @GET("/v1/app/bookmanage-book/{id}")
    Observable<Response<BooksBean>> queryOne(@Path("id") String id );
    /**
    * 分页查询指定书籍词汇库
    */
    @GET("/v1/app/wordstorage-book-userid")
    Observable<Response<List<WordStoreResultBean>>> findWordByUserId(@Query("userId") String userId, @Query("bookId") String bookId, @Query("feature") Integer feature, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 对书评进行点赞或点踩
    */
    @PATCH("/v1/app/bookcomment/{id}")
    Observable<Response<BookCommentBean>> patchCondition(@Path("id") String id, @Query("userId") String userId, @Query("condition") String condition );
}