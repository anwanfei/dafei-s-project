package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BigBangWordReplyResultBean implements Serializable{

    private String avatarUrl;
    private BigBangTopicReplyModel model;
    private String nickname;

    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public BigBangTopicReplyModel getModel(){
        return this.model;
    }

    public void setModel(BigBangTopicReplyModel model){
        this.model = model;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

}