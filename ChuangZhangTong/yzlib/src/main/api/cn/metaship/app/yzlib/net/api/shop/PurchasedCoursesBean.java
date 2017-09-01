package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;
import java.util.Date;

public final class PurchasedCoursesBean implements Serializable{

    private String subCourseId;
    private String mainCourseId;
    private Date purchaseTime;
    private String id;
    private String userId;

    public String getSubCourseId(){
        return this.subCourseId;
    }

    public void setSubCourseId(String subCourseId){
        this.subCourseId = subCourseId;
    }
    public String getMainCourseId(){
        return this.mainCourseId;
    }

    public void setMainCourseId(String mainCourseId){
        this.mainCourseId = mainCourseId;
    }
    public Date getPurchaseTime(){
        return this.purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime){
        this.purchaseTime = purchaseTime;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

}