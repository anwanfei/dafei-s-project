package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;

public final class MailRegisterBean implements Serializable{

    private String password;
    private String mail;
    private String confirmPassword;

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getMail(){
        return this.mail;
    }

    public void setMail(String mail){
        this.mail = mail;
    }
    public String getConfirmPassword(){
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword = confirmPassword;
    }

}