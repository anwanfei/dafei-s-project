package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;
import java.util.Date;

public final class CourseCommentResultBean implements Serializable{

    private String commentContent;
    private Integer likeCount;
    private String mainCourseId;
    private String commentUserId;
    private Date commentTime;
    private String commentUserAvatar;
    private String commentUserNickname;
    private Integer isLike;

    public String getCommentContent(){
        return this.commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getMainCourseId(){
        return this.mainCourseId;
    }

    public void setMainCourseId(String mainCourseId){
        this.mainCourseId = mainCourseId;
    }
    public String getCommentUserId(){
        return this.commentUserId;
    }

    public void setCommentUserId(String commentUserId){
        this.commentUserId = commentUserId;
    }
    public Date getCommentTime(){
        return this.commentTime;
    }

    public void setCommentTime(Date commentTime){
        this.commentTime = commentTime;
    }
    public String getCommentUserAvatar(){
        return this.commentUserAvatar;
    }

    public void setCommentUserAvatar(String commentUserAvatar){
        this.commentUserAvatar = commentUserAvatar;
    }
    public String getCommentUserNickname(){
        return this.commentUserNickname;
    }

    public void setCommentUserNickname(String commentUserNickname){
        this.commentUserNickname = commentUserNickname;
    }
    public Integer getIsLike(){
        return this.isLike;
    }

    public void setIsLike(Integer isLike){
        this.isLike = isLike;
    }

}