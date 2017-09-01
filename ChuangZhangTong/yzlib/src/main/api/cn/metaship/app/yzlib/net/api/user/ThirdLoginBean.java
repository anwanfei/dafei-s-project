package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class ThirdLoginBean implements Serializable{

    /**
    *验证信息，存储三方登陆的验证信息
    */
    private String accessToken;
    /**
    *登陆类型，weixin、sina、qq,大写
    */
    private String loginType;
    /**
    *uid
    */
    private String uid;

    public String getAccessToken(){
        return this.accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
    public String getLoginType(){
        return this.loginType;
    }

    public void setLoginType(String loginType){
        this.loginType = loginType;
    }
    public String getUid(){
        return this.uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }

}