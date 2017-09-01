package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class BigBangWordAdoptRecordBean implements Serializable{

    private List<String> avatarUrlList;
    private Integer adopterCount;

    public List<String> getAvatarUrlList(){
        return this.avatarUrlList;
    }

    public void setAvatarUrlList(List<String> avatarUrlList){
        this.avatarUrlList = avatarUrlList;
    }
    public Integer getAdopterCount(){
        return this.adopterCount;
    }

    public void setAdopterCount(Integer adopterCount){
        this.adopterCount = adopterCount;
    }

}