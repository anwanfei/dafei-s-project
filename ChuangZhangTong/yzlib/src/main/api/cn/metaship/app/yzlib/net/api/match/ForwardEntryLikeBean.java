package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ForwardEntryLikeBean implements Serializable{

    /**
    *点赞渠道
    */
    private Integer likeChannel;
    /**
    *转发的作品的作者ID
    */
    private String entryAuthorId;
    /**
    *点赞用户ip地址
    */
    private String likeUserIp;
    /**
    *动作，1-点赞，2-取消
    */
    private Byte action;
    private String id;
    /**
    *转发记录ID
    */
    private String forwardEntryId;
    /**
    *转发的作品ID
    */
    private String entryId;
    /**
    *点赞时间
    */
    private Date likeTime;
    /**
    *点赞用户ID
    */
    private String likeUserId;

    public Integer getLikeChannel(){
        return this.likeChannel;
    }

    public void setLikeChannel(Integer likeChannel){
        this.likeChannel = likeChannel;
    }
    public String getEntryAuthorId(){
        return this.entryAuthorId;
    }

    public void setEntryAuthorId(String entryAuthorId){
        this.entryAuthorId = entryAuthorId;
    }
    public String getLikeUserIp(){
        return this.likeUserIp;
    }

    public void setLikeUserIp(String likeUserIp){
        this.likeUserIp = likeUserIp;
    }
    public Byte getAction(){
        return this.action;
    }

    public void setAction(Byte action){
        this.action = action;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getForwardEntryId(){
        return this.forwardEntryId;
    }

    public void setForwardEntryId(String forwardEntryId){
        this.forwardEntryId = forwardEntryId;
    }
    public String getEntryId(){
        return this.entryId;
    }

    public void setEntryId(String entryId){
        this.entryId = entryId;
    }
    public Date getLikeTime(){
        return this.likeTime;
    }

    public void setLikeTime(Date likeTime){
        this.likeTime = likeTime;
    }
    public String getLikeUserId(){
        return this.likeUserId;
    }

    public void setLikeUserId(String likeUserId){
        this.likeUserId = likeUserId;
    }

}