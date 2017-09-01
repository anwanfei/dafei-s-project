package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class MatchJoinPropositionModel implements Serializable{

    private Integer isPublish;
    private Integer commentCount;
    private String propositionId;
    private List<WordDigModel> wordPosition;
    private Integer likeCount;
    private Integer lotteryQualification;
    private Date createTime;
    private String replaceText;

    public Integer getIsPublish(){
        return this.isPublish;
    }

    public void setIsPublish(Integer isPublish){
        this.isPublish = isPublish;
    }
    public Integer getCommentCount(){
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
    }
    public String getPropositionId(){
        return this.propositionId;
    }

    public void setPropositionId(String propositionId){
        this.propositionId = propositionId;
    }
    public List<WordDigModel> getWordPosition(){
        return this.wordPosition;
    }

    public void setWordPosition(List<WordDigModel> wordPosition){
        this.wordPosition = wordPosition;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public Integer getLotteryQualification(){
        return this.lotteryQualification;
    }

    public void setLotteryQualification(Integer lotteryQualification){
        this.lotteryQualification = lotteryQualification;
    }
    public Date getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public String getReplaceText(){
        return this.replaceText;
    }

    public void setReplaceText(String replaceText){
        this.replaceText = replaceText;
    }

}