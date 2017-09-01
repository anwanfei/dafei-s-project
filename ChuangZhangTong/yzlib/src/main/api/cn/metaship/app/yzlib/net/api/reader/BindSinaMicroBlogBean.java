package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BindSinaMicroBlogBean implements Serializable{

    private String accessToken;
    private String uid;

    public String getAccessToken(){
        return this.accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
    public String getUid(){
        return this.uid;
    }

    public void setUid(String uid){
        this.uid = uid;
    }

}