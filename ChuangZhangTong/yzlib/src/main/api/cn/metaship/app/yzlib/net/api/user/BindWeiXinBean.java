package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class BindWeiXinBean implements Serializable{

    private String accessToken;
    private String openid;

    public String getAccessToken(){
        return this.accessToken;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }
    public String getOpenid(){
        return this.openid;
    }

    public void setOpenid(String openid){
        this.openid = openid;
    }

}