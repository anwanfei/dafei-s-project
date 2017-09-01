package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleVoteLogBean implements Serializable{

    /**
    *投票ip，非必填
    */
    private String ip;
    /**
    *作品作者，必填
    */
    private String entryAuthorId;
    /**
    *投票用户id，非必填
    */
    private String userId;
    /**
    *作品id，必填
    */
    private String entryId;
    /**
    *投票渠道.web-1 android-2 ios-3 wap-4，必填
    */
    private Integer channel;
    private String iMEI;

    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
    public String getEntryAuthorId(){
        return this.entryAuthorId;
    }

    public void setEntryAuthorId(String entryAuthorId){
        this.entryAuthorId = entryAuthorId;
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
    public Integer getChannel(){
        return this.channel;
    }

    public void setChannel(Integer channel){
        this.channel = channel;
    }
    public String getIMEI(){
        return this.iMEI;
    }

    public void setIMEI(String iMEI){
        this.iMEI = iMEI;
    }

}