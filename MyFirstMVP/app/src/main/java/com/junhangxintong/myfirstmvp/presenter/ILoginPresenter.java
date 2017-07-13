package com.junhangxintong.myfirstmvp.presenter;

/**
 * Created by edz on 2017/7/13.
 */

public interface ILoginPresenter {
    void clear();

    void doLogin(String name, String password);

    void setProgressBarVisbility(int visbility);
}
