package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;

public final class GradeModel implements Serializable{

    private List<ClassModel> classs;
    private String gradeName;

    public List<ClassModel> getClasss(){
        return this.classs;
    }

    public void setClasss(List<ClassModel> classs){
        this.classs = classs;
    }
    public String getGradeName(){
        return this.gradeName;
    }

    public void setGradeName(String gradeName){
        this.gradeName = gradeName;
    }

}