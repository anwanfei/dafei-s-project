package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.Date;

public final class ExpertCommentModel implements Serializable{

    private String batchCode;
    private String expertId;
    private String expertName;
    private Integer score;
    private Date commentTime;
    private String commentContent;
    private String commentDetail;

    public String getBatchCode(){
        return this.batchCode;
    }

    public void setBatchCode(String batchCode){
        this.batchCode = batchCode;
    }
    public String getExpertId(){
        return this.expertId;
    }

    public void setExpertId(String expertId){
        this.expertId = expertId;
    }
    public String getExpertName(){
        return this.expertName;
    }

    public void setExpertName(String expertName){
        this.expertName = expertName;
    }
    public Integer getScore(){
        return this.score;
    }

    public void setScore(Integer score){
        this.score = score;
    }
    public Date getCommentTime(){
        return this.commentTime;
    }

    public void setCommentTime(Date commentTime){
        this.commentTime = commentTime;
    }
    public String getCommentContent(){
        return this.commentContent;
    }

    public void setCommentContent(String commentContent){
        this.commentContent = commentContent;
    }
    public String getCommentDetail(){
        return this.commentDetail;
    }

    public void setCommentDetail(String commentDetail){
        this.commentDetail = commentDetail;
    }

}