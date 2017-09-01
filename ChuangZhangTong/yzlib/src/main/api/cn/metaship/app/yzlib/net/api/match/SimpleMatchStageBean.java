package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class SimpleMatchStageBean implements Serializable{

    /**
    *是否当前阶段.(1-是，2，否)
    */
    private Integer isCurrent;
    /**
    *比赛阶段名称
    */
    private String stageName;
    /**
    *阶段开始时间
    */
    private Date startTime;
    /**
    *阶段结束时间
    */
    private Date endTime;
    /**
    *阶段code
    */
    private String stageCode;

    public Integer getIsCurrent(){
        return this.isCurrent;
    }

    public void setIsCurrent(Integer isCurrent){
        this.isCurrent = isCurrent;
    }
    public String getStageName(){
        return this.stageName;
    }

    public void setStageName(String stageName){
        this.stageName = stageName;
    }
    public Date getStartTime(){
        return this.startTime;
    }

    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }
    public Date getEndTime(){
        return this.endTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }
    public String getStageCode(){
        return this.stageCode;
    }

    public void setStageCode(String stageCode){
        this.stageCode = stageCode;
    }

}