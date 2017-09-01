package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;
import java.util.Date;

public final class CourseOrderBean implements Serializable{

    private String subCourseId;
    private Date createTime;
    private Integer shouldScoreCount;
    private Integer isVisible;
    private String mainCourseId;
    private Integer factPayCoinCount;
    private Integer orderStatus;
    private String id;
    private Integer shouldPayCoinCount;
    private Date lastUpdateTime;
    private String userId;
    private Integer factPayScoreCount;

    public String getSubCourseId(){
        return this.subCourseId;
    }

    public void setSubCourseId(String subCourseId){
        this.subCourseId = subCourseId;
    }
    public Date getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public Integer getShouldScoreCount(){
        return this.shouldScoreCount;
    }

    public void setShouldScoreCount(Integer shouldScoreCount){
        this.shouldScoreCount = shouldScoreCount;
    }
    public Integer getIsVisible(){
        return this.isVisible;
    }

    public void setIsVisible(Integer isVisible){
        this.isVisible = isVisible;
    }
    public String getMainCourseId(){
        return this.mainCourseId;
    }

    public void setMainCourseId(String mainCourseId){
        this.mainCourseId = mainCourseId;
    }
    public Integer getFactPayCoinCount(){
        return this.factPayCoinCount;
    }

    public void setFactPayCoinCount(Integer factPayCoinCount){
        this.factPayCoinCount = factPayCoinCount;
    }
    public Integer getOrderStatus(){
        return this.orderStatus;
    }

    public void setOrderStatus(Integer orderStatus){
        this.orderStatus = orderStatus;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public Integer getShouldPayCoinCount(){
        return this.shouldPayCoinCount;
    }

    public void setShouldPayCoinCount(Integer shouldPayCoinCount){
        this.shouldPayCoinCount = shouldPayCoinCount;
    }
    public Date getLastUpdateTime(){
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime){
        this.lastUpdateTime = lastUpdateTime;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public Integer getFactPayScoreCount(){
        return this.factPayScoreCount;
    }

    public void setFactPayScoreCount(Integer factPayScoreCount){
        this.factPayScoreCount = factPayScoreCount;
    }

}