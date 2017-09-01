package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class AccountCoinNormalPostBean implements Serializable{

    private String userId;
    private Integer coinCount;
    private String orderNumber;
    private String notes;

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public Integer getCoinCount(){
        return this.coinCount;
    }

    public void setCoinCount(Integer coinCount){
        this.coinCount = coinCount;
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