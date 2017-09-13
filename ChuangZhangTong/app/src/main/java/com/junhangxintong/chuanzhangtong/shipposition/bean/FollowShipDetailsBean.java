package com.junhangxintong.chuanzhangtong.shipposition.bean;

/**
 * Created by anwanfei on 2017/9/11.
 */

public class FollowShipDetailsBean {
    /**
     * message : 查询成功
     * status : success
     * data : {"object":{"imo":"imo001","shipSize":20000,"status":1,"superAdminUserId":1,"shipName":"君航号","modifyDate":"","mmsi":"mmsi001","shipWidth":10000,"type":"","nation":"中国","id":1,"callSign":"abc001","createDate":"2017-08-31 11:28:07"}}
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
         * object : {"imo":"imo001","shipSize":20000,"status":1,"superAdminUserId":1,"shipName":"君航号","modifyDate":"","mmsi":"mmsi001","shipWidth":10000,"type":"","nation":"中国","id":1,"callSign":"abc001","createDate":"2017-08-31 11:28:07"}
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
             * imo : imo001
             * shipSize : 20000
             * status : 1
             * superAdminUserId : 1
             * shipName : 君航号
             * modifyDate :
             * mmsi : mmsi001
             * shipWidth : 10000
             * type :
             * nation : 中国
             * id : 1
             * callSign : abc001
             * createDate : 2017-08-31 11:28:07
             */

            private String imo;
            private int shipSize;
            private int status;
            private int superAdminUserId;
            private String shipName;
            private String modifyDate;
            private String mmsi;
            private int shipWidth;
            private String type;
            private String nation;
            private int id;
            private String callSign;
            private String createDate;

            public String getImo() {
                return imo;
            }

            public void setImo(String imo) {
                this.imo = imo;
            }

            public int getShipSize() {
                return shipSize;
            }

            public void setShipSize(int shipSize) {
                this.shipSize = shipSize;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getSuperAdminUserId() {
                return superAdminUserId;
            }

            public void setSuperAdminUserId(int superAdminUserId) {
                this.superAdminUserId = superAdminUserId;
            }

            public String getShipName() {
                return shipName;
            }

            public void setShipName(String shipName) {
                this.shipName = shipName;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getMmsi() {
                return mmsi;
            }

            public void setMmsi(String mmsi) {
                this.mmsi = mmsi;
            }

            public int getShipWidth() {
                return shipWidth;
            }

            public void setShipWidth(int shipWidth) {
                this.shipWidth = shipWidth;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNation() {
                return nation;
            }

            public void setNation(String nation) {
                this.nation = nation;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCallSign() {
                return callSign;
            }

            public void setCallSign(String callSign) {
                this.callSign = callSign;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }
    }
}
