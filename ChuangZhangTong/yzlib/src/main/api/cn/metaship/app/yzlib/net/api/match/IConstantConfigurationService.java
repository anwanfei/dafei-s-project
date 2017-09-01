package cn.metaship.app.yzlib.net.api.match;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import java.util.Map;
import retrofit2.http.Query;
import retrofit2.http.POST;
public interface IConstantConfigurationService {
    @POST("/v1/constantconfiguration/insert")
    Observable<Response<List<ConstantConfigurationModel>>> insertOne();
    @GET("/v1/constantconfiguration/query-key")
    Observable<Response<Object>> findValueByKey(@Query("key") String key );
    @GET("/v1/constantconfiguration/query-value")
    Observable<Response<Map<Object,Object>>> findValueByType(@Query("type") String type );
    @POST("/v1/constantconfiguration/expiry-key")
    void setOneKey(@Query("key") String key );
    @GET("/v1/constant/config/real-time")
    void runRealTimeTask();
}