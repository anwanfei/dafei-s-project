package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.Date;

public final class PlatformTeacherDetailsModel implements Serializable{

    private String profession;
    private String idCard;
    private String provinceCode;
    private String cityCode;
    private String provinceName;
    private String name;
    private String countyName;
    private String idCardBackImgId;
    private Date roleExpiryEndTime;
    private String countyCode;
    private Date roleExpiryStartTime;
    private String professionalCredential;
    private String cityName;
    private String idCardFrontImgId;

    public String getProfession(){
        return this.profession;
    }

    public void setProfession(String profession){
        this.profession = profession;
    }
    public String getIdCard(){
        return this.idCard;
    }

    public void setIdCard(String idCard){
        this.idCard = idCard;
    }
    public String getProvinceCode(){
        return this.provinceCode;
    }

    public void setProvinceCode(String provinceCode){
        this.provinceCode = provinceCode;
    }
    public String getCityCode(){
        return this.cityCode;
    }

    public void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public String getProvinceName(){
        return this.provinceName;
    }

    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getCountyName(){
        return this.countyName;
    }

    public void setCountyName(String countyName){
        this.countyName = countyName;
    }
    public String getIdCardBackImgId(){
        return this.idCardBackImgId;
    }

    public void setIdCardBackImgId(String idCardBackImgId){
        this.idCardBackImgId = idCardBackImgId;
    }
    public Date getRoleExpiryEndTime(){
        return this.roleExpiryEndTime;
    }

    public void setRoleExpiryEndTime(Date roleExpiryEndTime){
        this.roleExpiryEndTime = roleExpiryEndTime;
    }
    public String getCountyCode(){
        return this.countyCode;
    }

    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public Date getRoleExpiryStartTime(){
        return this.roleExpiryStartTime;
    }

    public void setRoleExpiryStartTime(Date roleExpiryStartTime){
        this.roleExpiryStartTime = roleExpiryStartTime;
    }
    public String getProfessionalCredential(){
        return this.professionalCredential;
    }

    public void setProfessionalCredential(String professionalCredential){
        this.professionalCredential = professionalCredential;
    }
    public String getCityName(){
        return this.cityName;
    }

    public void setCityName(String cityName){
        this.cityName = cityName;
    }
    public String getIdCardFrontImgId(){
        return this.idCardFrontImgId;
    }

    public void setIdCardFrontImgId(String idCardFrontImgId){
        this.idCardFrontImgId = idCardFrontImgId;
    }

}