package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BindQQBean implements Serializable{

    private String uid;
    private String accessToken;

    public String getUid(){
        return this.uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }
    public String getAccessToken(){
        return this.accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

}