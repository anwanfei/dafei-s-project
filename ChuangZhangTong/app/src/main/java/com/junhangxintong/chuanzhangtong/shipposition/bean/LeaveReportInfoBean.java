package com.junhangxintong.chuanzhangtong.shipposition.bean;

/**
 * Created by anwanfei on 2017/9/10.
 */

public class LeaveReportInfoBean {
    /**
     * message : 查询成功
     * status : success
     * data : {"object":{"shipId":1,"leavaBerthDate":"2017-09-11 11:29:44","modifyDate":"","goodsNum":"2","id":1,"loadPort":"离港报","shipForwardDraft":"","loadBeginDate":"2017-09-09 10:29:44","isPilotage":"1","loadDndDate":"2017-09-11 10:29:44","createDate":"2017-09-08 11:29:44","expectArriveBearthDate":"2017-09-12 11:29:44","createUserId":17,"goodsType":"重物","tugUseNum":"2"}}
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
         * object : {"shipId":1,"leavaBerthDate":"2017-09-11 11:29:44","modifyDate":"","goodsNum":"2","id":1,"loadPort":"离港报","shipForwardDraft":"","loadBeginDate":"2017-09-09 10:29:44","isPilotage":"1","loadDndDate":"2017-09-11 10:29:44","createDate":"2017-09-08 11:29:44","expectArriveBearthDate":"2017-09-12 11:29:44","createUserId":17,"goodsType":"重物","tugUseNum":"2"}
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
             * shipId : 1
             * leavaBerthDate : 2017-09-11 11:29:44
             * modifyDate :
             * goodsNum : 2
             * id : 1
             * loadPort : 离港报
             * shipForwardDraft :
             * loadBeginDate : 2017-09-09 10:29:44
             * isPilotage : 1
             * loadDndDate : 2017-09-11 10:29:44
             * createDate : 2017-09-08 11:29:44
             * expectArriveBearthDate : 2017-09-12 11:29:44
             * createUserId : 17
             * goodsType : 重物
             * tugUseNum : 2
             */

            private int shipId;
            private String leavaBerthDate;
            private String modifyDate;
            private String goodsNum;
            private int id;
            private String loadPort;
            private String shipForwardDraft;
            private String loadBeginDate;
            private String isPilotage;
            private String loadDndDate;
            private String createDate;
            private String expectArriveBearthDate;
            private int createUserId;
            private String goodsType;
            private String tugUseNum;

            public int getShipId() {
                return shipId;
            }

            public void setShipId(int shipId) {
                this.shipId = shipId;
            }

            public String getLeavaBerthDate() {
                return leavaBerthDate;
            }

            public void setLeavaBerthDate(String leavaBerthDate) {
                this.leavaBerthDate = leavaBerthDate;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getGoodsNum() {
                return goodsNum;
            }

            public void setGoodsNum(String goodsNum) {
                this.goodsNum = goodsNum;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLoadPort() {
                return loadPort;
            }

            public void setLoadPort(String loadPort) {
                this.loadPort = loadPort;
            }

            public String getShipForwardDraft() {
                return shipForwardDraft;
            }

            public void setShipForwardDraft(String shipForwardDraft) {
                this.shipForwardDraft = shipForwardDraft;
            }

            public String getLoadBeginDate() {
                return loadBeginDate;
            }

            public void setLoadBeginDate(String loadBeginDate) {
                this.loadBeginDate = loadBeginDate;
            }

            public String getIsPilotage() {
                return isPilotage;
            }

            public void setIsPilotage(String isPilotage) {
                this.isPilotage = isPilotage;
            }

            public String getLoadDndDate() {
                return loadDndDate;
            }

            public void setLoadDndDate(String loadDndDate) {
                this.loadDndDate = loadDndDate;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getExpectArriveBearthDate() {
                return expectArriveBearthDate;
            }

            public void setExpectArriveBearthDate(String expectArriveBearthDate) {
                this.expectArriveBearthDate = expectArriveBearthDate;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public String getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(String goodsType) {
                this.goodsType = goodsType;
            }

            public String getTugUseNum() {
                return tugUseNum;
            }

            public void setTugUseNum(String tugUseNum) {
                this.tugUseNum = tugUseNum;
            }
        }
    }
}
