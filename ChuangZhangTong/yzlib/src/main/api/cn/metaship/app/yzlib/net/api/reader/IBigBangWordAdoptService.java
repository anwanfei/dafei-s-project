package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IBigBangWordAdoptService {
    /**
    * 根据单词id查找所有的领养记录
    */
    @GET("/v1/app/bigbangwordadopts/{id}")
    Observable<Response<List<BigBangWordAdoptBean>>> queryAll(@Path("id") String id );
    /**
    * 根据用户id查询领养词汇（某人的词汇库）
    */
    @GET("/v1/app/bigbangwordadopt-worddb/{id}")
    Observable<Response<WordStorageResultBean>> queryByUserId(@Path("id") String id, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 添加领养记录
    */
    @POST("/v1/app/bigbangwordadopt")
    Observable<Response<List<UserBean>>> insertAdoptBean(@Body BigBangWordAdoptRequestBean arg0 );
    /**
    * 查看每个单词的领养用户总数，最新九个领养用户
    */
    @GET("/v1/app/bigbangwordadopt/adopt-record")
    Observable<Response<BigBangWordAdoptRecordBean>> findByWord(@Query("wordId") String wordId );
}