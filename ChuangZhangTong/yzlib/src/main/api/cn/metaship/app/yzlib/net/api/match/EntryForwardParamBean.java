package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class EntryForwardParamBean implements Serializable{

    /**
    *被转发文章ID
    */
    private String beForwardEntryId;
    /**
    *转发描述
    */
    private String forwardDescription;
    /**
    *转发人ID
    */
    private String forwardUserId;

    public String getBeForwardEntryId(){
        return this.beForwardEntryId;
    }

    public void setBeForwardEntryId(String beForwardEntryId){
        this.beForwardEntryId = beForwardEntryId;
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