package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class MatchStageModel implements Serializable{

    /**
    *阶段开始时间
    */
    private Date startTime;
    /**
    *作文命题.
    */
    private String articleSubject;
    /**
    *阶段结束时间
    */
    private Date endTime;
    /**
    *比赛阶段流程图
    */
    private List<MatchFlowchartModel> matchFlowcharts;
    /**
    *阶段code
    */
    private String stageCode;
    /**
    *大赛组别编码
    */
    private String groupCode;
    /**
    *比赛阶段名称
    */
    private String stageName;

    public Date getStartTime(){
        return this.startTime;
    }

    public void setStartTime(Date startTime){
        this.startTime = startTime;
    }
    public String getArticleSubject(){
        return this.articleSubject;
    }

    public void setArticleSubject(String articleSubject){
        this.articleSubject = articleSubject;
    }
    public Date getEndTime(){
        return this.endTime;
    }

    public void setEndTime(Date endTime){
        this.endTime = endTime;
    }
    public List<MatchFlowchartModel> getMatchFlowcharts(){
        return this.matchFlowcharts;
    }

    public void setMatchFlowcharts(List<MatchFlowchartModel> matchFlowcharts){
        this.matchFlowcharts = matchFlowcharts;
    }
    public String getStageCode(){
        return this.stageCode;
    }

    public void setStageCode(String stageCode){
        this.stageCode = stageCode;
    }
    public String getGroupCode(){
        return this.groupCode;
    }

    public void setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }
    public String getStageName(){
        return this.stageName;
    }

    public void setStageName(String stageName){
        this.stageName = stageName;
    }

}