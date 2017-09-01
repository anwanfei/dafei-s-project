package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class ThirdBindPhoneVerifyBean implements Serializable{

    /**
    *登录类型，QQ、SINA、WEIXIN
    */
    private String loginType;
    /**
    *手机号
    */
    private String phoneNumber;

    public String getLoginType(){
        return this.loginType;
    }

    public void setLoginType(String loginType){
        this.loginType = loginType;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

}