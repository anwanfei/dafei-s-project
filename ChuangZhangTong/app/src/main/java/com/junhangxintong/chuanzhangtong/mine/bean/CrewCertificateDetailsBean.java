package com.junhangxintong.chuanzhangtong.mine.bean;

import java.io.Serializable;

/**
 * Created by anwanfei on 2017/9/8.
 */

public class CrewCertificateDetailsBean implements Serializable{


    private String message;
    private String status;
    private DataBean data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean implements Serializable{
        /**
         * object : {"certifNo":"123456","isUse":1,"advanceWarnDay":20,"modifyDate":"","isExpire":1,"id":1,"imgUrl":"[{\"picUrl\":\"buiness/user/pic/suggest/17/1/20170912151039.jpg\",\"seq\":1},{\"picUrl\":\"buiness/user/pic/suggest/17/2/20170912151039.jpg\",\"seq\":2},{\"picUrl\":\"buiness/user/pic/suggest/17/3/20170912151040.jpg\",\"seq\":3}]","certifType":1,"userId":22,"name":"证书1","validDate":"","domain":"http://ow5f3r531.bkt.clouddn.com/","createDate":"2017-09-07 17:15:30","isValid":1,"createUserId":17,"issueOrganization":"中国船舶公司"}
         */

        private ObjectBean object;

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public static class ObjectBean implements Serializable{

            private String certifNo;
            private int isUse;
            private int advanceWarnDay;
            private String modifyDate;
            private int isExpire;
            private int id;
            private String imgUrl;
            private int certifType;
            private int userId;
            private String name;
            private String validDate;
            private String domain;
            private String createDate;
            private int isValid;
            private int createUserId;
            private String issueOrganization;

            public String getCertifNo() {
                return certifNo;
            }

            public void setCertifNo(String certifNo) {
                this.certifNo = certifNo;
            }

            public int getIsUse() {
                return isUse;
            }

            public void setIsUse(int isUse) {
                this.isUse = isUse;
            }

            public int getAdvanceWarnDay() {
                return advanceWarnDay;
            }

            public void setAdvanceWarnDay(int advanceWarnDay) {
                this.advanceWarnDay = advanceWarnDay;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public int getIsExpire() {
                return isExpire;
            }

            public void setIsExpire(int isExpire) {
                this.isExpire = isExpire;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getCertifType() {
                return certifType;
            }

            public void setCertifType(int certifType) {
                this.certifType = certifType;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValidDate() {
                return validDate;
            }

            public void setValidDate(String validDate) {
                this.validDate = validDate;
            }

            public String getDomain() {
                return domain;
            }

            public void setDomain(String domain) {
                this.domain = domain;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getIsValid() {
                return isValid;
            }

            public void setIsValid(int isValid) {
                this.isValid = isValid;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public String getIssueOrganization() {
                return issueOrganization;
            }

            public void setIssueOrganization(String issueOrganization) {
                this.issueOrganization = issueOrganization;
            }
        }
    }
}
