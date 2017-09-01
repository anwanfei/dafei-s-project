package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ScrambleForwardLikeLogBean implements Serializable{

    /**
    *抢票赛转发记录的发表人ID
    */
    private String scrambleForwardUserId;
    /**
    *点赞用户
    */
    private String likeUser;
    /**
    *动作 1-点赞,2-取消点赞
    */
    private Integer action;
    /**
    *抢票赛转发记录ID
    */
    private String scrambleForwardId;
    /**
    *发布时间
    */
    private Date releaseTime;
    private String id;
    /**
    *抢票赛作品ID
    */
    private String scrambleId;
    /**
    *点赞IP
    */
    private String ip;

    public String getScrambleForwardUserId(){
        return this.scrambleForwardUserId;
    }

    public void setScrambleForwardUserId(String scrambleForwardUserId){
        this.scrambleForwardUserId = scrambleForwardUserId;
    }
    public String getLikeUser(){
        return this.likeUser;
    }

    public void setLikeUser(String likeUser){
        this.likeUser = likeUser;
    }
    public Integer getAction(){
        return this.action;
    }

    public void setAction(Integer action){
        this.action = action;
    }
    public String getScrambleForwardId(){
        return this.scrambleForwardId;
    }

    public void setScrambleForwardId(String scrambleForwardId){
        this.scrambleForwardId = scrambleForwardId;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
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