package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class EntryPictureModel implements Serializable{

    private String pictureUrl;
    private Integer index;

    public String getPictureUrl(){
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl){
        this.pictureUrl = pictureUrl;
    }
    public Integer getIndex(){
        return this.index;
    }

    public void setIndex(Integer index){
        this.index = index;
    }

}