package com.junhangxintong.chuanzhangtong.dynamic.bean;

import java.io.Serializable;

/**
 * Created by anwanfei on 2017/9/11.
 */

public class DynamicRemindBerthingReportBean implements Serializable {

    /**
     * message : 查询成功
     * status : success
     * data : {"reportType":2,"object":{"shipId":1,"shipName":"中国号","anchorAweighDate":"2017-09-09 10:29:44","modifyDate":"","id":1,"loadPort":"靠泊报","portBearth":"大连港","shipForwardDraft":"1","holdInspectionEndDate":"2017-09-10 10:29:44","isPilotage":"2","xtBerthDate":"2017-09-09 10:29:44","createDate":"2017-09-09 10:29:44","holdInspectionBeginDate":"2017-09-09 10:29:44","createUserId":17,"tugUseNum":"2"}}
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

    public static class DataBean implements Serializable{
        /**
         * reportType : 2
         * object : {"shipId":1,"shipName":"中国号","anchorAweighDate":"2017-09-09 10:29:44","modifyDate":"","id":1,"loadPort":"靠泊报","portBearth":"大连港","shipForwardDraft":"1","holdInspectionEndDate":"2017-09-10 10:29:44","isPilotage":"2","xtBerthDate":"2017-09-09 10:29:44","createDate":"2017-09-09 10:29:44","holdInspectionBeginDate":"2017-09-09 10:29:44","createUserId":17,"tugUseNum":"2"}
         */

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

        public static class ObjectBean implements Serializable {
            /**
             * shipId : 1
             * shipName : 中国号
             * anchorAweighDate : 2017-09-09 10:29:44
             * modifyDate :
             * id : 1
             * loadPort : 靠泊报
             * portBearth : 大连港
             * shipForwardDraft : 1
             * holdInspectionEndDate : 2017-09-10 10:29:44
             * isPilotage : 2
             * xtBerthDate : 2017-09-09 10:29:44
             * createDate : 2017-09-09 10:29:44
             * holdInspectionBeginDate : 2017-09-09 10:29:44
             * createUserId : 17
             * tugUseNum : 2
             */

            private int shipId;
            private String shipName;
            private String anchorAweighDate;
            private String modifyDate;
            private int id;
            private String loadPort;
            private String portBearth;
            private String shipForwardDraft;
            private String holdInspectionEndDate;
            private String isPilotage;
            private String xtBerthDate;
            private String createDate;
            private String holdInspectionBeginDate;
            private int createUserId;
            private String tugUseNum;
            private int isShowEditBtn;

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

            public String getAnchorAweighDate() {
                return anchorAweighDate;
            }

            public void setAnchorAweighDate(String anchorAweighDate) {
                this.anchorAweighDate = anchorAweighDate;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
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

            public String getPortBearth() {
                return portBearth;
            }

            public void setPortBearth(String portBearth) {
                this.portBearth = portBearth;
            }

            public String getShipForwardDraft() {
                return shipForwardDraft;
            }

            public void setShipForwardDraft(String shipForwardDraft) {
                this.shipForwardDraft = shipForwardDraft;
            }

            public String getHoldInspectionEndDate() {
                return holdInspectionEndDate;
            }

            public void setHoldInspectionEndDate(String holdInspectionEndDate) {
                this.holdInspectionEndDate = holdInspectionEndDate;
            }

            public String getIsPilotage() {
                return isPilotage;
            }

            public void setIsPilotage(String isPilotage) {
                this.isPilotage = isPilotage;
            }

            public String getXtBerthDate() {
                return xtBerthDate;
            }

            public void setXtBerthDate(String xtBerthDate) {
                this.xtBerthDate = xtBerthDate;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getHoldInspectionBeginDate() {
                return holdInspectionBeginDate;
            }

            public void setHoldInspectionBeginDate(String holdInspectionBeginDate) {
                this.holdInspectionBeginDate = holdInspectionBeginDate;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
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
