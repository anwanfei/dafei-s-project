package com.junhangxintong.chuangzhangtong.shipposition.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by anwanfei on 2017/8/2.
 */

@Entity
public class ShipDetailsBean{
    @Id(autoincrement = true)
    private Long shipId;
    private String shipName;
    private String shipNationality;
    private String mmsi;
    private String shipType;
    @Generated(hash = 2108138033)
    public ShipDetailsBean(Long shipId, String shipName, String shipNationality,
            String mmsi, String shipType) {
        this.shipId = shipId;
        this.shipName = shipName;
        this.shipNationality = shipNationality;
        this.mmsi = mmsi;
        this.shipType = shipType;
    }
    @Generated(hash = 957716942)
    public ShipDetailsBean() {
    }
    public Long getShipId() {
        return this.shipId;
    }
    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }
    public String getShipName() {
        return this.shipName;
    }
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
    public String getShipNationality() {
        return this.shipNationality;
    }
    public void setShipNationality(String shipNationality) {
        this.shipNationality = shipNationality;
    }
    public String getMmsi() {
        return this.mmsi;
    }
    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }
    public String getShipType() {
        return this.shipType;
    }
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }
}
