package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;

public final class BigBangWordShareBean implements Serializable{

    /**
    *分享用户ID
    */
    private String sharerId;
    /**
    *单词ID
    */
    private String wordId;
    private String id;
    /**
    *分享时间
    */
    private Date shareTime;

    public String getSharerId(){
        return this.sharerId;
    }

    public void setSharerId(String sharerId){
        this.sharerId = sharerId;
    }
    public String getWordId(){
        return this.wordId;
    }

    public void setWordId(String wordId){
        this.wordId = wordId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public Date getShareTime(){
        return this.shareTime;
    }

    public void setShareTime(Date shareTime){
        this.shareTime = shareTime;
    }

}