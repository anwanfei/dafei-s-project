package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ForwardEntryCommentReplyBean implements Serializable{

    private Byte isLike;
    /**
    *用户头像URL
    */
    private String avatarUrl;
    /**
    *回复量
    */
    private Integer replyCount;
    /**
    *发布时间
    */
    private Date releaseTime;
    /**
    *用户ID
    */
    private String userId;
    /**
    *转发记录ID
    */
    private String forwardEntryId;
    /**
    *回复ID
    */
    private String forwardEntryReplyId;
    /**
    *用户昵称
    */
    private String nickname;
    /**
    *评论ID
    */
    private String commentId;
    /**
    *点赞量
    */
    private Integer likeCount;
    /**
    *被回复记录ID
    */
    private String beCommentId;
    /**
    *回复内容
    */
    private String replyContent;

    public Byte getIsLike(){
        return this.isLike;
    }

    public void setIsLike(Byte isLike){
        this.isLike = isLike;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public Integer getReplyCount(){
        return this.replyCount;
    }

    public void setReplyCount(Integer replyCount){
        this.replyCount = replyCount;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getForwardEntryId(){
        return this.forwardEntryId;
    }

    public void setForwardEntryId(String forwardEntryId){
        this.forwardEntryId = forwardEntryId;
    }
    public String getForwardEntryReplyId(){
        return this.forwardEntryReplyId;
    }

    public void setForwardEntryReplyId(String forwardEntryReplyId){
        this.forwardEntryReplyId = forwardEntryReplyId;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getCommentId(){
        return this.commentId;
    }

    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getBeCommentId(){
        return this.beCommentId;
    }

    public void setBeCommentId(String beCommentId){
        this.beCommentId = beCommentId;
    }
    public String getReplyContent(){
        return this.replyContent;
    }

    public void setReplyContent(String replyContent){
        this.replyContent = replyContent;
    }

}