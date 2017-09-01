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
public interface IBigBangWordAskService {
    /**
    * 插入一条bigbang提问数据
    */
    @POST("/v1/app/bigbangwordask")
    Observable<Response<BigBangWordAskBean>> insert(@Body BigBangWordRequestBodyBean arg0 );
    /**
    * 根据bigbang的分词位置查询该单词的信息
    */
    @POST("/v1/app/bigbangwordask-position")
    Observable<Response<List<BigBangWordBean>>> queryAllBigbangwordBean(@Body BigbangRequestByBookType arg0, @Query("bookId") String bookId );
    /**
    * 根据用户id查询所有的提问(我的提问)
    */
    @GET("/v1/app/bigbangwordasks/userid/{id}")
    Observable<Response<List<BigBangWordAskResultBean>>> queryAllByUserId(@Path("id") String id );
    /**
    * 根据单词对象查询所有的提问
    */
    @POST("/v1/app/bigbangwordasks/bigbangword")
    Observable<Response<List<BigBangWordAskResultBean>>> queryAllByWordId(@Body BigBangWordBean arg0 );
}