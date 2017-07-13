package com.junhangxintong.myfirstmvp.view;

/**
 * Created by anwafei on 2017/7/13.
 */

public interface ILoginView {
    public void onClearText();
    public void onLoginResult(Boolean result, int code);
    public void onSetProgressBarVisbility(int visbility);
}
