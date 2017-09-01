package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class MatchStageBean implements Serializable{

    private String stageName;
    private String flowchartImgUrl;
    private String stageCode;
    private String groupCode;

    public String getStageName(){
        return this.stageName;
    }

    public void setStageName(String stageName){
        this.stageName = stageName;
    }
    public String getFlowchartImgUrl(){
        return this.flowchartImgUrl;
    }

    public void setFlowchartImgUrl(String flowchartImgUrl){
        this.flowchartImgUrl = flowchartImgUrl;
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

}