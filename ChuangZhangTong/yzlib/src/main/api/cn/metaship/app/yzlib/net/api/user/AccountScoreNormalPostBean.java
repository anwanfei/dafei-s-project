package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class AccountScoreNormalPostBean implements Serializable{

    private String userId;
    private Integer scoreCount;
    private String orderNumber;
    private String notes;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public Integer getScoreCount(){
        return this.scoreCount;
    }

    public void setScoreCount(Integer scoreCount){
        this.scoreCount = scoreCount;
    }
    public String getOrderNumber(){
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber){
        this.orderNumber = orderNumber;
    }
    public String getNotes(){
        return this.notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

}