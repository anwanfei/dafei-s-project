package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class StudentMatchBean implements Serializable{

    private String nickname;
    private String name;
    private String userName;
    private String userId;
    private String signature;

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getSignature(){
        return this.signature;
    }

    public void setSignature(String signature){
        this.signature = signature;
    }

}