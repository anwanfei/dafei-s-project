package com.junhangxintong.chuanzhangtong.dynamic.bean;

/**
 * Created by anwanfei on 2017/9/11.
 */

public class ShipCertificateExpireBean {
    /**
     * message : 查询成功
     * status : success
     * data : {"object":{"shipId":2,"assureType":"","certifNo":"","imoNo":"","isUse":1,"shipName":"大飞号","issueDate":"","issueAddress":"","modifyDate":"","advanceWarnDay":30,"shipCall":"123456","isExpire":0,"id":7,"imgUrl":"","certifType":2,"name":"落水险","validDate":"","shipNameOrAddress":"","shipNationaPort":"","createDate":"2017-09-08 20:04:25","isValid":2,"createUserId":19,"issueOrganization":""}}
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
         * object : {"shipId":2,"assureType":"","certifNo":"","imoNo":"","isUse":1,"shipName":"大飞号","issueDate":"","issueAddress":"","modifyDate":"","advanceWarnDay":30,"shipCall":"123456","isExpire":0,"id":7,"imgUrl":"","certifType":2,"name":"落水险","validDate":"","shipNameOrAddress":"","shipNationaPort":"","createDate":"2017-09-08 20:04:25","isValid":2,"createUserId":19,"issueOrganization":""}
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
             * shipId : 2
             * assureType :
             * certifNo :
             * imoNo :
             * isUse : 1
             * shipName : 大飞号
             * issueDate :
             * issueAddress :
             * modifyDate :
             * advanceWarnDay : 30
             * shipCall : 123456
             * isExpire : 0
             * id : 7
             * imgUrl :
             * certifType : 2
             * name : 落水险
             * validDate :
             * shipNameOrAddress :
             * shipNationaPort :
             * createDate : 2017-09-08 20:04:25
             * isValid : 2
             * createUserId : 19
             * issueOrganization :
             */

            private int shipId;
            private String assureType;
            private String certifNo;
            private String imoNo;
            private int isUse;
            private String shipName;
            private String issueDate;
            private String issueAddress;
            private String modifyDate;
            private int advanceWarnDay;
            private String shipCall;
            private int isExpire;
            private int id;
            private String imgUrl;
            private int certifType;
            private String name;
            private String validDate;
            private String shipNameOrAddress;
            private String shipNationaPort;
            private String createDate;
            private int isValid;
            private int createUserId;
            private String issueOrganization;

            public int getShipId() {
                return shipId;
            }

            public void setShipId(int shipId) {
                this.shipId = shipId;
            }

            public String getAssureType() {
                return assureType;
            }

            public void setAssureType(String assureType) {
                this.assureType = assureType;
            }

            public String getCertifNo() {
                return certifNo;
            }

            public void setCertifNo(String certifNo) {
                this.certifNo = certifNo;
            }

            public String getImoNo() {
                return imoNo;
            }

            public void setImoNo(String imoNo) {
                this.imoNo = imoNo;
            }

            public int getIsUse() {
                return isUse;
            }

            public void setIsUse(int isUse) {
                this.isUse = isUse;
            }

            public String getShipName() {
                return shipName;
            }

            public void setShipName(String shipName) {
                this.shipName = shipName;
            }

            public String getIssueDate() {
                return issueDate;
            }

            public void setIssueDate(String issueDate) {
                this.issueDate = issueDate;
            }

            public String getIssueAddress() {
                return issueAddress;
            }

            public void setIssueAddress(String issueAddress) {
                this.issueAddress = issueAddress;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public int getAdvanceWarnDay() {
                return advanceWarnDay;
            }

            public void setAdvanceWarnDay(int advanceWarnDay) {
                this.advanceWarnDay = advanceWarnDay;
            }

            public String getShipCall() {
                return shipCall;
            }

            public void setShipCall(String shipCall) {
                this.shipCall = shipCall;
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

            public String getShipNameOrAddress() {
                return shipNameOrAddress;
            }

            public void setShipNameOrAddress(String shipNameOrAddress) {
                this.shipNameOrAddress = shipNameOrAddress;
            }

            public String getShipNationaPort() {
                return shipNationaPort;
            }

            public void setShipNationaPort(String shipNationaPort) {
                this.shipNationaPort = shipNationaPort;
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
