package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ForwardEntryCommentResultBean implements Serializable{

    /**
    *转发记录ID
    */
    private String forwardEntryId;
    /**
    *用户ID
    */
    private String userId;
    /**
    *评论内容
    */
    private String comment;
    /**
    *点赞量
    */
    private Integer likeCount;
    /**
    *评论Id
    */
    private String commentId;
    /**
    *用户昵称
    */
    private String nickname;
    /**
    *用户头像URL
    */
    private String avatarUrl;
    private Byte isLike;
    /**
    *发布时间
    */
    private Date releaseTime;
    /**
    *回复量
    */
    private Integer replyCount;

    public String getForwardEntryId(){
        return this.forwardEntryId;
    }

    public void setForwardEntryId(String forwardEntryId){
        this.forwardEntryId = forwardEntryId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getComment(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getCommentId(){
        return this.commentId;
    }

    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public Byte getIsLike(){
        return this.isLike;
    }

    public void setIsLike(Byte isLike){
        this.isLike = isLike;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public Integer getReplyCount(){
        return this.replyCount;
    }

    public void setReplyCount(Integer replyCount){
        this.replyCount = replyCount;
    }

}