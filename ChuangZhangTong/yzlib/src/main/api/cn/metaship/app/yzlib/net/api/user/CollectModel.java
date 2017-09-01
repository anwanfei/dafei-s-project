package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.Date;

public final class CollectModel implements Serializable{

    private Integer collectType;
    private Date collectTime;
    private String soundUrl;
    private String authorId;
    private String title;
    private String userId;
    private String bookImgUrl;
    private String id;
    private String collectObjectId;

    public Integer getCollectType(){
        return this.collectType;
    }

    public void setCollectType(Integer collectType){
        this.collectType = collectType;
    }
    public Date getCollectTime(){
        return this.collectTime;
    }

    public void setCollectTime(Date collectTime){
        this.collectTime = collectTime;
    }
    public String getSoundUrl(){
        return this.soundUrl;
    }

    public void setSoundUrl(String soundUrl){
        this.soundUrl = soundUrl;
    }
    public String getAuthorId(){
        return this.authorId;
    }

    public void setAuthorId(String authorId){
        this.authorId = authorId;
    }
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getBookImgUrl(){
        return this.bookImgUrl;
    }

    public void setBookImgUrl(String bookImgUrl){
        this.bookImgUrl = bookImgUrl;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getCollectObjectId(){
        return this.collectObjectId;
    }

    public void setCollectObjectId(String collectObjectId){
        this.collectObjectId = collectObjectId;
    }

}