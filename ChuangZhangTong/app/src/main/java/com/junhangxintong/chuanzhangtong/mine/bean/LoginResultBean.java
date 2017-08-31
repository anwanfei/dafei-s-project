package com.junhangxintong.chuanzhangtong.mine.bean;

/**
 * Created by anwanfei on 2017/8/31.
 */

public class LoginResultBean {

    /**
     * message : 登录成功
     * status : success
     * data : {"token":"0E0AFF62-23AD-4480-B91F-A8BEB7895060","object":{"deptName":"","headImgUrl":"","sex":"0","superAdminUserId":"0","roleName":"其他","modifyDate":"","contactPersonPhone":"","cardType":"0","password":"c33367701511b4f6020ec61ded352059","nation":"","personName":"","registerIp":"0:0:0:0:0:0:0:1","cardNo":"","isDisabled":"0","id":"17","mobilePhone":"15810512120","postName":"","email":"","jobNo":"","contactPersonName":"","createDate":"2017-08-30 15:33:46","sourceRegister":"2","roleId":"4"}}
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

    public static class DataBean {
        /**
         * token : 0E0AFF62-23AD-4480-B91F-A8BEB7895060
         * object : {"deptName":"","headImgUrl":"","sex":"0","superAdminUserId":"0","roleName":"其他","modifyDate":"","contactPersonPhone":"","cardType":"0","password":"c33367701511b4f6020ec61ded352059","nation":"","personName":"","registerIp":"0:0:0:0:0:0:0:1","cardNo":"","isDisabled":"0","id":"17","mobilePhone":"15810512120","postName":"","email":"","jobNo":"","contactPersonName":"","createDate":"2017-08-30 15:33:46","sourceRegister":"2","roleId":"4"}
         */

        private String token;
        private ObjectBean object;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public static class ObjectBean {
            /**
             * deptName :
             * headImgUrl :
             * sex : 0
             * superAdminUserId : 0
             * roleName : 其他
             * modifyDate :
             * contactPersonPhone :
             * cardType : 0
             * password : c33367701511b4f6020ec61ded352059
             * nation :
             * personName :
             * registerIp : 0:0:0:0:0:0:0:1
             * cardNo :
             * isDisabled : 0
             * id : 17
             * mobilePhone : 15810512120
             * postName :
             * email :
             * jobNo :
             * contactPersonName :
             * createDate : 2017-08-30 15:33:46
             * sourceRegister : 2
             * roleId : 4
             */

            private String deptName;
            private String headImgUrl;
            private String sex;
            private String superAdminUserId;
            private String roleName;
            private String modifyDate;
            private String contactPersonPhone;
            private String cardType;
            private String password;
            private String nation;
            private String personName;
            private String registerIp;
            private String cardNo;
            private String isDisabled;
            private String id;
            private String mobilePhone;
            private String postName;
            private String email;
            private String jobNo;
            private String contactPersonName;
            private String createDate;
            private String sourceRegister;
            private String roleId;

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public String getHeadImgUrl() {
                return headImgUrl;
            }

            public void setHeadImgUrl(String headImgUrl) {
                this.headImgUrl = headImgUrl;
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

            public String getRegisterIp() {
                return registerIp;
            }

            public void setRegisterIp(String registerIp) {
                this.registerIp = registerIp;
            }

            public String getCardNo() {
                return cardNo;
            }

            public void setCardNo(String cardNo) {
                this.cardNo = cardNo;
            }

            public String getIsDisabled() {
                return isDisabled;
            }

            public void setIsDisabled(String isDisabled) {
                this.isDisabled = isDisabled;
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

            public String getSourceRegister() {
                return sourceRegister;
            }

            public void setSourceRegister(String sourceRegister) {
                this.sourceRegister = sourceRegister;
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
