package com.junhangxintong.chuanzhangtong.mine.bean;

/**
 * Created by anwanfei on 2017/9/8.
 */

public class CrewCertificateDetailsBean {

    /**
     * message : 查询成功
     * status : success
     * data : {"object":{"certifNo":"123","isUse":1,"advanceWarnDay":0,"modifyDate":"","isExpire":1,"id":1,"imgUrl":"","certifType":1,"userId":26,"name":"话","validDate":"","createDate":"2017-09-08 11:03:24","isValid":1,"createUserId":21,"issueOrganization":"好"}}
     * code : 200
     */

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

    public static class DataBean {
        /**
         * object : {"certifNo":"123","isUse":1,"advanceWarnDay":0,"modifyDate":"","isExpire":1,"id":1,"imgUrl":"","certifType":1,"userId":26,"name":"话","validDate":"","createDate":"2017-09-08 11:03:24","isValid":1,"createUserId":21,"issueOrganization":"好"}
         */

        private ObjectBean object;

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public static class ObjectBean {
            /**
             * certifNo : 123
             * isUse : 1
             * advanceWarnDay : 0
             * modifyDate :
             * isExpire : 1
             * id : 1
             * imgUrl :
             * certifType : 1
             * userId : 26
             * name : 话
             * validDate :
             * createDate : 2017-09-08 11:03:24
             * isValid : 1
             * createUserId : 21
             * issueOrganization : 好
             */

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
