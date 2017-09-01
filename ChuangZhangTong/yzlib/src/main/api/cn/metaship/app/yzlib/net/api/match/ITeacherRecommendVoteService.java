package cn.metaship.app.yzlib.net.api.match;

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
public interface ITeacherRecommendVoteService {
    /**
    * 老师赠送学生推荐票
    */
    @POST("/v1/teacherrecommendvote/give-recommend-vote")
    void giveStudentVote(@Body TeacherGiveVoteBean arg0 );
    /**
    * 学生使用推荐票
    */
    @POST("/v1/teacherrecommendvote/student-use-recommend-vote")
    void studentUserVote(@Body StudentUseVoteBean arg0 );
    /**
    * 根据学生用户id查询学生持有的推荐票
    */
    @GET("/v1/studentrecommend/{userId}")
    Observable<Response<StudentRecommendVoteBean>> queryVoteInfoByUserId(@Path("userId") String userId );
    /**
    * 查询老师持有的推荐票
    */
    @GET("/v1/teacherrecommendvote/query-given")
    Observable<Response<List<TeacherRecommendedVoteLogBean>>> queryVoteLogByGivenCondition(@Query("userId") String userId, @Query("matchId") String matchId, @Query("isGiven") Integer isGiven, @Query("page") Integer page, @Query("size") Integer size );
}