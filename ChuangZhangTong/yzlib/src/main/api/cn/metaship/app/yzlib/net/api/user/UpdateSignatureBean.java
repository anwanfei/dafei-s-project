package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class UpdateSignatureBean implements Serializable{

    private String userId;
    private String signature;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getSignature(){
        return this.signature;
    }

    public void setSignature(String signature){
        this.signature = signature;
    }

}