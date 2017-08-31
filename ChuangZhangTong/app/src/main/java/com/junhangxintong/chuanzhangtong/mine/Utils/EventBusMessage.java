package com.junhangxintong.chuanzhangtong.mine.Utils;

import java.util.ArrayList;

/**
 * Created by anwanfei on 2017/8/30.
 */

public class EventBusMessage {
    private ArrayList<String> photos;

    public EventBusMessage(ArrayList<String> photos) {
        this.photos = photos;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }
}
