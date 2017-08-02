package com.junhangxintong.chuangzhangtong.shipposition.bean;

/**
 * Created by anwanfei on 2017/8/2.
 */

public class ShipDetailsBean {
    private String ShipName;
    private String MMSI;

    public ShipDetailsBean(String shipName, String MMSI) {
        ShipName = shipName;
        this.MMSI = MMSI;
    }

    public String getShipName() {
        return ShipName;
    }

    public void setShipName(String shipName) {
        ShipName = shipName;
    }

    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
        this.MMSI = MMSI;
    }
}
