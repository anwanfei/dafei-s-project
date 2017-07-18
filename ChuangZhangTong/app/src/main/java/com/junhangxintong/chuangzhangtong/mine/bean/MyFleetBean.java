package com.junhangxintong.chuangzhangtong.mine.bean;

/**
 * Created by edz on 2017/7/18.
 */

public class MyFleetBean {
    private String shipName;
    private boolean isCheckbox;

    public MyFleetBean() {
    }

    public MyFleetBean(String shipName, boolean isCheckbox) {
        this.shipName = shipName;
        this.isCheckbox = isCheckbox;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public boolean isCheckbox() {
        return isCheckbox;
    }

    public void setCheckbox(boolean checkbox) {
        isCheckbox = checkbox;
    }
}
