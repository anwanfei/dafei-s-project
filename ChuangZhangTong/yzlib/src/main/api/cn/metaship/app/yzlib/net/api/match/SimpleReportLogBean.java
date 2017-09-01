package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleReportLogBean implements Serializable{

    /**
    *举报用户id，非必填
    */
    private String userId;
    /**
    *作品id，必填
    */
    private String entryId;
    /**
    *举报渠道.web-1 android-2 ios-3 wap-4，必填
    */
    private Integer channel;
    /**
    *举报ip，非必填
    */
    private String ip;
    /**
    *举报标签,app端选择的举报内容，非必填
    */
    private String reportTag;
    /**
    *作品作者，必填
    */
    private String entryAuthorId;
    /**
    *举报内容，非必填
    */
    private String reportContent;

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
    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
    public String getReportTag(){
        return this.reportTag;
    }

    public void setReportTag(String reportTag){
        this.reportTag = reportTag;
    }
    public String getEntryAuthorId(){
        return this.entryAuthorId;
    }

    public void setEntryAuthorId(String entryAuthorId){
        this.entryAuthorId = entryAuthorId;
    }
    public String getReportContent(){
        return this.reportContent;
    }

    public void setReportContent(String reportContent){
        this.reportContent = reportContent;
    }

}