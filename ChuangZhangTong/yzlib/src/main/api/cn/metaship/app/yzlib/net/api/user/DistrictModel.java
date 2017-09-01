package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;

public final class DistrictModel implements Serializable{

    /**
    *城市名称
    */
    private String districtName;
    /**
    *城区的子城区列表,如{'北京','110000',[{'北京','110100',['朝阳区','110105']}]}
    */
    private List<DistrictBean> children;
    /**
    *城市编码
    */
    private String districtCode;
    private String id;

    public String getDistrictName(){
        return this.districtName;
    }

    public void setDistrictName(String districtName){
        this.districtName = districtName;
    }
    public List<DistrictBean> getChildren(){
        return this.children;
    }

    public void setChildren(List<DistrictBean> children){
        this.children = children;
    }
    public String getDistrictCode(){
        return this.districtCode;
    }

    public void setDistrictCode(String districtCode){
        this.districtCode = districtCode;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}