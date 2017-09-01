package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleBrowserLogBean implements Serializable{

    /**
    *作品作者，必填
    */
    private String entryAuthorId;
    /**
    *浏览ip，非必填
    */
    private String ip;
    /**
    *浏览渠道.web-1 android-2 ios-3 wap-4，必填
    */
    private Integer channel;
    /**
    *作品id，必填
    */
    private String entryId;
    /**
    *浏览用户id，非必填
    */
    private String userId;

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
    public Integer getChannel(){
        return this.channel;
    }

    public void setChannel(Integer channel){
        this.channel = channel;
    }
    public String getEntryId(){
        return this.entryId;
    }

    public void setEntryId(String entryId){
        this.entryId = entryId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

}