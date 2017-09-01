package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleUserBean implements Serializable{

    /**
    *用户头像
    */
    private String avatarUrl;
    /**
    *用户名
    */
    private String userName;
    /**
    *用户ID
    */
    private String userId;
    /**
    *用户昵称
    */
    private String nickname;

    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
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
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

}