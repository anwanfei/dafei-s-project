package cn.metaship.app.yzlib.net.api.user;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IUserAccountTestService {
    @POST("/v/user/account/score/consume")
    void consumeScore(@Body AccountScoreNormalPostBean arg0 );
    @POST("/v1/user/account/score/normal-post")
    void scoreNormalPost(@Body AccountScoreNormalPostBean arg0 );
    @POST("/v1/user/account/coin/normal-post")
    void coinNormlPost(@Body AccountCoinNormalPostBean arg0 );
    @POST("/v1/user/account/coin/consume")
    void consumeCoin(@Body AccountCoinNormalPostBean arg0 );
}