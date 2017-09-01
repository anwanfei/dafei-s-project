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
public interface ISchoolModelService {
    /**
    * 根据省份编码、城市编码、区县编码、学校名称搜索学校列表(省、市、县、学校名称填一即可，也可组合)
    */
    @GET("/v1/app/school-district")
    Observable<Response<List<SchoolBean>>> findSchoolsByDistrictInfo(@Query("provinceCode") String provinceCode, @Query("cityCode") String cityCode, @Query("countyCode") String countyCode, @Query("schoolName") String schoolName );
    /**
    * 根据省份编码、城市编码、区县编码、学校名称搜索学校列表(省、市、县必填，学校选填)
    */
    @GET("/v1/app/school-limit-district/")
    Observable<Response<List<SchoolBean>>> findSchoolsByLimitDistrictInfo(@Query("provinceCode") String provinceCode, @Query("cityCode") String cityCode, @Query("countyCode") String countyCode, @Query("schoolName") String schoolName );
    @POST("/v1/app/school")
    Observable<Response<SchoolBean>> insertSchool(@Body SchoolBean arg0 );
}