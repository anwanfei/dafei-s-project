package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public final class BigBangTopicReplyBean implements Serializable{

    /**
    *主题ID
    */
    private String topicId;
    /**
    *回答的回复数量
    */
    private Integer replyCount;
    /**
    *发布时间
    */
    private Date releaseTime;
    /**
    *bigbang回答的ID
    */
    private String answerId;
    /**
    *点赞数量
    */
    private Integer likeCount;
    /**
    *发布主题的用户ID
    */
    private String topicUserId;
    /**
    *回复内容
    */
    private String replyContent;
    /**
    *问题ID
    */
    private String askId;
    /**
    *点踩数量
    */
    private Integer dislikeCount;
    private String id;
    /**
    *回复的用户ID
    */
    private String atUserId;
    /**
    *主题点赞点踩状态与用户绑定的集合保存
    */
    private Map<String,Byte> topicMap;

    public String getTopicId(){
        return this.topicId;
    }

    public void setTopicId(String topicId){
        this.topicId = topicId;
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
    public String getAnswerId(){
        return this.answerId;
    }

    public void setAnswerId(String answerId){
        this.answerId = answerId;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getTopicUserId(){
        return this.topicUserId;
    }

    public void setTopicUserId(String topicUserId){
        this.topicUserId = topicUserId;
    }
    public String getReplyContent(){
        return this.replyContent;
    }

    public void setReplyContent(String replyContent){
        this.replyContent = replyContent;
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
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getAtUserId(){
        return this.atUserId;
    }

    public void setAtUserId(String atUserId){
        this.atUserId = atUserId;
    }
    public Map<String,Byte> getTopicMap(){
        return this.topicMap;
    }

    public void setTopicMap(Map<String,Byte> topicMap){
        this.topicMap = topicMap;
    }

}