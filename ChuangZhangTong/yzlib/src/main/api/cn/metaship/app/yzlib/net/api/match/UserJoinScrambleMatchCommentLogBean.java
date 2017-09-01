package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class UserJoinScrambleMatchCommentLogBean implements Serializable{

    /**
    *点赞数
    */
    private Integer likeCount;
    /**
    *如果是评论动态，则为0;如果是评论评论，则为所要评论的评论ID
    */
    private String beCommentId;
    /**
    *评论内容
    */
    private String commentContent;
    /**
    *评论发表人
    */
    private String userId;
    /**
    *评论数量
    */
    private Integer commentCount;
    private String id;
    private Byte isLike;
    /**
    *评论创建时间
    */
    private Date createTime;
    /**
    *评论发表人头像url
    */
    private String avatarUrl;
    /**
    *评论发表人昵称
    */
    private String nickName;
    /**
    *抢票赛填词动态ID
    */
    private String scrambleId;

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
    public String getCommentContent(){
        return this.commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public Integer getCommentCount(){
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
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
    public Date getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
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