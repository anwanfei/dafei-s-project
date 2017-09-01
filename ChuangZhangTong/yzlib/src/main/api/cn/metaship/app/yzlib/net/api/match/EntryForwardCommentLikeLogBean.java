package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class EntryForwardCommentLikeLogBean implements Serializable{

    private String userId;
    private String entryForwardUserId;
    private Date likeTime;
    private Byte action;
    private String commentId;
    private String entryForwardId;
    private Integer channel;
    private String ip;
    private String id;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getEntryForwardUserId(){
        return this.entryForwardUserId;
    }

    public void setEntryForwardUserId(String entryForwardUserId){
        this.entryForwardUserId = entryForwardUserId;
    }
    public Date getLikeTime(){
        return this.likeTime;
    }

    public void setLikeTime(Date likeTime){
        this.likeTime = likeTime;
    }
    public Byte getAction(){
        return this.action;
    }

    public void setAction(Byte action){
        this.action = action;
    }
    public String getCommentId(){
        return this.commentId;
    }

    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    public String getEntryForwardId(){
        return this.entryForwardId;
    }

    public void setEntryForwardId(String entryForwardId){
        this.entryForwardId = entryForwardId;
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
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}