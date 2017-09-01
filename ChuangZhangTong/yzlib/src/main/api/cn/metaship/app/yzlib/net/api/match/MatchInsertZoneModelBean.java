package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;

public final class MatchInsertZoneModelBean implements Serializable{

    private List<MatchZoneModel> matchZoneModels;
    private String matchInfoId;

    public List<MatchZoneModel> getMatchZoneModels(){
        return this.matchZoneModels;
    }

    public void setMatchZoneModels(List<MatchZoneModel> matchZoneModels){
        this.matchZoneModels = matchZoneModels;
    }
    public String getMatchInfoId(){
        return this.matchInfoId;
    }

    public void setMatchInfoId(String matchInfoId){
        this.matchInfoId = matchInfoId;
    }

}