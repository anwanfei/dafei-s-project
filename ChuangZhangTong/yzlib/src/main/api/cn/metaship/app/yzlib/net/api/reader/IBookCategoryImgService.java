package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IBookCategoryImgService {
    /**
    * 查询所有分类封面数据
    */
    @GET("/v1/app/bookcategoryimg")
    Observable<Response<List<BookCategoryImgBean>>> findAllBookCategoryImg();
    /**
    * 插入分类封面数据
    */
    @POST("/v1/app/bookcategoryimg")
    Observable<Response<BookCategoryImgBean>> insertBookCategoryImgModel(@Body BookCategoryImgBean arg0 );
}