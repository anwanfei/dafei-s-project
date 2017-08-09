package com.anwanfei.anfly.foundation.bean;

/**
 * Created by anwanfei on 2017/7/28.
 */

public class FoundationBean {
    private int imageview;
    private String name;

    public FoundationBean(int imageview, String name) {
        this.imageview = imageview;
        this.name = name;
    }

    public FoundationBean() {
    }

    public int getImageview() {
        return imageview;
    }

    public void setImageview(int imageview) {
        this.imageview = imageview;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
