package cn.metaship.app.yzlib.net.api.user;

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
public interface IAppUserInfoService {
    @GET("/v1/app/get-userinfo")
    Observable<Response<UserBean>> getUserInfo(@Query("userId") String userId );
    /**
    * 学生激活身份过程中上传学生证头像;职业证书
    */
    @POST("/v1/userinfo/active/upload-student-avatar")
    Observable<Response<String>> uploadStudentAvatar(@Body okhttp3.MultipartBody arg0 );
    /**
    * 激活平台老师身份
    */
    @POST("/v1/userinfo/active/platform-teacher")
    Observable<Response<UserBean>> activeTeacherRole(@Body ActivePlatformTeacherBean arg0 );
    /**
    *  根据体制内教师用户id查询教师的身份信息
    */
    @GET("/v1/userinfo/find-active-system-teacher")
    Observable<Response<SystemTeacherDetailsModel>> findSystemTeacherByUserId(@Query("userId") String userId );
    /**
    *  查询用户基本信息接口
    */
    @GET("/v1/userinfo/find-base-user")
    Observable<Response<BaseUserBean>> findBaseUserBeanByUserId(@Query("userId") String userId );
    /**
    * 激活体制内老师身份
    */
    @POST("/v1/userinfo/active/system-teacher")
    Observable<Response<UserBean>> activeSystemTeacherRole(@Body ActiveSystemTeacherBean arg0 );
    /**
    *  根据教师id、学生名称查询教师的参赛学生列表
    */
    @GET("/v1/userinfo/find-teacher-match-students")
    Observable<Response<List<StudentMatchBean>>> findByTeacherIdAndStudentName(@Query("teacherId") String teacherId, @Query("studentName") String studentName, @Query("page") Integer page, @Query("size") Integer size );
    /**
    * 用户注册时添加一些默认设置
    */
    @POST("/v1/userinfo/set-invitation")
    void setInvitationCode(@Query("userId") String userId );
    /**
    * 选择身份进行激活的时候，点击学生身份按钮的激活身份查询
    */
    @GET("/v1/userinfo/get-user-active-info")
    Observable<Response<StudentDetailsModel>> queryStudentActiveInfo(@Query("userId") String userId );
    /**
    *  查询参赛学生的指导老师列表
    */
    @GET("/v1/userinfo/find-student-teachers")
    Observable<Response<List<TutorBean>>> queryTeachersByStudentClassInfo(@Query("userId") String userId );
    @POST("/v1/app/put-user-avatar/{id}")
    Observable<Response<UserBean>> putUserAvatar(@Path("id") String id, @Body okhttp3.MultipartBody avatar );
    @POST("/v1/app/put-user-signature")
    Observable<Response<UserBean>> putUserSignature(@Body UpdateSignatureBean arg0 );
    @POST("/v1/app/put-user-nickname")
    Observable<Response<UserBean>> putUserNickname(@Body UpdateNicknameBean arg0 );
    /**
    * 查看用户身份认证信息-学生
    */
    @GET("/v1/app/userinfo/auth-student/{id}")
    Observable<Response<StudentDetailsModel>> getAuthUserInfo(@Path("id") String id );
    /**
    * 激活学生身份
    */
    @POST("/v1/userinfo/active/student")
    Observable<Response<UserBean>> activeStudent(@Body ActiveStudentBean arg0 );
    @POST("/v1/app/put-user-gender")
    Observable<Response<UserBean>> putUserGender(@Body UpdateGenderBean arg0 );
    @POST("/v1/app/put-user-birthday")
    Observable<Response<UserBean>> putUserBirthday(@Body UpdateBirthdayBean arg0 );
}