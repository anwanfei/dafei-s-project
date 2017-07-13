package com.junhangxintong.myfirstmvp.Model;

/**
 * Created by anwanfei on 2017/7/13.
 */

public class User implements IUser {

    String name;
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int checkUserValidity(String name, String password) {
        if (name == null || password == null || !name.equals(getName()) || !password.equals(getPassword())) {
            return -1;
        }
        return 0;
    }
}
