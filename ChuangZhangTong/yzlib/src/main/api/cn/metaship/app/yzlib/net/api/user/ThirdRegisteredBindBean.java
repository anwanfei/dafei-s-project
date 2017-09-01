package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class ThirdRegisteredBindBean implements Serializable{

    /**
    *手机号
    */
    private String phoneNumber;
    /**
    *uid
    */
    private String uid;
    /**
    *照片地址
    */
    private String photoUrl;
    /**
    *性别
    */
    private Integer gender;
    /**
    *登录类型
    */
    private String loginType;
    /**
    *昵称
    */
    private String nickname;
    /**
    *令牌
    */
    private String token;

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getUid(){
        return this.uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }
    public String getPhotoUrl(){
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
    public Integer getGender(){
        return this.gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;
    }
    public String getLoginType(){
        return this.loginType;
    }

    public void setLoginType(String loginType){
        this.loginType = loginType;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token = token;
    }

}