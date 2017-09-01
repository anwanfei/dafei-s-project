package cn.metaship.app.yzlib.net.api.match;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface ISlideShowService {
    /**
    * 删除一条轮播图-大赛
    */
    @DELETE("/v1/slideshow/deleteone")
    void deleteSlideShow(@Query("id") String id );
    /**
    * 查找所有轮播图-大赛
    */
    @GET("/v1/slideshow/findall")
    Observable<Response<List<SlideShowBean>>> findAllSlideShow();
    /**
    * 修改轮播图的相关属性-大赛
    */
    @PATCH("/v1/slideshow/patchone")
    Observable<Response<SlideShowBean>> patchSlideShow(@Body SlideShowUpdateBean arg0 );
    /**
    * 插入一条轮播图-大赛
    */
    @POST("/v1/slideshow/insertone")
    Observable<Response<SlideShowBean>> insertOneSlideShow(@Body SlideShowBean arg0 );
    /**
    * 查找所有轮播图-抢票赛
    */
    @GET("/v1/slideshow-scramble/findall")
    Observable<Response<List<SlideShowBean>>> findAllSlideShowByScramble();
    /**
    * 插入一条轮播图-抢票赛
    */
    @POST("/v1/slideshow-scramble/insertone")
    Observable<Response<SlideShowBean>> insertOneScrambleSlideShow(@Body SlideShowBean arg0 );
}