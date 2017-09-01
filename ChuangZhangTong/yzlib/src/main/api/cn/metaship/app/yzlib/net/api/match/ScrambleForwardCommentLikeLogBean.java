package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ScrambleForwardCommentLikeLogBean implements Serializable{

    private String scrambleForwardId;
    private String ip;
    private String scrambleForwardUserId;
    private Byte action;
    private String commentId;
    private Integer channel;
    private String id;
    private String userId;
    private Date likeTime;

    public String getScrambleForwardId(){
        return this.scrambleForwardId;
    }

    public void setScrambleForwardId(String scrambleForwardId){
        this.scrambleForwardId = scrambleForwardId;
    }
    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
    public String getScrambleForwardUserId(){
        return this.scrambleForwardUserId;
    }

    public void setScrambleForwardUserId(String scrambleForwardUserId){
        this.scrambleForwardUserId = scrambleForwardUserId;
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
    public Integer getChannel(){
        return this.channel;
    }

    public void setChannel(Integer channel){
        this.channel = channel;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public Date getLikeTime(){
        return this.likeTime;
    }

    public void setLikeTime(Date likeTime){
        this.likeTime = likeTime;
    }

}