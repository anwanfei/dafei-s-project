package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;

public final class IdeaLikeModel implements Serializable{

    /**
    *用户ID
    */
    private String userId;
    /**
    *感想ID
    */
    private String ideaId;
    /**
    *发布时间
    */
    private Date releasedTime;
    /**
    *用户感想态度(点赞或点踩状态)
    */
    private Byte likeCondition;
    private String id;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getIdeaId(){
        return this.ideaId;
    }

    public void setIdeaId(String ideaId){
        this.ideaId = ideaId;
    }
    public Date getReleasedTime(){
        return this.releasedTime;
    }

    public void setReleasedTime(Date releasedTime){
        this.releasedTime = releasedTime;
    }
    public Byte getLikeCondition(){
        return this.likeCondition;
    }

    public void setLikeCondition(Byte likeCondition){
        this.likeCondition = likeCondition;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}