package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class RegisterBean implements Serializable{

    private String confirmPassword;
    private String password;
    private String phoneNumber;
    private String verifyCode;

    public String getConfirmPassword(){
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
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