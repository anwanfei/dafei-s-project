package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IReaderCorrectionService {
    /**
    * 阅读器纠错
    */
    @POST("/v1/app/bookcorrection")
    Observable<Response<BookCorrectionBean>> bookCorrection(@Body BookCorrectionBean arg0 );
}