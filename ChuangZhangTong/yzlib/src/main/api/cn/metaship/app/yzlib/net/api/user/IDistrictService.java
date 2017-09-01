package cn.metaship.app.yzlib.net.api.user;

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
public interface IDistrictService {
    /**
    * 批量添加城区信息
    */
    @POST("/v1/app/district-batch")
    Observable<Response<List<DistrictModel>>> batchInsertDistricts(@Body List<DistrictBean> arg0 );
    /**
    * 根据城市编码查询城区信息
    */
    @GET("/v1/app/district-query-districtcode")
    Observable<Response<DistrictBean>> queryByDistrictCode(@Query("districtCode") String districtCode );
    /**
    * 根据城市名称查询城区信息
    */
    @GET("/v1/app/district-query-districtname")
    Observable<Response<DistrictBean>> queryByDistrictName(@Query("districtName") String districtName );
    /**
    * 查询所有城市信息列表
    */
    @GET("/v1/app/district")
    Observable<Response<List<DistrictBean>>> queryAllDistricts();
    /**
    * 添加一条城区信息
    */
    @POST("/v1/app/district")
    Observable<Response<DistrictBean>> insertDistrict(@Body DistrictBean arg0 );
}