package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;

public final class StudentDetailsModel implements Serializable{

    /**
    *学号
    */
    private String educationId;
    /**
    *学校所在县的代码
    */
    private String countyCode;
    /**
    *省名称
    */
    private String schoolProvinceName;
    /**
    *学校ID
    */
    private String schoolId;
    /**
    *学生认证信息的有效期结束时间
    */
    private Date expireDateEndTime;
    /**
    *学校所在市代码
    */
    private String schoolCityCode;
    /**
    *学校名称
    */
    private String schoolName;
    /**
    *参赛组别
    */
    private String groupCode;
    /**
    *联系方式
    */
    private String contactMethod;
    /**
    *学校所在市名称
    */
    private String schoolCityName;
    /**
    *学校所在县的名称
    */
    private String countyName;
    /**
    *赛区编码
    */
    private String zoneCode;
    /**
    *年级名称
    */
    private String gradeName;
    /**
    *学校所在省份代码
    */
    private String schoolProvinceCode;
    /**
    *教育级别(小学、初中、高中)
    */
    private String educationLevelName;
    /**
    *学生认证信息的有效期开始时间
    */
    private Date expiryDateStartTime;
    /**
    *身份证号码
    */
    private String idCard;
    /**
    *学生姓名
    */
    private String fullName;
    /**
    *班级名称
    */
    private String className;
    /**
    *学生证照片ID
    */
    private String eduIdPhotoId;

    public String getEducationId(){
        return this.educationId;
    }

    public void setEducationId(String educationId){
        this.educationId = educationId;
    }
    public String getCountyCode(){
        return this.countyCode;
    }

    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public String getSchoolProvinceName(){
        return this.schoolProvinceName;
    }

    public void setSchoolProvinceName(String schoolProvinceName){
        this.schoolProvinceName = schoolProvinceName;
    }
    public String getSchoolId(){
        return this.schoolId;
    }

    public void setSchoolId(String schoolId){
        this.schoolId = schoolId;
    }
    public Date getExpireDateEndTime(){
        return this.expireDateEndTime;
    }

    public void setExpireDateEndTime(Date expireDateEndTime){
        this.expireDateEndTime = expireDateEndTime;
    }
    public String getSchoolCityCode(){
        return this.schoolCityCode;
    }

    public void setSchoolCityCode(String schoolCityCode){
        this.schoolCityCode = schoolCityCode;
    }
    public String getSchoolName(){
        return this.schoolName;
    }

    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
    }
    public String getGroupCode(){
        return this.groupCode;
    }

    public void setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }
    public String getContactMethod(){
        return this.contactMethod;
    }

    public void setContactMethod(String contactMethod){
        this.contactMethod = contactMethod;
    }
    public String getSchoolCityName(){
        return this.schoolCityName;
    }

    public void setSchoolCityName(String schoolCityName){
        this.schoolCityName = schoolCityName;
    }
    public String getCountyName(){
        return this.countyName;
    }

    public void setCountyName(String countyName){
        this.countyName = countyName;
    }
    public String getZoneCode(){
        return this.zoneCode;
    }

    public void setZoneCode(String zoneCode){
        this.zoneCode = zoneCode;
    }
    public String getGradeName(){
        return this.gradeName;
    }

    public void setGradeName(String gradeName){
        this.gradeName = gradeName;
    }
    public String getSchoolProvinceCode(){
        return this.schoolProvinceCode;
    }

    public void setSchoolProvinceCode(String schoolProvinceCode){
        this.schoolProvinceCode = schoolProvinceCode;
    }
    public String getEducationLevelName(){
        return this.educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName){
        this.educationLevelName = educationLevelName;
    }
    public Date getExpiryDateStartTime(){
        return this.expiryDateStartTime;
    }

    public void setExpiryDateStartTime(Date expiryDateStartTime){
        this.expiryDateStartTime = expiryDateStartTime;
    }
    public String getIdCard(){
        return this.idCard;
    }

    public void setIdCard(String idCard){
        this.idCard = idCard;
    }
    public String getFullName(){
        return this.fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public String getClassName(){
        return this.className;
    }

    public void setClassName(String className){
        this.className = className;
    }
    public String getEduIdPhotoId(){
        return this.eduIdPhotoId;
    }

    public void setEduIdPhotoId(String eduIdPhotoId){
        this.eduIdPhotoId = eduIdPhotoId;
    }

}