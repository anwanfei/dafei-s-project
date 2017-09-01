package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class LikedLogBean implements Serializable{

    /**
    *作品作者
    */
    private String entryAuthorId;
    /**
    *点赞ip
    */
    private String ip;
    /**
    *作品评论id
    */
    private String entryCommentId;
    /**
    *上传渠道.web-1 android-2 ios-3 wap-4
    */
    private Integer channel;
    /**
    *点赞动作，1-点赞，2-取消点赞
    */
    private Byte action;
    private String id;
    /**
    *作品id
    */
    private String entryId;
    /**
    *点赞用户id
    */
    private String userId;
    /**
    *点赞时间
    */
    private Date likedTime;

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
    public String getEntryCommentId(){
        return this.entryCommentId;
    }

    public void setEntryCommentId(String entryCommentId){
        this.entryCommentId = entryCommentId;
    }
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
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
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
    public Date getLikedTime(){
        return this.likedTime;
    }

    public void setLikedTime(Date likedTime){
        this.likedTime = likedTime;
    }

}