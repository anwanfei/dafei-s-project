package com.junhangxintong.chuanzhangtong.dynamic.bean;

import java.io.Serializable;

/**
 * Created by anwanfei on 2017/9/11.
 */

public class DynamicRemindLeaveReportBean implements Serializable {

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

        private int reportType;
        private ObjectBean object;

        public int getReportType() {
            return reportType;
        }

        public void setReportType(int reportType) {
            this.reportType = reportType;
        }

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public static class ObjectBean implements Serializable{

            private int shipId;
            private String shipName;
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
            private int isShowEditBtn;
            private int timer;

            public int getTimer() {
                return timer;
            }

            public void setTimer(int timer) {
                this.timer = timer;
            }

            public int getIsShowEditBtn() {
                return isShowEditBtn;
            }

            public void setIsShowEditBtn(int isShowEditBtn) {
                this.isShowEditBtn = isShowEditBtn;
            }

            public int getShipId() {
                return shipId;
            }

            public void setShipId(int shipId) {
                this.shipId = shipId;
            }

            public String getShipName() {
                return shipName;
            }

            public void setShipName(String shipName) {
                this.shipName = shipName;
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
