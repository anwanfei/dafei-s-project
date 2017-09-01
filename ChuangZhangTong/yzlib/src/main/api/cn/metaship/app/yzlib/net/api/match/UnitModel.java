package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class UnitModel implements Serializable{

    /**
    *单元组编码
    */
    private String unitCode;
    /**
    *单元组名称
    */
    private String unitName;

    public String getUnitCode(){
        return this.unitCode;
    }

    public void setUnitCode(String unitCode){
        this.unitCode = unitCode;
    }
    public String getUnitName(){
        return this.unitName;
    }

    public void setUnitName(String unitName){
        this.unitName = unitName;
    }

}