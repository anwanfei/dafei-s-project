package com.junhangxintong.myfirstmvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.junhangxintong.myfirstmvp.R;
import com.junhangxintong.myfirstmvp.presenter.LoginPresenterComplete;

public class LoginbActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private EditText et_name;
    private EditText et_pwd;
    private Button btn_login;
    private Button btn_cancel;
    private ProgressBar progress_login;
    private LoginPresenterComplete loginPresenterComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        initView();

        //设置监听
        initListener();

        //初始化实现类
        init();


    }

    private void init() {
        loginPresenterComplete = new LoginPresenterComplete(this);
        loginPresenterComplete.setProgressBarVisbility(View.INVISIBLE);
    }

    private void initListener() {
        btn_cancel.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        progress_login = (ProgressBar) findViewById(R.id.progress_login);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                loginPresenterComplete.clear();
                break;
            case R.id.btn_login:
                //当点击登陆按钮的时候，显示progressbar，两个按钮都能点击，presenter做登录处理
                loginPresenterComplete.setProgressBarVisbility(View.VISIBLE);
                btn_cancel.setEnabled(false);
                btn_login.setEnabled(false);
                loginPresenterComplete.doLogin(et_name.getText().toString(), et_pwd.getText().toString());
                break;
        }
    }

    @Override
    public void onClearText() {
        et_name.setText("");
        et_pwd.setText("");
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        loginPresenterComplete.setProgressBarVisbility(View.VISIBLE);
        btn_cancel.setEnabled(true);
        btn_login.setEnabled(true);
        if (result) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Login Fail, code = " + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSetProgressBarVisbility(int visbility) {
        progress_login.setVisibility(visbility);
    }
}
