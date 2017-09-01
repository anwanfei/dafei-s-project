package cn.metaship.app.yzlib.net.api.base;

import java.io.Serializable;

public class Order implements Serializable {
    public final static String ASC = "ASC";
    public final static String DESC = "DESC";
    private String direction;
    private String property;

    public Order() {
    }

    public Order(String direction, String property) {
        this.direction = direction;
        this.property = property;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    public void setProperty(String property){
        this.property = property;
    }
}
