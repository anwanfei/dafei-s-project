package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class ForwardEntryCancelLikeBean implements Serializable{

    private String userId;
    private String forwardEntryId;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getForwardEntryId(){
        return this.forwardEntryId;
    }

    public void setForwardEntryId(String forwardEntryId){
        this.forwardEntryId = forwardEntryId;
    }

}