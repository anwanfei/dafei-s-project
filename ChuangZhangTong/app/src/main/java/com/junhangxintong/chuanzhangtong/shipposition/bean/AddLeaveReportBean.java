package com.junhangxintong.chuanzhangtong.shipposition.bean;

/**
 * Created by anwanfei on 2017/9/10.
 */

public class AddLeaveReportBean {
    /**
     * userId : 17
     * shipId : 1
     * type : 2
     * reportJsonData : {"leavaBerthDate":"2017-09-11 11:29:44","goodsNum":"22","loadPort":"离港报","shipForwardDraft":"","loadBeginDate":"2017-09-09 10:29:44","isPilotage":"2","loadDndDate":"2017-09-11 10:29:44","expectArriveBearthDate":"2017-09-12 11:29:44","goodsType":"重物","tugUseNum":"2"}
     */

    private String userId;
    private String shipId;
    private String type;
    private ReportJsonDataBean reportJsonData;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ReportJsonDataBean getReportJsonData() {
        return reportJsonData;
    }

    public void setReportJsonData(ReportJsonDataBean reportJsonData) {
        this.reportJsonData = reportJsonData;
    }

    public static class ReportJsonDataBean {
        /**
         * leavaBerthDate : 2017-09-11 11:29:44
         * goodsNum : 22
         * loadPort : 离港报
         * shipForwardDraft :
         * loadBeginDate : 2017-09-09 10:29:44
         * isPilotage : 2
         * loadDndDate : 2017-09-11 10:29:44
         * expectArriveBearthDate : 2017-09-12 11:29:44
         * goodsType : 重物
         * tugUseNum : 2
         */

        private String leavaBerthDate;
        private String goodsNum;
        private String loadPort;
        private String shipForwardDraft;
        private String loadBeginDate;
        private String isPilotage;
        private String loadDndDate;
        private String expectArriveBearthDate;
        private String goodsType;
        private String tugUseNum;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLeavaBerthDate() {
            return leavaBerthDate;
        }

        public void setLeavaBerthDate(String leavaBerthDate) {
            this.leavaBerthDate = leavaBerthDate;
        }

        public String getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(String goodsNum) {
            this.goodsNum = goodsNum;
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

        public String getExpectArriveBearthDate() {
            return expectArriveBearthDate;
        }

        public void setExpectArriveBearthDate(String expectArriveBearthDate) {
            this.expectArriveBearthDate = expectArriveBearthDate;
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
