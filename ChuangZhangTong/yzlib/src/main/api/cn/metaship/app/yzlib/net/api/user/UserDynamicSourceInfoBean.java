package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class UserDynamicSourceInfoBean implements Serializable{

    private String forwardUserName;
    private String forwardId;
    private String forwardUserId;
    private String forwardDescription;

    public String getForwardUserName(){
        return this.forwardUserName;
    }

    public void setForwardUserName(String forwardUserName){
        this.forwardUserName = forwardUserName;
    }
    public String getForwardId(){
        return this.forwardId;
    }

    public void setForwardId(String forwardId){
        this.forwardId = forwardId;
    }
    public String getForwardUserId(){
        return this.forwardUserId;
    }

    public void setForwardUserId(String forwardUserId){
        this.forwardUserId = forwardUserId;
    }
    public String getForwardDescription(){
        return this.forwardDescription;
    }

    public void setForwardDescription(String forwardDescription){
        this.forwardDescription = forwardDescription;
    }

}