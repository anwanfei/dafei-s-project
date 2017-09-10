package com.junhangxintong.chuanzhangtong.shipposition.bean;

/**
 * Created by anwanfei on 2017/9/10.
 */

public class AddBerthingReportBean {

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

        private String anchorAweighDate;
        private String loadPort;
        private String portBearth;
        private String shipForwardDraft;
        private String holdInspectionEndDate;
        private String isPilotage;
        private String xtBerthDate;
        private String holdInspectionBeginDate;
        private String tugUseNum;

        public String getAnchorAweighDate() {
            return anchorAweighDate;
        }

        public void setAnchorAweighDate(String anchorAweighDate) {
            this.anchorAweighDate = anchorAweighDate;
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

        public String getHoldInspectionBeginDate() {
            return holdInspectionBeginDate;
        }

        public void setHoldInspectionBeginDate(String holdInspectionBeginDate) {
            this.holdInspectionBeginDate = holdInspectionBeginDate;
        }

        public String getTugUseNum() {
            return tugUseNum;
        }

        public void setTugUseNum(String tugUseNum) {
            this.tugUseNum = tugUseNum;
        }
    }
}
