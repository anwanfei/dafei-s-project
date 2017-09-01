package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ScrambleForwardCommentLogBean implements Serializable{

    /**
    *评论人ID
    */
    private String commentUserId;
    /**
    *点赞量
    */
    private Integer likeCount;
    /**
    *评论内容
    */
    private String commentContent;
    /**
    *被评论的ID-如果是对评论进行回复的话，则为被评论ID。如果是对抢票赛转发记录进行评论的话， 则为0
    */
    private String beCommentId;
    /**
    *评论时间
    */
    private Date commentTime;
    /**
    *转发作品作者ID
    */
    private String scramblePropositionAuthorId;
    /**
    *评论量
    */
    private Integer commentCount;
    /**
    *抢票赛转发记录的ID
    */
    private String scrambleForwardId;
    private String id;
    private Byte isLike;
    /**
    *评论发表人头像url
    */
    private String avatarUrl;
    /**
    *评论发表人昵称
    */
    private String nickName;
    /**
    *抢票赛作品ID
    */
    private String scrambleId;

    public String getCommentUserId(){
        return this.commentUserId;
    }

    public void setCommentUserId(String commentUserId){
        this.commentUserId = commentUserId;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getCommentContent(){
        return this.commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    public String getBeCommentId(){
        return this.beCommentId;
    }

    public void setBeCommentId(String beCommentId){
        this.beCommentId = beCommentId;
    }
    public Date getCommentTime(){
        return this.commentTime;
    }

    public void setCommentTime(Date commentTime){
        this.commentTime = commentTime;
    }
    public String getScramblePropositionAuthorId(){
        return this.scramblePropositionAuthorId;
    }

    public void setScramblePropositionAuthorId(String scramblePropositionAuthorId){
        this.scramblePropositionAuthorId = scramblePropositionAuthorId;
    }
    public Integer getCommentCount(){
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
    }
    public String getScrambleForwardId(){
        return this.scrambleForwardId;
    }

    public void setScrambleForwardId(String scrambleForwardId){
        this.scrambleForwardId = scrambleForwardId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
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
    public String getNickName(){
        return this.nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public String getScrambleId(){
        return this.scrambleId;
    }

    public void setScrambleId(String scrambleId){
        this.scrambleId = scrambleId;
    }

}