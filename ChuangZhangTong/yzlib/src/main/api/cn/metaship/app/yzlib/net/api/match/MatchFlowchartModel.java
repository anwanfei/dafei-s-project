package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class MatchFlowchartModel implements Serializable{

    private Integer imgType;
    private String imgUrl;

    public Integer getImgType(){
        return this.imgType;
    }

    public void setImgType(Integer imgType){
        this.imgType = imgType;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

}