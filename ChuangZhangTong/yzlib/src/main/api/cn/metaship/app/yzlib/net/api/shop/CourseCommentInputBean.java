package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;

public final class CourseCommentInputBean implements Serializable{

    private String commentUserId;
    private String commentContent;
    private String mainCourseId;
    private Integer channel;

    public String getCommentUserId(){
        return this.commentUserId;
    }

    public void setCommentUserId(String commentUserId){
        this.commentUserId = commentUserId;
    }
    public String getCommentContent(){
        return this.commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    public String getMainCourseId(){
        return this.mainCourseId;
    }

    public void setMainCourseId(String mainCourseId){
        this.mainCourseId = mainCourseId;
    }
    public Integer getChannel(){
        return this.channel;
    }

    public void setChannel(Integer channel){
        this.channel = channel;
    }

}