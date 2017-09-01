package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ExpertCommentEntryBean implements Serializable{

    /**
    *专家id
    */
    private String expertId;
    /**
    *作者id
    */
    private String entryAuthorId;
    /**
    *评分
    */
    private Integer score;
    /**
    *点评时间
    */
    private Integer commentState;
    /**
    *作品id
    */
    private String entryId;
    /**
    *作品题目
    */
    private String entryTitle;
    /**
    *点评时间
    */
    private Date commentTime;
    /**
    *作者名称
    */
    private String entryAuthorName;
    /**
    *点评明细
    */
    private String commentDetail;
    /**
    *点评内容
    */
    private String commentContent;
    /**
    *大赛作品编号
    */
    private String matchEntryCode;

    public String getExpertId(){
        return this.expertId;
    }

    public void setExpertId(String expertId){
        this.expertId = expertId;
    }
    public String getEntryAuthorId(){
        return this.entryAuthorId;
    }

    public void setEntryAuthorId(String entryAuthorId){
        this.entryAuthorId = entryAuthorId;
    }
    public Integer getScore(){
        return this.score;
    }

    public void setScore(Integer score){
        this.score = score;
    }
    public Integer getCommentState(){
        return this.commentState;
    }

    public void setCommentState(Integer commentState){
        this.commentState = commentState;
    }
    public String getEntryId(){
        return this.entryId;
    }

    public void setEntryId(String entryId){
        this.entryId = entryId;
    }
    public String getEntryTitle(){
        return this.entryTitle;
    }

    public void setEntryTitle(String entryTitle){
        this.entryTitle = entryTitle;
    }
    public Date getCommentTime(){
        return this.commentTime;
    }

    public void setCommentTime(Date commentTime){
        this.commentTime = commentTime;
    }
    public String getEntryAuthorName(){
        return this.entryAuthorName;
    }

    public void setEntryAuthorName(String entryAuthorName){
        this.entryAuthorName = entryAuthorName;
    }
    public String getCommentDetail(){
        return this.commentDetail;
    }

    public void setCommentDetail(String commentDetail){
        this.commentDetail = commentDetail;
    }
    public String getCommentContent(){
        return this.commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    public String getMatchEntryCode(){
        return this.matchEntryCode;
    }

    public void setMatchEntryCode(String matchEntryCode){
        this.matchEntryCode = matchEntryCode;
    }

}