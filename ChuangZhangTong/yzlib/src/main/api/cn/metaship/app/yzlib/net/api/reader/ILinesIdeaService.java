package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface ILinesIdeaService {
    /**
    * 给划线部分添加感想
    */
    @POST("/v1/app/reader-linesidea")
    Observable<Response<LinesIdeaBean>> insertLinesIdeaRequestBean(@Body LinesIdeaRequestBean arg0 );
    /**
    * 查看某一段的所有感想内容，分页
    */
    @GET("/v1/app/reader-linesidea-content/paragraph")
    Observable<Response<List<LinesIdeaResultBean>>> findAllIdeaByParagraph(@Query("bookId") String bookId, @Query("chapterId") Integer chapterId, @Query("paragraphId") Integer paragraphId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 查看某条感想的详情
    */
    @GET("/v1/app/reader-linesidea/idea/{id}")
    Observable<Response<LinesIdeaResultBean>> findLinesIdeaResultBeanById(@Path("id") String id );
    /**
    * 查看某个人的感想(TA的感想)
    */
    @POST("/v1/app/reader-linesidea/userid")
    Observable<Response<List<LinesIdeaModel>>> findAllLinesIdeaModelsByUserId(@Body cn.metaship.app.yzlib.net.api.base.Query<LinesIdeaModel> arg0, @Query("page") int page, @Query("size") int size );
    /**
    * 根据划线部分的位置查出所有感想
    */
    @POST("/v1/app/reader-linesidea/paragraphs")
    Observable<Response<List<LinesIdeaBean>>> findIdeasByLinesId(@Body LinePositionIdeaBean arg0, @Query("bookId") String bookId, @Query("chapterId") int chapterId );
    /**
    * 查看该章节的所有段落的感想数量
    */
    @GET("/v1/app/reader-linesidea/paragraph")
    Observable<Response<List<ParagraphIdeaCountModel>>> findLinesIdeaResultBeansByParagraph(@Query("chapterId") Integer chapterId, @Query("bookId") String bookId );
    /**
    * 查看该书籍的所有感想，并分页
    */
    @POST("/v1/app/reader-linesidea/ideas")
    Observable<Response<List<LinesIdeaModel>>> findAll(@Body cn.metaship.app.yzlib.net.api.base.Query<LinesIdeaModel> arg0, @Query("page") int page, @Query("size") int size );
}