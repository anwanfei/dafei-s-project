package com.junhangxintong.chuanzhangtong.dynamic.bean;

/**
 * Created by anwanfei on 2017/9/16.
 */

public class DynamicCrewDetails {
    /**
     * message : 查询成功
     * status : success
     * data : {"object":{"personName":"小李","certifNo":"123456789","isUse":1,"advanceWarnDay":0,"modifyDate":"","isExpire":1,"id":2,"imgUrl":"","certifType":1,"userId":26,"name":"君航","validDate":"2017/9/8","createDate":"2017-09-08 11:39:34","isValid":1,"createUserId":21,"issueOrganization":"上海海事局"}}
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
         * object : {"personName":"小李","certifNo":"123456789","isUse":1,"advanceWarnDay":0,"modifyDate":"","isExpire":1,"id":2,"imgUrl":"","certifType":1,"userId":26,"name":"君航","validDate":"2017/9/8","createDate":"2017-09-08 11:39:34","isValid":1,"createUserId":21,"issueOrganization":"上海海事局"}
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
             * personName : 小李
             * certifNo : 123456789
             * isUse : 1
             * advanceWarnDay : 0
             * modifyDate :
             * isExpire : 1
             * id : 2
             * imgUrl :
             * certifType : 1
             * userId : 26
             * name : 君航
             * validDate : 2017/9/8
             * createDate : 2017-09-08 11:39:34
             * isValid : 1
             * createUserId : 21
             * issueOrganization : 上海海事局
             */

            private String personName;
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

            public String getPersonName() {
                return personName;
            }

            public void setPersonName(String personName) {
                this.personName = personName;
            }

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
