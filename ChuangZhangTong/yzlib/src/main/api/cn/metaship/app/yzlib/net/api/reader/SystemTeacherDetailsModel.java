package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class SystemTeacherDetailsModel implements Serializable{

    private String countyName;
    private String idCardBackImgId;
    private String schoolName;
    private String provinceName;
    private String name;
    private String schoolId;
    private String cityName;
    private String idCardFrontImgId;
    private Date roleExpiryEndTime;
    private String countyCode;
    private String teacherProfessionalCredential;
    private Date roleExpiryStartTime;
    private List<String> classNameList;
    private String idCard;
    private String provinceCode;
    private String cityCode;
    private String gradeName;

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
    public String getSchoolName(){
        return this.schoolName;
    }

    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
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
    public String getSchoolId(){
        return this.schoolId;
    }

    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
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
    public String getTeacherProfessionalCredential(){
        return this.teacherProfessionalCredential;
    }

    public void setTeacherProfessionalCredential(String teacherProfessionalCredential){
        this.teacherProfessionalCredential = teacherProfessionalCredential;
    }
    public Date getRoleExpiryStartTime(){
        return this.roleExpiryStartTime;
    }

    public void setRoleExpiryStartTime(Date roleExpiryStartTime){
        this.roleExpiryStartTime = roleExpiryStartTime;
    }
    public List<String> getClassNameList(){
        return this.classNameList;
    }

    public void setClassNameList(List<String> classNameList){
        this.classNameList = classNameList;
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
    public String getGradeName(){
        return this.gradeName;
    }

    public void setGradeName(String gradeName){
        this.gradeName = gradeName;
    }

}