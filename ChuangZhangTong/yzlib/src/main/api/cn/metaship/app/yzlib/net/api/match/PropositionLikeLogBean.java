package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class PropositionLikeLogBean implements Serializable{

    /**
    *点赞IP
    */
    private String ip;
    /**
    *点赞发布时间
    */
    private Date releaseTime;
    /**
    *命题参与者
    */
    private String propositionParticipator;
    /**
    *点赞用户ID
    */
    private String userId;
    private String id;
    /**
    *命题ID
    */
    private String propositionId;
    /**
    *点赞状态，1-点赞，2-取消点赞
    */
    private Integer action;

    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public String getPropositionParticipator(){
        return this.propositionParticipator;
    }

    public void setPropositionParticipator(String propositionParticipator){
        this.propositionParticipator = propositionParticipator;
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
    public String getPropositionId(){
        return this.propositionId;
    }

    public void setPropositionId(String propositionId){
        this.propositionId = propositionId;
    }
    public Integer getAction(){
        return this.action;
    }

    public void setAction(Integer action){
        this.action = action;
    }

}