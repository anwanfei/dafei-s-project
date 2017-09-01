package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BigBangWordAskResultBean implements Serializable{

    private BigBangWordAskBean bigBangWordAskBean;
    private String nickname;
    private String avatarUrl;

    public BigBangWordAskBean getBigBangWordAskBean(){
        return this.bigBangWordAskBean;
    }

    public void setBigBangWordAskBean(BigBangWordAskBean bigBangWordAskBean){
        this.bigBangWordAskBean = bigBangWordAskBean;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

}