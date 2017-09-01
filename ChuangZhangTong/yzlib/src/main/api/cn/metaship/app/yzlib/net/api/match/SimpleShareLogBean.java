package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SimpleShareLogBean implements Serializable{

    /**
    *分享ip，非必填
    */
    private String ip;
    /**
    *作品作者，必填
    */
    private String entryAuthorId;
    /**
    *分享后的评论内容，非必填
    */
    private String shareCommentContent;
    /**
    *分享内容,文章地址，非必填
    */
    private String shareContent;
    /**
    *分享用户id，非必填
    */
    private String userId;
    /**
    *分享类型。qq-1，微信-2，微博-3，必填
    */
    private Integer shareType;
    /**
    *作品id，必填
    */
    private String entryId;
    /**
    *分享渠道.web-1 android-2 ios-3 wap-4，必填
    */
    private Integer channel;

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
    public String getShareCommentContent(){
        return this.shareCommentContent;
    }

    public void setShareCommentContent(String shareCommentContent){
        this.shareCommentContent = shareCommentContent;
    }
    public String getShareContent(){
        return this.shareContent;
    }

    public void setShareContent(String shareContent){
        this.shareContent = shareContent;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public Integer getShareType(){
        return this.shareType;
    }

    public void setShareType(Integer shareType){
        this.shareType = shareType;
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

}