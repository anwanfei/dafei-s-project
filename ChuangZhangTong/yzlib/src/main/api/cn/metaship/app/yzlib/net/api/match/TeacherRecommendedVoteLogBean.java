package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class TeacherRecommendedVoteLogBean implements Serializable{

    /**
    *推荐票有效期截止时间
    */
    private Date expiryEndTime;
    /**
    *文案内容
    */
    private String copywriterContent;
    /**
    *推荐票类型（1-海选赛推荐票，有其他类型再加）
    */
    private Integer voteType;
    /**
    *大赛ID
    */
    private String matchId;
    /**
    *受赠用户ID
    */
    private String recipientId;
    /**
    *受赠用户名称
    */
    private String recipientName;
    /**
    *赠送时间
    */
    private Date givenTime;
    /**
    *推荐票获得时间
    */
    private Date gainTime;
    /**
    *是否赠送 1-未赠送 2-赠送
    */
    private Integer isGiven;
    private String id;
    /**
    *推荐票id
    */
    private String recommendVoteId;
    /**
    *推荐票有效期开始时间
    */
    private Date expiryStartTime;
    /**
    *教师用户id
    */
    private String teacherId;

    public Date getExpiryEndTime(){
        return this.expiryEndTime;
    }

    public void setExpiryEndTime(Date expiryEndTime){
        this.expiryEndTime = expiryEndTime;
    }
    public String getCopywriterContent(){
        return this.copywriterContent;
    }

    public void setCopywriterContent(String copywriterContent){
        this.copywriterContent = copywriterContent;
    }
    public Integer getVoteType(){
        return this.voteType;
    }

    public void setVoteType(Integer voteType){
        this.voteType = voteType;
    }
    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public String getRecipientId(){
        return this.recipientId;
    }

    public void setRecipientId(String recipientId){
        this.recipientId = recipientId;
    }
    public String getRecipientName(){
        return this.recipientName;
    }

    public void setRecipientName(String recipientName){
        this.recipientName = recipientName;
    }
    public Date getGivenTime(){
        return this.givenTime;
    }

    public void setGivenTime(Date givenTime){
        this.givenTime = givenTime;
    }
    public Date getGainTime(){
        return this.gainTime;
    }

    public void setGainTime(Date gainTime){
        this.gainTime = gainTime;
    }
    public Integer getIsGiven(){
        return this.isGiven;
    }

    public void setIsGiven(Integer isGiven){
        this.isGiven = isGiven;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getRecommendVoteId(){
        return this.recommendVoteId;
    }

    public void setRecommendVoteId(String recommendVoteId){
        this.recommendVoteId = recommendVoteId;
    }
    public Date getExpiryStartTime(){
        return this.expiryStartTime;
    }

    public void setExpiryStartTime(Date expiryStartTime){
        this.expiryStartTime = expiryStartTime;
    }
    public String getTeacherId(){
        return this.teacherId;
    }

    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }

}