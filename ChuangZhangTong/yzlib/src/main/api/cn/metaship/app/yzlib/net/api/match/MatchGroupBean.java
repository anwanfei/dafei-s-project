package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class MatchGroupBean implements Serializable{

    private MatchStageBean stage;
    private String groupName;
    private String groupCode;

    public MatchStageBean getStage(){
        return this.stage;
    }

    public void setStage(MatchStageBean stage){
        this.stage = stage;
    }
    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public String getGroupCode(){
        return this.groupCode;
    }

    public void setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }

}