package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class ScramSecondForwardParamBean implements Serializable{

    /**
    *被转发ID
    */
    private String beForwardId;
    /**
    *转发描述
    */
    private String forwardDescription;
    /**
    *转发人ID
    */
    private String forwardUserId;

    public String getBeForwardId(){
        return this.beForwardId;
    }

    public void setBeForwardId(String beForwardId){
        this.beForwardId = beForwardId;
    }
    public String getForwardDescription(){
        return this.forwardDescription;
    }

    public void setForwardDescription(String forwardDescription){
        this.forwardDescription = forwardDescription;
    }
    public String getForwardUserId(){
        return this.forwardUserId;
    }

    public void setForwardUserId(String forwardUserId){
        this.forwardUserId = forwardUserId;
    }

}