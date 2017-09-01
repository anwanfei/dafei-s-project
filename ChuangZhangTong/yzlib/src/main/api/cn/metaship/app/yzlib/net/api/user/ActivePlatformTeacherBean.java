package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class ActivePlatformTeacherBean implements Serializable{

    private String name;
    private String provinceName;
    private String idCardBackImgId;
    private String countyName;
    private String countyCode;
    private String cityName;
    private String idCardFrontImgId;
    private String professionalCredential;
    private String userId;
    private String profession;
    private String idCard;
    private String provinceCode;
    private String cityCode;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getProvinceName(){
        return this.provinceName;
    }

    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
    }
    public String getIdCardBackImgId(){
        return this.idCardBackImgId;
    }

    public void setIdCardBackImgId(String idCardBackImgId){
        this.idCardBackImgId = idCardBackImgId;
    }
    public String getCountyName(){
        return this.countyName;
    }

    public void setCountyName(String countyName){
        this.countyName = countyName;
    }
    public String getCountyCode(){
        return this.countyCode;
    }

    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
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
    public String getProfessionalCredential(){
        return this.professionalCredential;
    }

    public void setProfessionalCredential(String professionalCredential){
        this.professionalCredential = professionalCredential;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
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

}