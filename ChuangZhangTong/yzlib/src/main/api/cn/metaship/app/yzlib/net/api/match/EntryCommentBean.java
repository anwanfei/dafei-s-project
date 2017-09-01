package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class EntryCommentBean implements Serializable{

    /**
    *评论用户ID
    */
    private String commentUserId;
    /**
    *被评论个人作品ID
    */
    private String entryId;
    /**
    *评论量
    */
    private Integer commentCount;
    /**
    *评论时间
    */
    private Date commentTime;
    private String id;
    /**
    *被评论的评论id(如果评论作品，置为0)
    */
    private String beCommentId;
    /**
    *评论内容
    */
    private String commentContent;
    /**
    *点赞量
    */
    private Integer likedCount;

    public String getCommentUserId(){
        return this.commentUserId;
    }

    public void setCommentUserId(String commentUserId){
        this.commentUserId = commentUserId;
    }
    public String getEntryId(){
        return this.entryId;
    }

    public void setEntryId(String entryId){
        this.entryId = entryId;
    }
    public Integer getCommentCount(){
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
    }
    public Date getCommentTime(){
        return this.commentTime;
    }

    public void setCommentTime(Date commentTime){
        this.commentTime = commentTime;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getBeCommentId(){
        return this.beCommentId;
    }

    public void setBeCommentId(String beCommentId){
        this.beCommentId = beCommentId;
    }
    public String getCommentContent(){
        return this.commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    public Integer getLikedCount(){
        return this.likedCount;
    }

    public void setLikedCount(Integer likedCount){
        this.likedCount = likedCount;
    }

}