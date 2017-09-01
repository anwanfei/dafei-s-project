package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class EducationLevelModel implements Serializable{

    private String levelName;
    private String id;

    public String getLevelName(){
        return this.levelName;
    }

    public void setLevelName(String levelName){
        this.levelName = levelName;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}