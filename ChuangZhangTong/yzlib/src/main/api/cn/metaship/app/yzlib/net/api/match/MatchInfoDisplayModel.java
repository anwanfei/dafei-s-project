package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class MatchInfoDisplayModel implements Serializable{

    /**
    *赛事信息文本内容
    */
    private String matchInfoText;
    /**
    *赛事信息发布时间
    */
    private Date releaseTime;
    /**
    *赛事信息图片地址
    */
    private String matchInfoImgUrl;
    /**
    *赛事信息发布状态
    */
    private Byte releaseStatus;
    /**
    *赛事信息ID
    */
    private String matchInfoId;
    private String id;

    public String getMatchInfoText(){
        return this.matchInfoText;
    }

    public void setMatchInfoText(String matchInfoText){
        this.matchInfoText = matchInfoText;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public String getMatchInfoImgUrl(){
        return this.matchInfoImgUrl;
    }

    public void setMatchInfoImgUrl(String matchInfoImgUrl){
        this.matchInfoImgUrl = matchInfoImgUrl;
    }
    public Byte getReleaseStatus(){
        return this.releaseStatus;
    }

    public void setReleaseStatus(Byte releaseStatus){
        this.releaseStatus = releaseStatus;
    }
    public String getMatchInfoId(){
        return this.matchInfoId;
    }

    public void setMatchInfoId(String matchInfoId){
        this.matchInfoId = matchInfoId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}