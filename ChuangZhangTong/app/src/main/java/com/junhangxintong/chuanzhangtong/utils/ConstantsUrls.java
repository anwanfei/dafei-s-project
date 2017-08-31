package com.junhangxintong.chuanzhangtong.utils;

/**
 * Created by anwanfei on 2017/8/30.
 */

public class ConstantsUrls {

    public static final String LOCAL_BASE_URL = "http://192.168.0.101:8082";
    public static final String WWW_TEST_BASE_URL = "http://116.62.152.191:8082";
    public static final String WWW_BASE_URL = "http://192.168.0.101:8082";
    //注册发送验证码
    public static final String SEND_VERIFICATION_CODE = LOCAL_BASE_URL + "/user/buiness/login/phone/sendSMS.do";
    //验证码登录
    public static final String LOGIN_BY_PHNOE = LOCAL_BASE_URL + "/user/buiness/login/phone.do";

}
