package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public final class BookCommentBean implements Serializable{

    /**
    *书籍点评发布时间
    */
    private Date releaseTime;
    /**
    *点赞与点踩的状态与对应的用户绑定
    */
    private Map<String,Byte> condition;
    /**
    *点评星级
    */
    private Integer commentStarCount;
    /**
    *点赞数量
    */
    private Integer likeCount;
    /**
    *评论内容
    */
    private String comment;
    /**
    *点踩数量
    */
    private Integer dislikeCount;
    private String id;
    /**
    *书籍ID
    */
    private String bookId;
    /**
    *点评用户ID
    */
    private String userId;

    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public Map<String,Byte> getCondition(){
        return this.condition;
    }

    public void setCondition(Map<String,Byte> condition){
        this.condition = condition;
    }
    public Integer getCommentStarCount(){
        return this.commentStarCount;
    }

    public void setCommentStarCount(Integer commentStarCount){
        this.commentStarCount = commentStarCount;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getComment(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
    public Integer getDislikeCount(){
        return this.dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount){
        this.dislikeCount = dislikeCount;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

}