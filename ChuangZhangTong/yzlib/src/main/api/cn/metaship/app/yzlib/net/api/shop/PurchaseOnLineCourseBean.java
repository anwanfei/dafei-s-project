package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;

public final class PurchaseOnLineCourseBean implements Serializable{

    private String subCourseId;
    private String userId;
    private Integer factPayScoreCount;
    private Integer shouldPayCoinCount;
    private String mainCourseId;
    private Integer factPayCoinCount;
    private Integer shouldPayScoreCount;

    public String getSubCourseId(){
        return this.subCourseId;
    }

    public void setSubCourseId(String subCourseId){
        this.subCourseId = subCourseId;
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
    public Integer getShouldPayCoinCount(){
        return this.shouldPayCoinCount;
    }

    public void setShouldPayCoinCount(Integer shouldPayCoinCount){
        this.shouldPayCoinCount = shouldPayCoinCount;
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
    public Integer getShouldPayScoreCount(){
        return this.shouldPayScoreCount;
    }

    public void setShouldPayScoreCount(Integer shouldPayScoreCount){
        this.shouldPayScoreCount = shouldPayScoreCount;
    }

}