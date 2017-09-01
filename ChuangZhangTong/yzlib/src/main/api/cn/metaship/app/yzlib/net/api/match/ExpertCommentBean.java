package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class ExpertCommentBean implements Serializable{

    private String entryId;
    private String commentContent;
    private String commentDetail;
    private String batchCode;
    private Integer score;
    private String expertId;

    public String getEntryId(){
        return this.entryId;
    }

    public void setEntryId(String entryId){
        this.entryId = entryId;
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
    public String getBatchCode(){
        return this.batchCode;
    }

    public void setBatchCode(String batchCode){
        this.batchCode = batchCode;
    }
    public Integer getScore(){
        return this.score;
    }

    public void setScore(Integer score){
        this.score = score;
    }
    public String getExpertId(){
        return this.expertId;
    }

    public void setExpertId(String expertId){
        this.expertId = expertId;
    }

}