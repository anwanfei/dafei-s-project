package com.junhangxintong.chuanzhangtong.shipposition.bean;

/**
 * Created by anwanfei on 2017/9/9.
 */

public class AddNoonReportBean {
    /**
     * userId : 17
     * shipId : 1
     * type : 1
     * reportJsonData : {"pressure":"11","remark":"备注","windDirection":"南风","modifyDate":"","snailRange":"2","consume":"22","temperature":"22","course":"往北","shipLightOil":"22","avgSpeed":"22","shipForwardDraft":"22","shipFreshwater":"22","lightOilConsumption":"22","weather":"晴天","longitude":"22","shipHeavyOil":"22","waveLevel":"22","latitude":"131.223","createDate":"2017-09-09 10:29:44","freshwaterConsumption":"22","expectArrivalDate":"2017-09-09 10:29:44","currShipSpeed":"50"}
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
         * pressure : 11
         * remark : 备注
         * windDirection : 南风
         * modifyDate :
         * snailRange : 2
         * consume : 22
         * temperature : 22
         * course : 往北
         * shipLightOil : 22
         * avgSpeed : 22
         * shipForwardDraft : 22
         * shipFreshwater : 22
         * lightOilConsumption : 22
         * weather : 晴天
         * longitude : 22
         * shipHeavyOil : 22
         * waveLevel : 22
         * latitude : 131.223
         * createDate : 2017-09-09 10:29:44
         * freshwaterConsumption : 22
         * expectArrivalDate : 2017-09-09 10:29:44
         * currShipSpeed : 50
         */

        private String pressure;
        private String remark;
        private String windDirection;
        private String modifyDate;
        private String snailRange;
        private String consume;
        private String temperature;
        private String course;
        private String shipLightOil;
        private String avgSpeed;
        private String shipForwardDraft;
        private String shipFreshwater;
        private String lightOilConsumption;
        private String weather;
        private String longitude;
        private String shipHeavyOil;
        private String waveLevel;
        private String latitude;
        private String createDate;
        private String freshwaterConsumption;
        private String expectArrivalDate;
        private String currShipSpeed;

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getWindDirection() {
            return windDirection;
        }

        public void setWindDirection(String windDirection) {
            this.windDirection = windDirection;
        }

        public String getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(String modifyDate) {
            this.modifyDate = modifyDate;
        }

        public String getSnailRange() {
            return snailRange;
        }

        public void setSnailRange(String snailRange) {
            this.snailRange = snailRange;
        }

        public String getConsume() {
            return consume;
        }

        public void setConsume(String consume) {
            this.consume = consume;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getShipLightOil() {
            return shipLightOil;
        }

        public void setShipLightOil(String shipLightOil) {
            this.shipLightOil = shipLightOil;
        }

        public String getAvgSpeed() {
            return avgSpeed;
        }

        public void setAvgSpeed(String avgSpeed) {
            this.avgSpeed = avgSpeed;
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

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
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

        public String getWaveLevel() {
            return waveLevel;
        }

        public void setWaveLevel(String waveLevel) {
            this.waveLevel = waveLevel;
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

        public String getExpectArrivalDate() {
            return expectArrivalDate;
        }

        public void setExpectArrivalDate(String expectArrivalDate) {
            this.expectArrivalDate = expectArrivalDate;
        }

        public String getCurrShipSpeed() {
            return currShipSpeed;
        }

        public void setCurrShipSpeed(String currShipSpeed) {
            this.currShipSpeed = currShipSpeed;
        }
    }
}
