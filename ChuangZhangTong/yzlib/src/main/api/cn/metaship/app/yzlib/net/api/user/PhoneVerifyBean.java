package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class PhoneVerifyBean implements Serializable{

    /**
    *手机号
    */
    private String phoneNumber;
    /**
    *验证码
    */
    private String verifyCode;

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getVerifyCode(){
        return this.verifyCode;
    }

    public void setVerifyCode(String verifyCode){
        this.verifyCode = verifyCode;
    }

}