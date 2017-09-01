package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class UserDynamicForwardBean implements Serializable{

    /**
    *转发用户ID
    */
    private String forwardUserId;
    /**
    *转发描述
    */
    private String forwardDescription;
    /**
    *动态类型（1新作、2新作转发、3抢票赛作品、4接力赛作品、5朗读赛作品、6表演赛作品7抢票赛转发）
    */
    private Integer dynamicType;
    /**
    *被转发id，如果是原文，即作品id，如果是转发即转发id
    */
    private String beForwardId;

    public String getForwardUserId(){
        return this.forwardUserId;
    }

    public void setForwardUserId(String forwardUserId){
        this.forwardUserId = forwardUserId;
    }
    public String getForwardDescription(){
        return this.forwardDescription;
    }

    public void setForwardDescription(String forwardDescription){
        this.forwardDescription = forwardDescription;
    }
    public Integer getDynamicType(){
        return this.dynamicType;
    }

    public void setDynamicType(Integer dynamicType){
        this.dynamicType = dynamicType;
    }
    public String getBeForwardId(){
        return this.beForwardId;
    }

    public void setBeForwardId(String beForwardId){
        this.beForwardId = beForwardId;
    }

}