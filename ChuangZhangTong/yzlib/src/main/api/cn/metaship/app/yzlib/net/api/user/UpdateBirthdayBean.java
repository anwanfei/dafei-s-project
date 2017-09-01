package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class UpdateBirthdayBean implements Serializable{

    private String userId;
    private String birthday;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getBirthday(){
        return this.birthday;
    }

    public void setBirthday(String birthday){
        this.birthday = birthday;
    }

}