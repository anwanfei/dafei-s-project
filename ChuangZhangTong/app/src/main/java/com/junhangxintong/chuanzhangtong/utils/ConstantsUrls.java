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
    public static final String SEND_VERIFICATION_CODE = "/user/buiness/login/phone/sendSMS.do";
    //验证码登录
    public static final String LOGIN_BY_VERIFICATION_CODE = "/user/buiness/login/phone.do";

    /**
     * 手机号注册登录
     */
    //注册发送验证码
    public static final String REGIDTER_SEND_VERIFICATION_CODE = "/user/buiness/register/phone/sendSMS.do";
    //手机号注册
    public static final String REGISTER_BY_PHNOE = "/user/buiness/register/phone.do";
    //手机号登录
    public static final String LOGIN_BY_PHNOE = "/user/buiness/login.do";

    /**
     * 忘记密码
     */
    //发送手机验证码
    public static final String SEND_VERIFICATION_CODE_FORGET_PWD = "/user/buiness/find/password/sendSMS.do";
    //验证短信验证码
    public static final String VERIFY_SMS_FORGET_PWD = "/user/buiness/find/password/checkSms.do";
    //提交新密码
    public static final String SUMBITNEWPASSWORD = "/user/buiness/find/password/sumbitNewPassword.do";

    /**
     * 我的个人信息
     */
    //获取个人信息
    public static final String GET_USER_INFO = "/my/baseinfo/get/user/info.do";

    //修改个人信息
    public static final String MODIFY_USER_INFO = "/my/baseinfo/modify/user/info.do";

    //修改手机号发送验证码
    public static final String MODIFY_PHONE_SENDSMS = "/my/baseinfo/phone/sendSMS.do";

    //提交修改手机号
    public static final String COMMIT_MODIFY_PHONE = "/my/baseinfo/phone/update.do";

    /**
     * 设置
     */
    //重置密码
    public static final String RESET_PWD = "/my/baseinfo/password/update.do";

    //退出登录
    public static final String LOGIN_OUT = "/my/baseinfo/user/logout.do";

    /**
     * 意见反馈
     */
    public static final String FEEDBACK = "/my/suggest/add.do";

    /**
     * 船员管理
     */
    //新增船员
    public static final String ADD_CREW = "/my/ship/employeeManger/add.do";

    //船员列表展示
    public static final String CREW_LISTS = "/my/ship/employeeManger/query/list.do";

    //查看船员详情信息
    public static final String CREW_DETAILS = "/my/ship/employeeManger/query/info.do";

    //删除船员
    public static final String DELETE_CREW = "/my/ship/employeeManger/delete.do";

    //修改船员信息
    public static final String MODIFY_CRWE_INFO = "/my/ship/employeeManger/update.do";

    /**
     * 船员证书管理
     */
    //新增船员证书
    public static final String ADD_CREW_CERTIFICATE = "/my/certificate/user/add.do";

    //船员证书列表展示
    public static final String CREW_CERTIFICATE_LISTS = "/my/certificate/user/query/list.do";

    //查看船员证书详情信息
    public static final String CREW_CERTIFICATE_DETAILS = "/my/certificate/user/query/info.do";

    //删除船员证书
    public static final String DELETE_CREW_CERTIFICATE = "/my/certificate/user/delete.do";


    //我的船队
    //我的船（船队列表）
    public static final String MY_SHIP_LISTS = "/my/ship/team/query/list.do";

    //查看船的详情
    public static final String MY_SHIP_INFO = "/my/ship/team/query/info.do";

    //报文记录列表
    public static final String REPORT_LISTS = "/my/ship/report/query/list.do";

    //查询报文详情
    public static final String REPORT_INFO = "/my/ship/report/query/info.do";

    //新增报文
    public static final String ADD_REPORT = "/my/ship/report/add.do";


    //船舶证书管理
    //新增船舶证书
    public static final String ADD_SHIP_CERTIFICATE = "/my/certificate/ship/add.do";

    //新增船舶保险
    public static final String ADD_SHIP_INSURANCE = "/my/certificate/ship/add.do";

    //船舶证书列表
    public static final String SHIP_CERTIFICATE_LIST = "/my/certificate/ship/query/list.do";

    //查看船舶证书详情
    public static final String SHIP_CERTIFICATE_INFO = "/my/certificate/ship/query/info.do";

    //删除船舶证书
    public static final String DELETE_SHIP_CERTIFICATE = "/my/certificate/ship/delete.do";


    //我关注的船
    //我关注的船列表
    public static final String FOLLOW_SHIP_LIST = "/my/ship/attention/query/list.do";

    //添加我关注的船
    public static final String ADD_FOLLOW_SHIP = "/my/ship/attention/add.do";

    //删除我关注的船
    public static final String DELETE_FOLLOW_SHIP = "/my/ship/attention/delete.do";

    //查看我关注的船的详情
    public static final String FOLLOW_SHIP_INFO = "/my/ship/attention/query/info.do";

    //取消我关注的船
    public static final String CANCEL_FOLLOW_SHIP = "/my/ship/attention/cancel.do";


    /**
     * 航运头条
     */
    //展示列表
    public static final String QUERY_NEWS_LISTS = "/ship/headline/query/list.do";

    //查询详情信息
    public static final String QUERY_NEWS_DETAILS = "/ship/headline/query/info.do";



    /**
     * 动态提醒
     */
    //动态提醒列表
    public static final String DYNAMIC_REMIND_LIST = "/remind/dynamic/query/list.do";

    //查看动态详情
    public static final String DYNAMIC_DETAILS = "/remind/dynamic/query/info.do";

    //报文动态提醒列表
    public static final String REPORT_DYNAMIC_LISTS = "/remind/dynamic/report/query/list.do";


    /**
     * 船员信息
     */
    //确认添加船员
    public static final String SHIP_ADD_CREW = "/ship/employeeInfo/add.do";

    //船员列表
    public static final String SHIP_CREW_LISTS = "/ship/employeeInfo/query/list.do";

    //查看船员详情信息
    public static final String SHIP_CREW_DETAILS = "/ship/employeeInfo/query/info.do";

    //删除船员
    public static final String SHIP_DELETE_CREW = "/ship/employeeInfo/delete.do";

    //可添加船员
    public static final String SHIP_ADDTIVE_CRWE_LIST = "/ship/employeeInfo/add/list.do";

}
