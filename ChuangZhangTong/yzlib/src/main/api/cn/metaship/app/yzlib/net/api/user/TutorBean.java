package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class TutorBean implements Serializable{

    private String tutorName;
    private String tutorUserId;

    public String getTutorName(){
        return this.tutorName;
    }

    public void setTutorName(String tutorName){
        this.tutorName = tutorName;
    }
    public String getTutorUserId(){
        return this.tutorUserId;
    }

    public void setTutorUserId(String tutorUserId){
        this.tutorUserId = tutorUserId;
    }

}