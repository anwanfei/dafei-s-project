package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;

public final class LinesIdeaModel implements Serializable{

    /**
    *感想内容
    */
    private String idea;
    /**
    *划线对象ID
    */
    private String lineContentId;
    /**
    *书籍的ID
    */
    private String bookId;
    /**
    *用户ID
    */
    private String userId;
    /**
    *当前感想点赞数量
    */
    private Integer likeCount;
    private String id;
    /**
    *感想发布时间
    */
    private Date releasedTime;
    /**
    *当前感想点踩数量
    */
    private Integer dislikeCount;

    public String getIdea(){
        return this.idea;
    }

    public void setIdea(String idea){
        this.idea = idea;
    }
    public String getLineContentId(){
        return this.lineContentId;
    }

    public void setLineContentId(String lineContentId){
        this.lineContentId = lineContentId;
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
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public Date getReleasedTime(){
        return this.releasedTime;
    }

    public void setReleasedTime(Date releasedTime){
        this.releasedTime = releasedTime;
    }
    public Integer getDislikeCount(){
        return this.dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount){
        this.dislikeCount = dislikeCount;
    }

}