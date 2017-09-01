package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class ScramForwardParamBean implements Serializable{

    /**
    *转发描述
    */
    private String forwardDescription;
    /**
    *转发人ID
    */
    private String forwardUserId;
    /**
    *被转发抢票赛作品ID
    */
    private String beForwardScramEntryId;

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
    public String getBeForwardScramEntryId(){
        return this.beForwardScramEntryId;
    }

    public void setBeForwardScramEntryId(String beForwardScramEntryId){
        this.beForwardScramEntryId = beForwardScramEntryId;
    }

}