package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ConstantConfigurationModel implements Serializable{

    private Date createTime;
    private String constantType;
    private String constantKey;
    private String value;
    private String description;

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public String getConstantType(){
        return this.constantType;
    }

    public void setConstantType(String constantType){
        this.constantType = constantType;
    }
    public String getConstantKey(){
        return this.constantKey;
    }

    public void setConstantKey(String constantKey){
        this.constantKey = constantKey;
    }
    public String getValue(){
        return this.value;
    }

    public void setValue(String value){
        this.value = value;
    }
    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}