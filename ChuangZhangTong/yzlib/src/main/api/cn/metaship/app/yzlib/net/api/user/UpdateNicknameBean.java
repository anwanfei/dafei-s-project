package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class UpdateNicknameBean implements Serializable{

    private String userId;
    private String nickname;

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