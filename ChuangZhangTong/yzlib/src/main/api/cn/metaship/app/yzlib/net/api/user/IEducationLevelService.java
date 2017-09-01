package cn.metaship.app.yzlib.net.api.user;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IEducationLevelService {
    /**
    * 添加一条教育等级数据
    */
    @POST("/v1/educationlevel")
    Observable<Response<EducationLevelBean>> insertEducationLevel(@Body EducationLevelBean arg0 );
    /**
    * 批量添加教育等级数据
    */
    @POST("/v1/educationlevel/batch")
    Observable<Response<List<EducationLevelModel>>> insertBatch(@Body List<EducationLevelBean> arg0 );
    /**
    * 查找所有教育等级对象
    */
    @GET("/v1/educationlevel")
    Observable<Response<List<EducationLevelBean>>> findAll();
}