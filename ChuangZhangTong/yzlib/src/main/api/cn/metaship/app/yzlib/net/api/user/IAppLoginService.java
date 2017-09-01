package cn.metaship.app.yzlib.net.api.user;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.*;
import retrofit2.Response;

import retrofit2.http.GET;
import java.util.Map;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.POST;
import cn.metaship.app.yzlib.net.api.base.AjaxResult;
import retrofit2.http.Body;
public interface IAppLoginService {
    @POST("/v1/other")
    Observable<Response<String>> test();
    /**
    * 修改密码验证码验证
    */
    @POST("/v1/login/resetpwd/app-verify")
    Observable<Response<AjaxResult>> resetVerify(@Body PhoneVerifyBean arg0 );
    /**
    * 注册设置密码
    */
    @POST("/v1/register/inputpassword")
    Observable<Response<UserBean>> registerUser(@Body RegisterBean arg0 );
    /**
    * 登录
    */
    @POST("/v1/login/app-user-login")
    Observable<Response<Map<String,Object>>> loginVerify(@Body LoginBean arg0 );
    @POST("/v1/register/mail/nextstep")
    Observable<Response<AjaxResult>> nextstep(@Body MailRegisterBean arg0 );
    @GET("/v1/register/mail/active/{code}")
    Observable<Response<AjaxResult>> activeUserByMail(@Path("code") String code );
    /**
    * 重置密码
    */
    @POST("/v1/login/resetPwd/app-user-restpwd")
    Observable<Response<AjaxResult>> resetPwd(@Body RegisterBean arg0 );
    /**
    * 注册验证码验证
    */
    @POST("/v1/register/app-verify")
    Observable<Response<AjaxResult>> registerVerify(@Body PhoneVerifyBean arg0 );
    /**
    * 第三方登录-判断第三方账号是否绑定手机号
    */
    @POST("/v1/login/thirdpart-login/isBind")
    Observable<Response<AjaxResult>> thirdPartLogin(@Body ThirdLoginBean arg0 );
    @GET("/v1/register/mail")
    Observable<Response<AjaxResult>> mailRegister(@Query("mail") String mail );
    @GET("/v1/login/redirect")
    Observable<Response<AjaxResult>> redirect();
    /**
    * 修改密码发送验证码
    */
    @GET("/v1/login/resetpwd/app-sendverificationcode")
    Observable<Response<AjaxResult>> resetPwdSendVerificationCode(@Query("phoneNumber") String phoneNumber );
    /**
    * 第三方登录-绑定手机号发送验证码
    */
    @POST("/v1/login/thirdpart-login/sendverificationcode")
    Observable<Response<AjaxResult>> sendRegisteredPhoneCode(@Body ThirdBindPhoneVerifyBean arg0 );
    /**
    * 第三方登录-手机号码是否注册及绑定第三方账号详情
    */
    @GET("/v1/login/thirdpart/app-user-phonenumberverify")
    Observable<Response<UserBean>> verifyPhoneNumber(@Query("phoneNumber") String phoneNumber, @Query("loginType") String loginType );
    /**
    * 注册发送验证码
    */
    @GET("/v1/register/app-sendverificationcode")
    Observable<Response<AjaxResult>> createVerificationCode(@Query("phoneNumber") String phoneNumber );
    @GET("/v1/app/codes")
    Observable<Response<Map<Integer,String>>> queryAll_CodeMessage();
    /**
    * 第三方登录-未注册的手机号绑定第三方账号
    */
    @POST("/v1/login/thirdpart-login/notregisterphone")
    Observable<Response<UserBean>> notRegisteredPhoneBindQQ(@Body ThirdNotRegisterBindBean arg0 );
    /**
    * 第三方登录-已经注册的手机号绑定第三方账号
    */
    @POST("/v1/login/thirdpart-login/registeredphone")
    Observable<Response<UserBean>> RegisteredPhoneBindThirPart(@Body ThirdRegisteredBindBean arg0 );
    /**
    * 第三方登录-绑定手机号验证码验证
    */
    @POST("/v1/login/thirdpart-login/verify")
    Observable<Response<AjaxResult>> bindPhoneCodeVerify(@Body ThirdVerifyBean arg0 );
}