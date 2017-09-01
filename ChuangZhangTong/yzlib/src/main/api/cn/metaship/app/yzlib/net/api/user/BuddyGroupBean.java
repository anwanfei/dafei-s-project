package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;

public final class BuddyGroupBean implements Serializable{

    private String groupName;
    private List<GroupMembersModel> groupMembersModels;
    private String userId;
    private String frontGroupId;
    private String id;
    private String behindGroupId;

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public List<GroupMembersModel> getGroupMembersModels(){
        return this.groupMembersModels;
    }

    public void setGroupMembersModels(List<GroupMembersModel> groupMembersModels){
        this.groupMembersModels = groupMembersModels;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getFrontGroupId(){
        return this.frontGroupId;
    }

    public void setFrontGroupId(String frontGroupId){
        this.frontGroupId = frontGroupId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getBehindGroupId(){
        return this.behindGroupId;
    }

    public void setBehindGroupId(String behindGroupId){
        this.behindGroupId = behindGroupId;
    }

}