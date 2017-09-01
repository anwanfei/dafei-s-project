package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;

public final class SimpleMatchInfoBean implements Serializable{

    /**
    *大赛名称
    */
    private String matchName;
    /**
    *大赛id
    */
    private String matchId;
    /**
    *比赛阶段，包含比赛阶段编码和比赛阶段流程图地址
    */
    private List<MatchGroupBean> matchGroups;
    /**
    *大赛赛区列表
    */
    private List<MatchZoneModel> zones;

    public String getMatchName(){
        return this.matchName;
    }

    public void setMatchName(String matchName){
        this.matchName = matchName;
    }
    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public List<MatchGroupBean> getMatchGroups(){
        return this.matchGroups;
    }

    public void setMatchGroups(List<MatchGroupBean> matchGroups){
        this.matchGroups = matchGroups;
    }
    public List<MatchZoneModel> getZones(){
        return this.zones;
    }

    public void setZones(List<MatchZoneModel> zones){
        this.zones = zones;
    }

}