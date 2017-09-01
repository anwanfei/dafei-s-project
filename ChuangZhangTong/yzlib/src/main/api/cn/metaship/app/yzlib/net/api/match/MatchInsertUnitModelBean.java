package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;

public final class MatchInsertUnitModelBean implements Serializable{

    private List<UnitModel> unitModels;
    private String matchInfoId;

    public List<UnitModel> getUnitModels(){
        return this.unitModels;
    }

    public void setUnitModels(List<UnitModel> unitModels){
        this.unitModels = unitModels;
    }
    public String getMatchInfoId(){
        return this.matchInfoId;
    }

    public void setMatchInfoId(String matchInfoId){
        this.matchInfoId = matchInfoId;
    }

}