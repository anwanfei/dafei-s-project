package com.junhangxintong.myfirstmvp.presenter;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.junhangxintong.myfirstmvp.Model.IUser;
import com.junhangxintong.myfirstmvp.Model.User;
import com.junhangxintong.myfirstmvp.view.ILoginView;

/**
 * Created by anwanfei on 2017/7/13.
 */

public class LoginPresenterComplete implements ILoginPresenter {

    ILoginView iLoginView;
    IUser iUser;
    Handler handler;

    public LoginPresenterComplete(ILoginView iLoginView) {

        this.iLoginView = iLoginView;
        handler = new Handler(Looper.getMainLooper());
        iUser = new User("fei", "123");
    }

    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String name, String password) {
        Boolean isLoginSuccess = true;
        final int code = iUser.checkUserValidity(name, password);
        if (code != 0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.onLoginResult(result, code);
                iLoginView.onSetProgressBarVisbility(View.INVISIBLE);
            }
        }, 5000);
    }

    @Override
    public void setProgressBarVisbility(int visbility) {
        iLoginView.onSetProgressBarVisbility(visbility);
    }
}
