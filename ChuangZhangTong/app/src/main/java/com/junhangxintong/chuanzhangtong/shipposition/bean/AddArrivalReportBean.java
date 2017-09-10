package com.junhangxintong.chuanzhangtong.shipposition.bean;

/**
 * Created by anwanfei on 2017/9/10.
 */

public class AddArrivalReportBean {

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
         * portRadsteadBerth : 1号
         * remark :
         * arriveAnchorDate : 2017-09-08 10:29:44
         * course : 北部
         * arrivePort : 上海港
         * shipLightOil : 22.33
         * anchorPosition : 12
         * shipForwardDraft : 22
         * shipFreshwater : 22
         * lightOilConsumption : 22
         * longitude : 12.222
         * shipHeavyOil : 22
         * latitude : 133.111
         * freshwaterConsumption : 22
         * currShipSpeed : 100
         */

        private String portRadsteadBerth;
        private String remark;
        private String arriveAnchorDate;
        private String course;
        private String arrivePort;
        private String shipLightOil;
        private String anchorPosition;
        private String shipForwardDraft;
        private String shipFreshwater;
        private String lightOilConsumption;
        private String longitude;
        private String shipHeavyOil;
        private String latitude;
        private String freshwaterConsumption;
        private String currShipSpeed;

        public String getPortRadsteadBerth() {
            return portRadsteadBerth;
        }

        public void setPortRadsteadBerth(String portRadsteadBerth) {
            this.portRadsteadBerth = portRadsteadBerth;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getArriveAnchorDate() {
            return arriveAnchorDate;
        }

        public void setArriveAnchorDate(String arriveAnchorDate) {
            this.arriveAnchorDate = arriveAnchorDate;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getArrivePort() {
            return arrivePort;
        }

        public void setArrivePort(String arrivePort) {
            this.arrivePort = arrivePort;
        }

        public String getShipLightOil() {
            return shipLightOil;
        }

        public void setShipLightOil(String shipLightOil) {
            this.shipLightOil = shipLightOil;
        }

        public String getAnchorPosition() {
            return anchorPosition;
        }

        public void setAnchorPosition(String anchorPosition) {
            this.anchorPosition = anchorPosition;
        }

        public String getShipForwardDraft() {
            return shipForwardDraft;
        }

        public void setShipForwardDraft(String shipForwardDraft) {
            this.shipForwardDraft = shipForwardDraft;
        }

        public String getShipFreshwater() {
            return shipFreshwater;
        }

        public void setShipFreshwater(String shipFreshwater) {
            this.shipFreshwater = shipFreshwater;
        }

        public String getLightOilConsumption() {
            return lightOilConsumption;
        }

        public void setLightOilConsumption(String lightOilConsumption) {
            this.lightOilConsumption = lightOilConsumption;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getShipHeavyOil() {
            return shipHeavyOil;
        }

        public void setShipHeavyOil(String shipHeavyOil) {
            this.shipHeavyOil = shipHeavyOil;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getFreshwaterConsumption() {
            return freshwaterConsumption;
        }

        public void setFreshwaterConsumption(String freshwaterConsumption) {
            this.freshwaterConsumption = freshwaterConsumption;
        }

        public String getCurrShipSpeed() {
            return currShipSpeed;
        }

        public void setCurrShipSpeed(String currShipSpeed) {
            this.currShipSpeed = currShipSpeed;
        }
    }
}
