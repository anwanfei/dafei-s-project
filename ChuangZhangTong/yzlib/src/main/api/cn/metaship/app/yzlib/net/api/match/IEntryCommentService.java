package cn.metaship.app.yzlib.net.api.match;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import java.util.List;
import retrofit2.http.Query;
import retrofit2.http.POST;
import retrofit2.http.Body;
public interface IEntryCommentService {
    /**
    * 添加作品评论
    */
    @POST("/v1/entrycomment/addcomment")
    Observable<Response<SimpleEntryCommentResultBean>> insertEntryComment(@Body EntryCommentBean arg0 );
    /**
    * 查询评论
    */
    @POST("/v1/entrycomment/query-comments")
    Observable<Response<List<SimpleEntryCommentResultBean>>> queryEntryCommentByPage(@Body cn.metaship.app.yzlib.net.api.base.Query<EntryCommentModel> arg0, @Query("page") Integer page, @Query("size") Integer size );
}