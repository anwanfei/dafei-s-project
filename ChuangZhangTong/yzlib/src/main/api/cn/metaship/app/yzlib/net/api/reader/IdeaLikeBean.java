package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;

public final class IdeaLikeBean implements Serializable{

    private String likeCondition;
    /**
    *发布时间
    */
    private Date releasedTime;
    private String id;
    /**
    *感想ID
    */
    private String ideaId;
    /**
    *用户ID
    */
    private String userId;

    public String getLikeCondition(){
        return this.likeCondition;
    }

    public void setLikeCondition(String likeCondition){
        this.likeCondition = likeCondition;
    }
    public Date getReleasedTime(){
        return this.releasedTime;
    }

    public void setReleasedTime(Date releasedTime){
        this.releasedTime = releasedTime;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getIdeaId(){
        return this.ideaId;
    }

    public void setIdeaId(String ideaId){
        this.ideaId = ideaId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

}