package com.metashipanwf.androidsummarize.event;

/**
 * author:AnWanfei
 * time:2017/2/15.
 * Email:anwanfei_sp@163.com
 * function:3.构造发送消息类
 */

public class MessageEvent {
    public String name;
    public String password;

    public MessageEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
