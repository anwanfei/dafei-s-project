package com.junhangxintong.myfirstmvp.Model;

/**
 * Created by anwanfei on 2017/7/13.
 */

public interface IUser {
    String getName();
    String getPassword();
    int checkUserValidity(String name,String password);
}
