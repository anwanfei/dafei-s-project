package com.junhangxintong.chuanzhangtong.mine.bean;

/**
 * Created by anwanfei on 2017/9/9.
 */

public class CustomCertificateBean {
    private String name;
    private String binahao;
    private String issueArgument;
    private String validDate;
    private String id;
    private String shipName;

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBinahao() {
        return binahao;
    }

    public void setBinahao(String binahao) {
        this.binahao = binahao;
    }

    public String getIssueArgument() {
        return issueArgument;
    }

    public void setIssueArgument(String issueArgument) {
        this.issueArgument = issueArgument;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
