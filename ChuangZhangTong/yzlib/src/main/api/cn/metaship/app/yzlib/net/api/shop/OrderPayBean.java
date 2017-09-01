package cn.metaship.app.yzlib.net.api.shop;

import java.io.Serializable;

public final class OrderPayBean implements Serializable{

    private Integer scoreCount;
    private String orderId;
    private Integer coinCount;

    public Integer getScoreCount(){
        return this.scoreCount;
    }

    public void setScoreCount(Integer scoreCount){
        this.scoreCount = scoreCount;
    }
    public String getOrderId(){
        return this.orderId;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
    public Integer getCoinCount(){
        return this.coinCount;
    }

    public void setCoinCount(Integer coinCount){
        this.coinCount = coinCount;
    }

}