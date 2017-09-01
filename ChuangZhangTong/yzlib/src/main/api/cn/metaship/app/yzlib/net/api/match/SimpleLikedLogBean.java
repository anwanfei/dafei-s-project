package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleLikedLogBean implements Serializable{

    /**
    *上传渠道.web-1 android-2 ios-3 wap-4，必填
    */
    private Integer channel;
    /**
    *点赞动作，1-点赞，2-取消点赞
    */
    private Byte action;
    /**
    *作品评论id，非必填
    */
    private String entryComentId;
    /**
    *点赞用户id，必填
    */
    private String userId;
    /**
    *作品id，必填
    */
    private String entryId;
    /**
    *作品作者，必填
    */
    private String entryAuthorId;
    /**
    *点赞ip，非必填
    */
    private String ip;

    public Integer getChannel(){
        return this.channel;
    }

    public void setChannel(Integer channel){
        this.channel = channel;
    }
    public Byte getAction(){
        return this.action;
    }

    public void setAction(Byte action){
        this.action = action;
    }
    public String getEntryComentId(){
        return this.entryComentId;
    }

    public void setEntryComentId(String entryComentId){
        this.entryComentId = entryComentId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getEntryId(){
        return this.entryId;
    }

    public void setEntryId(String entryId){
        this.entryId = entryId;
    }
    public String getEntryAuthorId(){
        return this.entryAuthorId;
    }

    public void setEntryAuthorId(String entryAuthorId){
        this.entryAuthorId = entryAuthorId;
    }
    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

}