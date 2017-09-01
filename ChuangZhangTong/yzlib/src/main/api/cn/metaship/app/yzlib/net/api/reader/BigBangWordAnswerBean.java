package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public final class BigBangWordAnswerBean implements Serializable{

    /**
    *回答内容
    */
    private String answerContent;
    /**
    *单词ID
    */
    private String wordId;
    /**
    *回答点赞、点踩与相关用户绑定
    */
    private Map<String,Byte> condition;
    /**
    *回答的回复数量
    */
    private Integer replyCount;
    /**
    *回答发布时间
    */
    private Date releaseTime;
    /**
    *提问用户ID
    */
    private String askUserId;
    private String id;
    /**
    *提问ID
    */
    private String askId;
    /**
    *回答点踩数量
    */
    private Integer dislikeCount;
    /**
    *回答点赞数量
    */
    private Integer likeCount;
    /**
    *回答用户ID
    */
    private String answerUserId;

    public String getAnswerContent(){
        return this.answerContent;
    }

    public void setAnswerContent(String answerContent){
        this.answerContent = answerContent;
    }
    public String getWordId(){
        return this.wordId;
    }

    public void setWordId(String wordId){
        this.wordId = wordId;
    }
    public Map<String,Byte> getCondition(){
        return this.condition;
    }

    public void setCondition(Map<String,Byte> condition){
        this.condition = condition;
    }
    public Integer getReplyCount(){
        return this.replyCount;
    }

    public void setReplyCount(Integer replyCount){
        this.replyCount = replyCount;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public String getAskUserId(){
        return this.askUserId;
    }

    public void setAskUserId(String askUserId){
        this.askUserId = askUserId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getAskId(){
        return this.askId;
    }

    public void setAskId(String askId){
        this.askId = askId;
    }
    public Integer getDislikeCount(){
        return this.dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount){
        this.dislikeCount = dislikeCount;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getAnswerUserId(){
        return this.answerUserId;
    }

    public void setAnswerUserId(String answerUserId){
        this.answerUserId = answerUserId;
    }

}