package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;
import java.util.Date;

public final class MainCourseBean implements Serializable{

    private Integer albumStatus;
    private Date releasedTime;
    private Integer normalPrice;
    private Integer vipPrice;
    private String courseAuthorId;
    private String courseHeadPictureUrl;
    private String courseName;
    private Integer playCount;
    private String id;
    private String courseCategoryCode;
    private Integer freeStatus;

    public Integer getAlbumStatus(){
        return this.albumStatus;
    }

    public void setAlbumStatus(Integer albumStatus){
        this.albumStatus = albumStatus;
    }
    public Date getReleasedTime(){
        return this.releasedTime;
    }

    public void setReleasedTime(Date releasedTime){
        this.releasedTime = releasedTime;
    }
    public Integer getNormalPrice(){
        return this.normalPrice;
    }

    public void setNormalPrice(Integer normalPrice){
        this.normalPrice = normalPrice;
    }
    public Integer getVipPrice(){
        return this.vipPrice;
    }

    public void setVipPrice(Integer vipPrice){
        this.vipPrice = vipPrice;
    }
    public String getCourseAuthorId(){
        return this.courseAuthorId;
    }

    public void setCourseAuthorId(String courseAuthorId){
        this.courseAuthorId = courseAuthorId;
    }
    public String getCourseHeadPictureUrl(){
        return this.courseHeadPictureUrl;
    }

    public void setCourseHeadPictureUrl(String courseHeadPictureUrl){
        this.courseHeadPictureUrl = courseHeadPictureUrl;
    }
    public String getCourseName(){
        return this.courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }
    public Integer getPlayCount(){
        return this.playCount;
    }

    public void setPlayCount(Integer playCount){
        this.playCount = playCount;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getCourseCategoryCode(){
        return this.courseCategoryCode;
    }

    public void setCourseCategoryCode(String courseCategoryCode){
        this.courseCategoryCode = courseCategoryCode;
    }
    public Integer getFreeStatus(){
        return this.freeStatus;
    }

    public void setFreeStatus(Integer freeStatus){
        this.freeStatus = freeStatus;
    }

}