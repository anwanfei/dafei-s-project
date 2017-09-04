package com.junhangxintong.chuanzhangtong.utils;

/**
 * Created by anwanfei on 2017/8/30.
 */

public class ConstantsUrls {

    public static final String LOCAL_BASE_URL = "http://192.168.0.101:8082";
    public static final String WWW_TEST_BASE_URL = "http://116.62.152.191:8082";
    public static final String WWW_BASE_URL = "http://192.168.0.101:8082";

    /**
     * 短信验证登录
     */
    //注册发送验证码
    public static final String SEND_VERIFICATION_CODE = WWW_TEST_BASE_URL + "/user/buiness/login/phone/sendSMS.do";
    //验证码登录
    public static final String LOGIN_BY_VERIFICATION_CODE = WWW_TEST_BASE_URL + "/user/buiness/login/phone.do";

    /**
     * 手机号注册登录
     */
    //注册发送验证码
    public static final String REGIDTER_SEND_VERIFICATION_CODE = WWW_TEST_BASE_URL + "/user/buiness/register/phone/sendSMS.do";
    //手机号注册
    public static final String REGISTER_BY_PHNOE = WWW_TEST_BASE_URL + "/user/buiness/register/phone.do";
    //手机号登录
    public static final String LOGIN_BY_PHNOE = WWW_TEST_BASE_URL + "/user/buiness/login.do";

    /**
     * 忘记密码
     */
    //发送手机验证码
    public static final String SEND_VERIFICATION_CODE_FORGET_PWD = WWW_TEST_BASE_URL + "/user/buiness/find/password/sendSMS.do";
    //验证短信验证码
    public static final String VERIFY_SMS_FORGET_PWD = WWW_TEST_BASE_URL + "/user/buiness/find/password/checkSms.do";
    //提交新密码
    public static final String SUMBITNEWPASSWORD = WWW_TEST_BASE_URL + "/user/buiness/find/password/sumbitNewPassword.do";

    /**
     * 我的个人信息
     */
    //获取个人信息
    public static final String GET_USER_INFO = WWW_TEST_BASE_URL + "/my/baseinfo/get/user/info.do";

    //修改个人信息
    public static final String MODIFY_USER_INFO = WWW_TEST_BASE_URL + "/my/baseinfo/modify/user/info.do";

    //修改手机号发送验证码
    public static final String MODIFY_PHONE_SENDSMS = WWW_TEST_BASE_URL + "/my/baseinfo/phone/sendSMS.do";

    //提交修改手机号
    public static final String COMMIT_MODIFY_PHONE = WWW_TEST_BASE_URL + "/my/baseinfo/phone/update.do";

    /**
     * 设置
     */
    //重置密码
    public static final String RESET_PWD = WWW_TEST_BASE_URL + "/my/baseinfo/password/update.do";

    //退出登录
    public static final String LOGIN_OUT = WWW_TEST_BASE_URL + "/my/baseinfo/user/logout.do";

}
