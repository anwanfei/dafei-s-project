package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class UserJoinScrambleMatchCommentLikeLogBean implements Serializable{

    private String scrambleAuthorId;
    private Byte action;
    private String commentId;
    private Integer channel;
    private Date likeTime;
    private String userId;
    private String id;
    private String scrambleId;
    private String ip;

    public String getScrambleAuthorId(){
        return this.scrambleAuthorId;
    }

    public void setScrambleAuthorId(String scrambleAuthorId){
        this.scrambleAuthorId = scrambleAuthorId;
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
    public Date getLikeTime(){
        return this.likeTime;
    }

    public void setLikeTime(Date likeTime){
        this.likeTime = likeTime;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getScrambleId(){
        return this.scrambleId;
    }

    public void setScrambleId(String scrambleId){
        this.scrambleId = scrambleId;
    }
    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

}