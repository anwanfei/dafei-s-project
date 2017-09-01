package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class MatchZoneModel implements Serializable{

    /**
    *大赛编码
    */
    private String zoneCode;
    /**
    *大赛名称
    */
    private String zoneName;

    public String getZoneCode(){
        return this.zoneCode;
    }

    public void setZoneCode(String zoneCode){
        this.zoneCode = zoneCode;
    }
    public String getZoneName(){
        return this.zoneName;
    }

    public void setZoneName(String zoneName){
        this.zoneName = zoneName;
    }

}