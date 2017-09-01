package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class AddBuddyGroupBean implements Serializable{

    private String groupName;
    private String userId;

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

}