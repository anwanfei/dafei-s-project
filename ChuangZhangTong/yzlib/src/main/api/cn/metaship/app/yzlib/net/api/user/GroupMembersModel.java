package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class GroupMembersModel implements Serializable{

    private String friendUserId;
    private String notesName;

    public String getFriendUserId(){
        return this.friendUserId;
    }

    public void setFriendUserId(String friendUserId){
        this.friendUserId = friendUserId;
    }
    public String getNotesName(){
        return this.notesName;
    }

    public void setNotesName(String notesName){
        this.notesName = notesName;
    }

}