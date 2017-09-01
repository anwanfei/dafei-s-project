package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class MatchVoteTodayPropositionBean implements Serializable{

    /**
    *命题的有效期的起始时间
    */
    private Date expiryStartTime;
    /**
    *当前用户是否参与该命题  1-参与 2-未参与
    */
    private Integer isJoin;
    /**
    *被抠出的所有单词
    */
    private List<WordDigModel> wordDigModels;
    /**
    *命题的有效期截止时间
    */
    private Date expiryEndTime;
    /**
    *命题赛的标题
    */
    private String propositionTitle;
    private String id;
    /**
    *命题发布时间
    */
    private Date releasedTime;
    /**
    *命题全文
    */
    private String propositionText;

    public Date getExpiryStartTime(){
        return this.expiryStartTime;
    }

    public void setExpiryStartTime(Date expiryStartTime){
        this.expiryStartTime = expiryStartTime;
    }
    public Integer getIsJoin(){
        return this.isJoin;
    }

    public void setIsJoin(Integer isJoin){
        this.isJoin = isJoin;
    }
    public List<WordDigModel> getWordDigModels(){
        return this.wordDigModels;
    }

    public void setWordDigModels(List<WordDigModel> wordDigModels){
        this.wordDigModels = wordDigModels;
    }
    public Date getExpiryEndTime(){
        return this.expiryEndTime;
    }

    public void setExpiryEndTime(Date expiryEndTime){
        this.expiryEndTime = expiryEndTime;
    }
    public String getPropositionTitle(){
        return this.propositionTitle;
    }

    public void setPropositionTitle(String propositionTitle){
        this.propositionTitle = propositionTitle;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public Date getReleasedTime(){
        return this.releasedTime;
    }

    public void setReleasedTime(Date releasedTime){
        this.releasedTime = releasedTime;
    }
    public String getPropositionText(){
        return this.propositionText;
    }

    public void setPropositionText(String propositionText){
        this.propositionText = propositionText;
    }

}