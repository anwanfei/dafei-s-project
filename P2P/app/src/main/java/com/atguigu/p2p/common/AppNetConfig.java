package com.atguigu.p2p.common;

/**
 * 提供网络访问数据相关的url地址
 * Created by shkstart on 2016/8/12 0012.
 */
public class AppNetConfig {

    private static final String HOST = "192.168.56.1";

    private static final String BASEURL = "http://" + HOST + ":8080/P2PInvest/";

    public static final String PRODUCT = BASEURL + "product";
    public static final String TEST = BASEURL + "test";
    public static final String INDEX = BASEURL + "index";
    public static final String LOGIN = BASEURL + "login";

}
