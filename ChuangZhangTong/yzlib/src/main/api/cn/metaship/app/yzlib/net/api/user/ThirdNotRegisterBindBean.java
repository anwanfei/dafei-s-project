package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class ThirdNotRegisterBindBean implements Serializable{

    /**
    *确认密码
    */
    private String confirmPassword;
    /**
    *昵称
    */
    private String nickname;
    /**
    *令牌
    */
    private String accessToken;
    /**
    *uid
    */
    private String uid;
    /**
    *头像地址
    */
    private String photoUrl;
    /**
    *手机号
    */
    private String phoneNumber;
    /**
    *密码
    */
    private String password;
    /**
    *登录类型
    */
    private String loginType;
    /**
    *地址
    */
    private Integer gender;

    public String getConfirmPassword(){
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getAccessToken(){
        return this.accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
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
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getLoginType(){
        return this.loginType;
    }

    public void setLoginType(String loginType){
        this.loginType = loginType;
    }
    public Integer getGender(){
        return this.gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;
    }

}