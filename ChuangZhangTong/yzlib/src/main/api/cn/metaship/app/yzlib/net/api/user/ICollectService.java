package cn.metaship.app.yzlib.net.api.user;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.POST;
import retrofit2.http.Body;
public interface ICollectService {
    /**
    * 添加收藏
    */
    @POST("/v1/collect")
    Observable<Response<CollectModel>> insertOneCollect(@Body CollectModel arg0 );
}