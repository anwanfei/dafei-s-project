package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IBigBangWordShareService {
    /**
    * 保存分享
    */
    @POST("/v1/app/bigbangwordshare")
    Observable<Response<String>> saveShare(@Body BigBangWordShareBean arg0 );
}