package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class UpdateGenderBean implements Serializable{

    private Integer gender;
    private String userId;

    public Integer getGender(){
        return this.gender;
    }

    public void setGender(Integer gender){
        this.gender = gender;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

}