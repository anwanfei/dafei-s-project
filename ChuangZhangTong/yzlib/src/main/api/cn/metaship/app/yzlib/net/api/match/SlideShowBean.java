package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class SlideShowBean implements Serializable{

    /**
    *图片地址
    */
    private String imgUrl;
    /**
    *显示序号
    */
    private Long orderNumber;
    /**
    *比赛类型,1-大赛，2-抢票赛，后续的待扩展
    */
    private Byte matchType;
    /**
    *链接地址
    */
    private String linkUrl;
    /**
    *显示状态
    */
    private Byte showStatus;
    private String id;

    public String getImgUrl(){
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public Long getOrderNumber(){
        return this.orderNumber;
    }

    public void setOrderNumber(Long orderNumber){
        this.orderNumber = orderNumber;
    }
    public Byte getMatchType(){
        return this.matchType;
    }

    public void setMatchType(Byte matchType){
        this.matchType = matchType;
    }
    public String getLinkUrl(){
        return this.linkUrl;
    }

    public void setLinkUrl(String linkUrl){
        this.linkUrl = linkUrl;
    }
    public Byte getShowStatus(){
        return this.showStatus;
    }

    public void setShowStatus(Byte showStatus){
        this.showStatus = showStatus;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}