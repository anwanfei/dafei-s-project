package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;
import java.util.List;

public final class SubCourseBean implements Serializable{

    private Integer freeStatus;
    private String courseDescription;
    private Integer isPurchased;
    private String videoUrlId;
    private Integer commentCount;
    private Integer playCount;
    private Integer playDuration;
    private String subCourseName;
    private String mainCourseId;
    private Integer normalPrice;
    private Integer vipPrice;
    private List<String> coursePictureUrls;
    private String id;

    public Integer getFreeStatus(){
        return this.freeStatus;
    }

    public void setFreeStatus(Integer freeStatus){
        this.freeStatus = freeStatus;
    }
    public String getCourseDescription(){
        return this.courseDescription;
    }

    public void setCourseDescription(String courseDescription){
        this.courseDescription = courseDescription;
    }
    public Integer getIsPurchased(){
        return this.isPurchased;
    }

    public void setIsPurchased(Integer isPurchased){
        this.isPurchased = isPurchased;
    }
    public String getVideoUrlId(){
        return this.videoUrlId;
    }

    public void setVideoUrlId(String videoUrlId){
        this.videoUrlId = videoUrlId;
    }
    public Integer getCommentCount(){
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
    }
    public Integer getPlayCount(){
        return this.playCount;
    }

    public void setPlayCount(Integer playCount){
        this.playCount = playCount;
    }
    public Integer getPlayDuration(){
        return this.playDuration;
    }

    public void setPlayDuration(Integer playDuration){
        this.playDuration = playDuration;
    }
    public String getSubCourseName(){
        return this.subCourseName;
    }

    public void setSubCourseName(String subCourseName){
        this.subCourseName = subCourseName;
    }
    public String getMainCourseId(){
        return this.mainCourseId;
    }

    public void setMainCourseId(String mainCourseId){
        this.mainCourseId = mainCourseId;
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
    public List<String> getCoursePictureUrls(){
        return this.coursePictureUrls;
    }

    public void setCoursePictureUrls(List<String> coursePictureUrls){
        this.coursePictureUrls = coursePictureUrls;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}