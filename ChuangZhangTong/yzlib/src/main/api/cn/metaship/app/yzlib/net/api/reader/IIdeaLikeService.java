package cn.metaship.app.yzlib.net.api.reader;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IIdeaLikeService {
    /**
    * 添加点赞或点踩记录
    */
    @POST("/v1/app/idealike")
    Observable<Response<IdeaLikeBean>> insertIdeaLikeBean(@Body IdeaLikeBean arg0 );
    /**
    * 根据idea的id、点赞状态排序并且分页
    */
    @POST("/v1/app/idealike/users")
    Observable<Response<List<UserBean>>> queryLikeUsers(@Body cn.metaship.app.yzlib.net.api.base.Query<IdeaLikeModel> arg0, @Query("page") int page, @Query("size") int size );
}