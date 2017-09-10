package com.junhangxintong.chuanzhangtong.mine.bean;

import java.util.List;

/**
 * Created by anwanfei on 2017/9/7.
 */

public class CrewServeBean {

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

        private int count;
        private List<ArrayBean> array;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ArrayBean> getArray() {
            return array;
        }

        public void setArray(List<ArrayBean> array) {
            this.array = array;
        }

        public static class ArrayBean {

            private int shipId;
            private String deptName;
            private int sex;
            private int superAdminUserId;
            private String roleName;
            private String modifyDate;
            private String contactPersonPhone;
            private int assigneUserId;
            private int cardType;
            private String password;
            private String nation;
            private String personName;
            private String cardNo;
            private int id;
            private String mobilePhone;
            private String postName;
            private String email;
            private String jobNo;
            private String contactPersonName;
            private String createDate;
            private int roleId;
            private boolean isCheckbox;

            public boolean isCheckbox() {
                return isCheckbox;
            }

            public void setCheckbox(boolean checkbox) {
                isCheckbox = checkbox;
            }

            public int getShipId() {
                return shipId;
            }

            public void setShipId(int shipId) {
                this.shipId = shipId;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getSuperAdminUserId() {
                return superAdminUserId;
            }

            public void setSuperAdminUserId(int superAdminUserId) {
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

            public int getAssigneUserId() {
                return assigneUserId;
            }

            public void setAssigneUserId(int assigneUserId) {
                this.assigneUserId = assigneUserId;
            }

            public int getCardType() {
                return cardType;
            }

            public void setCardType(int cardType) {
                this.cardType = cardType;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }
        }
    }
}
