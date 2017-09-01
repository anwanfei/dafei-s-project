package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class MatchCompanyBean implements Serializable{

    /**
    *大赛id
    */
    private String matchId;
    /**
    *主办单位logo图片地址
    */
    private String logoUrl;
    /**
    *大赛主办单位名称
    */
    private String companyName;
    private String id;

    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public String getLogoUrl(){
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl){
        this.logoUrl = logoUrl;
    }
    public String getCompanyName(){
        return this.companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}