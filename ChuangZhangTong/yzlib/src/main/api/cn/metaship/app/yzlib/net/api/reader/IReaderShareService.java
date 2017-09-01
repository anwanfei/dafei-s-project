package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.Query;
import retrofit2.http.POST;
public interface IReaderShareService {
    /**
    * 分享书籍
    */
    @POST("/v1/app-bookshare")
    Observable<Response<String>> shareBook(@Query("bookId") String bookId, @Query("userId") String userId );
}