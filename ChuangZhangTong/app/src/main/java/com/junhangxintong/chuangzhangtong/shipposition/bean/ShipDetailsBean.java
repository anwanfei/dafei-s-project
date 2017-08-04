package com.junhangxintong.chuangzhangtong.shipposition.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by anwanfei on 2017/8/2.
 */

@Entity
public class ShipDetailsBean {
    @Id(autoincrement = true)
    private Long shipId;
    private String ShipName;

    @Generated(hash = 844890054)
    public ShipDetailsBean(Long shipId, String ShipName) {
        this.shipId = shipId;
        this.ShipName = ShipName;
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
        return this.ShipName;
    }

    public void setShipName(String ShipName) {
        this.ShipName = ShipName;
    }
}
