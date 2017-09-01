package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class ActiveStudentBean implements Serializable{

    /**
    *学生证照片ID
    */
    private String eduIdPhotoId;
    private String userId;
    /**
    *班级名称
    */
    private String className;
    /**
    *学生姓名
    */
    private String fullName;
    /**
    *教育级别(小学、初中、高中)
    */
    private String educationLevelName;
    /**
    *身份证号
    */
    private String idCard;
    /**
    *学校所在省份代码
    */
    private String schoolProvinceCode;
    /**
    *年级名称
    */
    private String gradeName;
    /**
    *赛区编码
    */
    private String zoneCode;
    /**
    *联系方式
    */
    private String contactMethod;
    /**
    *学校所在县的名称
    */
    private String countyName;
    private String groupCode;
    /**
    *学校名称
    */
    private String schoolName;
    /**
    *学校所在市代码
    */
    private String schoolCityCode;
    /**
    *学校所在省的名称
    */
    private String provinceName;
    /**
    *学校ID
    */
    private String schoolId;
    /**
    *学校所在市的名称
    */
    private String cityName;
    /**
    *学校所在县的代码
    */
    private String countyCode;
    /**
    *学号
    */
    private String educationId;

    public String getEduIdPhotoId(){
        return this.eduIdPhotoId;
    }

    public void setEduIdPhotoId(String eduIdPhotoId){
        this.eduIdPhotoId = eduIdPhotoId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getClassName(){
        return this.className;
    }

    public void setClassName(String className){
        this.className = className;
    }
    public String getFullName(){
        return this.fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public String getEducationLevelName(){
        return this.educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName){
        this.educationLevelName = educationLevelName;
    }
    public String getIdCard(){
        return this.idCard;
    }

    public void setIdCard(String idCard){
        this.idCard = idCard;
    }
    public String getSchoolProvinceCode(){
        return this.schoolProvinceCode;
    }

    public void setSchoolProvinceCode(String schoolProvinceCode){
        this.schoolProvinceCode = schoolProvinceCode;
    }
    public String getGradeName(){
        return this.gradeName;
    }

    public void setGradeName(String gradeName){
        this.gradeName = gradeName;
    }
    public String getZoneCode(){
        return this.zoneCode;
    }

    public void setZoneCode(String zoneCode){
        this.zoneCode = zoneCode;
    }
    public String getContactMethod(){
        return this.contactMethod;
    }

    public void setContactMethod(String contactMethod){
        this.contactMethod = contactMethod;
    }
    public String getCountyName(){
        return this.countyName;
    }

    public void setCountyName(String countyName){
        this.countyName = countyName;
    }
    public String getGroupCode(){
        return this.groupCode;
    }

    public void setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }
    public String getSchoolName(){
        return this.schoolName;
    }

    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
    }
    public String getSchoolCityCode(){
        return this.schoolCityCode;
    }

    public void setSchoolCityCode(String schoolCityCode){
        this.schoolCityCode = schoolCityCode;
    }
    public String getProvinceName(){
        return this.provinceName;
    }

    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
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
    public String getCountyCode(){
        return this.countyCode;
    }

    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public String getEducationId(){
        return this.educationId;
    }

    public void setEducationId(String educationId){
        this.educationId = educationId;
    }

}