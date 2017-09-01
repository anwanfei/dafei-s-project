package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class StudentUseVoteBean implements Serializable{

    private String userId;
    private String unitCode;
    private String recommendId;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getUnitCode(){
        return this.unitCode;
    }

    public void setUnitCode(String unitCode){
        this.unitCode = unitCode;
    }
    public String getRecommendId(){
        return this.recommendId;
    }

    public void setRecommendId(String recommendId){
        this.recommendId = recommendId;
    }

}