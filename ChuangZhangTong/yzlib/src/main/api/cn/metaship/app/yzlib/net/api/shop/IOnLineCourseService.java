package cn.metaship.app.yzlib.net.api.shop;

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
public interface IOnLineCourseService {
    @POST("/v1/course/insert-sub-course")
    Observable<Response<SubCourseBean>> insertSubCourse(@Body SubCourseBean arg0 );
    @POST("/v1/course/insert-main-course")
    Observable<Response<MainCourseBean>> insertMainCourse(@Body MainCourseBean arg0 );
    /**
    * 购买在线课程
    */
    @POST("/v1/course/purchase")
    Observable<Response<CourseOrderBean>> purchaseCourse(@Body PurchaseOnLineCourseBean arg0 );
    /**
    * 在线课程购买订单支付
    */
    @POST("/v1/course/pay-order")
    Observable<Response<PurchasedCoursesBean>> payCourseOrder(@Body OrderPayBean arg0 );
    /**
    * 分页查询免费付费在线课程列表 
    */
    @GET("/v1/course/free-courses")
    Observable<Response<List<MainCourseBean>>> queryFreeStatusOnLineCourses(@Query("isFree") Integer isFree, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 查询在线课程详情接口
    */
    @GET("/v1/course/course-details")
    Observable<Response<List<SubCourseBean>>> queryOnLineCourseDetails(@Query("mainCourseId") String mainCourseId, @Query("userId") String userId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 分页查询最热在线课程列表
    */
    @GET("/v1/course/hot-courses")
    Observable<Response<List<MainCourseBean>>> queryHotOnLineCourses(@Query("page") Integer page, @Query("size") Integer size );
    @POST("/v1/course/calculate-hot-course")
    void calculateHotCourse();
    /**
    * 在线课程评论点赞和取消点赞
    */
    @POST("/v1/course/comment-like")
    void insertCourseCommentLike(@Body CourseCommentLikeInputBean arg0 );
    /**
    * 根据子课程id查询视频地址
    */
    @GET("/v1/course/course-url")
    Observable<Response<CourseVideoBean>> queryCourseUrlBySubCourseId(@Query("userId") String userId, @Query("subCourseId") String subCourseId );
    /**
    * 在线课程评论
    */
    @POST("/v1/course/insert-comment")
    void insertCourseComment(@Body CourseCommentInputBean arg0 );
    /**
    * 分页查询课程评论列表
    */
    @GET("/v1/course/query-comments")
    Observable<Response<List<CourseCommentResultBean>>> queryCourseComments(@Query("userId") String userId, @Query("mainCourseId") String mainCourseId, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 分页查询最新在线课程列表
    */
    @GET("/v1/course/new-courses")
    Observable<Response<List<MainCourseBean>>> queryNewOnLineCourses(@Query("page") Integer page, @Query("size") Integer size );
    /**
    * 查询购买到的课程列表
    */
    @GET("/v1/course/purchased-courses")
    Observable<Response<List<MainCourseBean>>> queryPurchasedCourses(@Query("userId") String userId, @Query("page") Integer page, @Query("size") Integer size );
}