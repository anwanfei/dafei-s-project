package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class StudentRecommendVoteBean implements Serializable{

    /**
    *推荐票获得时间
    */
    private Date gainTime;
    /**
    *大赛单元组
    */
    private String unitCode;
    /**
    *推荐票开始时间
    */
    private Date expiryStartTime;
    /**
    *组别名称
    */
    private String groupName;
    /**
    *推荐票ID
    */
    private String recommendId;
    /**
    *推荐票截止时间
    */
    private Date expiryEndTime;
    /**
    *大赛ID
    */
    private String matchId;
    /**
    *推荐票是否使用
    */
    private Integer isUsed;
    /**
    *参赛状态
    */
    private Integer joinMatchState;

    public Date getGainTime(){
        return this.gainTime;
    }

    public void setGainTime(Date gainTime){
        this.gainTime = gainTime;
    }
    public String getUnitCode(){
        return this.unitCode;
    }

    public void setUnitCode(String unitCode){
        this.unitCode = unitCode;
    }
    public Date getExpiryStartTime(){
        return this.expiryStartTime;
    }

    public void setExpiryStartTime(Date expiryStartTime){
        this.expiryStartTime = expiryStartTime;
    }
    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public String getRecommendId(){
        return this.recommendId;
    }

    public void setRecommendId(String recommendId){
        this.recommendId = recommendId;
    }
    public Date getExpiryEndTime(){
        return this.expiryEndTime;
    }

    public void setExpiryEndTime(Date expiryEndTime){
        this.expiryEndTime = expiryEndTime;
    }
    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public Integer getIsUsed(){
        return this.isUsed;
    }

    public void setIsUsed(Integer isUsed){
        this.isUsed = isUsed;
    }
    public Integer getJoinMatchState(){
        return this.joinMatchState;
    }

    public void setJoinMatchState(Integer joinMatchState){
        this.joinMatchState = joinMatchState;
    }

}