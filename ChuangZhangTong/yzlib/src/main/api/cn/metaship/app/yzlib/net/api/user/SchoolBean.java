package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;

public final class SchoolBean implements Serializable{

    /**
    *年级名称列表
    */
    private List<GradeModel> grades;
    /**
    *学校名称
    */
    private String schoolName;
    /**
    *县区编码
    */
    private String countyCode;
    private String id;
    /**
    *学校简称
    */
    private String shortSchoolName;
    /**
    *省份编码
    */
    private String provinceCode;
    /**
    *市级编码
    */
    private String cityCode;

    public List<GradeModel> getGrades(){
        return this.grades;
    }

    public void setGrades(List<GradeModel> grades){
        this.grades = grades;
    }
    public String getSchoolName(){
        return this.schoolName;
    }

    public void setSchoolName(String schoolName){
        this.schoolName = schoolName;
    }
    public String getCountyCode(){
        return this.countyCode;
    }

    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getShortSchoolName(){
        return this.shortSchoolName;
    }

    public void setShortSchoolName(String shortSchoolName){
        this.shortSchoolName = shortSchoolName;
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