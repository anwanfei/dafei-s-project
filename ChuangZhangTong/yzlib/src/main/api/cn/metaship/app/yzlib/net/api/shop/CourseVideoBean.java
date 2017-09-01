package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;

public final class CourseVideoBean implements Serializable{

    private String videoUlrId;
    private String subCourseId;
    private String temporaryVideoUrl;
    private Integer price;

    public String getVideoUlrId(){
        return this.videoUlrId;
    }

    public void setVideoUlrId(String videoUlrId){
        this.videoUlrId = videoUlrId;
    }
    public String getSubCourseId(){
        return this.subCourseId;
    }

    public void setSubCourseId(String subCourseId){
        this.subCourseId = subCourseId;
    }
    public String getTemporaryVideoUrl(){
        return this.temporaryVideoUrl;
    }

    public void setTemporaryVideoUrl(String temporaryVideoUrl){
        this.temporaryVideoUrl = temporaryVideoUrl;
    }
    public Integer getPrice(){
        return this.price;
    }

    public void setPrice(Integer price){
        this.price = price;
    }

}