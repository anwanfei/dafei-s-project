package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class LoginBean implements Serializable{

    private String userName;
    private String password;

    public String getUserName(){
        return this.userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }

}