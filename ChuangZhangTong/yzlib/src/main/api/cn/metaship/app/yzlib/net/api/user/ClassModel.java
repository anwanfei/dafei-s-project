package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class ClassModel implements Serializable{

    private String className;

    public String getClassName(){
        return this.className;
    }

    public void setClassName(String className){
        this.className = className;
    }

}