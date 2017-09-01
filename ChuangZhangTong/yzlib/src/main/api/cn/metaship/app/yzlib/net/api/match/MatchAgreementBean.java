package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class MatchAgreementBean implements Serializable{

    /**
    *大赛协议html地址
    */
    private String agreementUrl;
    /**
    *大赛组别编码
    */
    private String groupCode;

    public String getAgreementUrl(){
        return this.agreementUrl;
    }

    public void setAgreementUrl(String agreementUrl){
        this.agreementUrl = agreementUrl;
    }
    public String getGroupCode(){
        return this.groupCode;
    }

    public void setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }

}