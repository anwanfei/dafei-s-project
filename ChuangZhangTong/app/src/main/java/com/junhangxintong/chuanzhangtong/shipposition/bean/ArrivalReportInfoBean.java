package com.junhangxintong.chuanzhangtong.shipposition.bean;

import java.io.Serializable;

/**
 * Created by anwanfei on 2017/9/10.
 */

public class ArrivalReportInfoBean implements Serializable {
    /**
     * message : 查询成功
     * status : success
     * data : {"object":{"shipId":1,"portRadsteadBerth":"1号","remark":"","arriveAnchorDate":"2017-09-08 10:29:44","modifyDate":"","id":1,"course":"北部","arrivePort":"上海港","shipLightOil":"12.222","anchorPosition":"12","shipForwardDraft":"12.222","shipFreshwater":"12.222","lightOilConsumption":"12.222","longitude":"12.222","shipHeavyOil":"22.345","latitude":"133.111","createDate":"2017-09-08 10:29:44","freshwaterConsumption":"12","currShipSpeed":"100","createUserId":17}}
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
         * object : {"shipId":1,"portRadsteadBerth":"1号","remark":"","arriveAnchorDate":"2017-09-08 10:29:44","modifyDate":"","id":1,"course":"北部","arrivePort":"上海港","shipLightOil":"12.222","anchorPosition":"12","shipForwardDraft":"12.222","shipFreshwater":"12.222","lightOilConsumption":"12.222","longitude":"12.222","shipHeavyOil":"22.345","latitude":"133.111","createDate":"2017-09-08 10:29:44","freshwaterConsumption":"12","currShipSpeed":"100","createUserId":17}
         */

        private ObjectBean object;

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public static class ObjectBean implements Serializable{
            /**
             * shipId : 1
             * portRadsteadBerth : 1号
             * remark :
             * arriveAnchorDate : 2017-09-08 10:29:44
             * modifyDate :
             * id : 1
             * course : 北部
             * arrivePort : 上海港
             * shipLightOil : 12.222
             * anchorPosition : 12
             * shipForwardDraft : 12.222
             * shipFreshwater : 12.222
             * lightOilConsumption : 12.222
             * longitude : 12.222
             * shipHeavyOil : 22.345
             * latitude : 133.111
             * createDate : 2017-09-08 10:29:44
             * freshwaterConsumption : 12
             * currShipSpeed : 100
             * createUserId : 17
             */

            private int shipId;
            private String portRadsteadBerth;
            private String remark;
            private String arriveAnchorDate;
            private String modifyDate;
            private int id;
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
            private String createDate;
            private String freshwaterConsumption;
            private String currShipSpeed;
            private int createUserId;

            public int getShipId() {
                return shipId;
            }

            public void setShipId(int shipId) {
                this.shipId = shipId;
            }

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

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
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

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }
        }
    }
}
