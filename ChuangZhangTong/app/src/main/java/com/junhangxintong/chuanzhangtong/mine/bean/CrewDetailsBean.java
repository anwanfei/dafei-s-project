package com.junhangxintong.chuanzhangtong.mine.bean;

import java.io.Serializable;

/**
 * Created by anwanfei on 2017/9/7.
 */

public class CrewDetailsBean implements Serializable{
    /**
     * message : 查询成功
     * status : success
     * data : {"object":{"shipId":"1","deptName":"","sex":"0","superAdminUserId":"1","roleName":"船长","modifyDate":"","contactPersonPhone":"","cardType":"0","nation":"","personName":"2","cardNo":"","id":"17","mobilePhone":"15810512120","postName":"","email":"","jobNo":"","contactPersonName":"2","createDate":"2017-08-30 15:33:46","roleId":"2"}}
     * code : 200
     */

    private String message;
    private String status;
    private DataBean data;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class DataBean implements Serializable{
        /**
         * object : {"shipId":"1","deptName":"","sex":"0","superAdminUserId":"1","roleName":"船长","modifyDate":"","contactPersonPhone":"","cardType":"0","nation":"","personName":"2","cardNo":"","id":"17","mobilePhone":"15810512120","postName":"","email":"","jobNo":"","contactPersonName":"2","createDate":"2017-08-30 15:33:46","roleId":"2"}
         */

        private ObjectBean object;

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public static class ObjectBean implements Serializable{

            private String shipId;
            private String deptName;
            private String sex;
            private String superAdminUserId;
            private String roleName;
            private String modifyDate;
            private String contactPersonPhone;
            private String cardType;
            private String nation;
            private String personName;
            private String cardNo;
            private String id;
            private String mobilePhone;
            private String postName;
            private String email;
            private String jobNo;
            private String contactPersonName;
            private String createDate;
            private String roleId;

            public String getShipId() {
                return shipId;
            }

            public void setShipId(String shipId) {
                this.shipId = shipId;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSuperAdminUserId() {
                return superAdminUserId;
            }

            public void setSuperAdminUserId(String superAdminUserId) {
                this.superAdminUserId = superAdminUserId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getContactPersonPhone() {
                return contactPersonPhone;
            }

            public void setContactPersonPhone(String contactPersonPhone) {
                this.contactPersonPhone = contactPersonPhone;
            }

            public String getCardType() {
                return cardType;
            }

            public void setCardType(String cardType) {
                this.cardType = cardType;
            }

            public String getNation() {
                return nation;
            }

            public void setNation(String nation) {
                this.nation = nation;
            }

            public String getPersonName() {
                return personName;
            }

            public void setPersonName(String personName) {
                this.personName = personName;
            }

            public String getCardNo() {
                return cardNo;
            }

            public void setCardNo(String cardNo) {
                this.cardNo = cardNo;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMobilePhone() {
                return mobilePhone;
            }

            public void setMobilePhone(String mobilePhone) {
                this.mobilePhone = mobilePhone;
            }

            public String getPostName() {
                return postName;
            }

            public void setPostName(String postName) {
                this.postName = postName;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getJobNo() {
                return jobNo;
            }

            public void setJobNo(String jobNo) {
                this.jobNo = jobNo;
            }

            public String getContactPersonName() {
                return contactPersonName;
            }

            public void setContactPersonName(String contactPersonName) {
                this.contactPersonName = contactPersonName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }
        }
    }
}
