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
import cn.metaship.app.yzlib.net.api.base.AjaxResult;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface ILinesContentService {
    @DELETE("/v1/app/reader-linescontent/{id}")
    Observable<Response<AjaxResult>> delete(@Path("id") String id );
    @POST("/v1/app/reader-linescontent/batch")
    Observable<Response<AjaxResult>> batchAddLinesContentBeans(@Body List<LinesContentBean> arg0 );
    @GET("/v1/app/reader-linescontent/{id}")
    Observable<Response<LinesContentBean>> queryLinesContentBeanById(@Path("id") String id );
    @GET("/v1/app/reader-linescontent")
    Observable<Response<List<LinesContentBean>>> queryLinesContentBeans();
    @POST("/v1/app/reader-linescontent")
    Observable<Response<AjaxResult>> addLinesContentBean(@Body LinesContentBean arg0 );
}