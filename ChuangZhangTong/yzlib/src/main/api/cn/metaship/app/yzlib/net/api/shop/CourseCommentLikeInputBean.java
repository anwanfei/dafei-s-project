package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;

public final class CourseCommentLikeInputBean implements Serializable{

    private String ip;
    private String courseId;
    private Integer channel;
    private String commentId;
    private Integer action;

    public String getIp(){
        return this.ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }
    public String getCourseId(){
        return this.courseId;
    }

    public void setCourseId(String courseId){
        this.courseId = courseId;
    }
    public Integer getChannel(){
        return this.channel;
    }

    public void setChannel(Integer channel){
        this.channel = channel;
    }
    public String getCommentId(){
        return this.commentId;
    }

    public void setCommentId(String commentId){
        this.commentId = commentId;
    }
    public Integer getAction(){
        return this.action;
    }

    public void setAction(Integer action){
        this.action = action;
    }

}