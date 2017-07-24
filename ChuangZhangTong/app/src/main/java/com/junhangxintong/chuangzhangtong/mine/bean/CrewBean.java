package com.junhangxintong.chuangzhangtong.mine.bean;

/**
 * Created by edz on 2017/7/20.
 */

public class CrewBean {
    private String crewName;
    private String ID;
    private String nationality;
    private String jobNum;
    private String duty;
    private String phoneNum;
    private String mailBox;
    private boolean isCheckbox;

    public CrewBean() {
    }

    public boolean isCheckbox() {
        return isCheckbox;
    }

    public void setCheckbox(boolean checkbox) {
        isCheckbox = checkbox;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }
}
