package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class TeacherGiveVoteBean implements Serializable{

    private String recipientId;
    private String teacherId;
    private String recommendId;

    public String getRecipientId(){
        return this.recipientId;
    }

    public void setRecipientId(String recipientId){
        this.recipientId = recipientId;
    }
    public String getTeacherId(){
        return this.teacherId;
    }

    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }
    public String getRecommendId(){
        return this.recommendId;
    }

    public void setRecommendId(String recommendId){
        this.recommendId = recommendId;
    }

}