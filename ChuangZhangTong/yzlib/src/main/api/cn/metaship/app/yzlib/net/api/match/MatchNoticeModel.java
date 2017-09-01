package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class MatchNoticeModel implements Serializable{

    /**
    *图片地址
    */
    private String imageUrl;
    /**
    *发布时间
    */
    private Date publishTime;
    /**
    *公告标题
    */
    private String title;
    /**
    *发布内容
    */
    private String content;
    /**
    *状态,未发布-1，已发布-2
    */
    private Integer state;

    public String getImageUrl(){
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public Date getPublishTime(){
        return this.publishTime;
    }

    public void setPublishTime(Date publishTime){
        this.publishTime = publishTime;
    }
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }
    public Integer getState(){
        return this.state;
    }

    public void setState(Integer state){
        this.state = state;
    }

}