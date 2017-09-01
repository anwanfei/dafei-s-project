package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class ScrambleForwardBean implements Serializable{

    /**
    *转发人ID
    */
    private String forwardUserId;
    /**
    *转发类型
    */
    private Byte entryType;
    /**
    *被转发用户ID
    */
    private String beForwardUserId;
    private String avatarUrl;
    /**
    *转发人名称
    */
    private String forwardUserName;
    /**
    *被转发文章摘要
    */
    private String beForwardEntrySummary;
    /**
    *点赞量
    */
    private Integer likeCount;
    /**
    *评论量
    */
    private Integer commentCount;
    /**
    *被转发用户名称
    */
    private String beForwardUserName;
    /**
    *转发自ID
    */
    private String forwardFromId;
    /**
    *转发描述
    */
    private String forwardDescription;
    /**
    *被转发抢票赛内容ID
    */
    private String beForwardScrambleId;
    /**
    *转发自列表
    */
    private List<UserDynamicSourceInfoBean> userDynamicSourceInfoBeanList;
    /**
    *转发时间
    */
    private Date forwardTime;
    private String nickname;
    private String id;

    public String getForwardUserId(){
        return this.forwardUserId;
    }

    public void setForwardUserId(String forwardUserId){
        this.forwardUserId = forwardUserId;
    }
    public Byte getEntryType(){
        return this.entryType;
    }

    public void setEntryType(Byte entryType){
        this.entryType = entryType;
    }
    public String getBeForwardUserId(){
        return this.beForwardUserId;
    }

    public void setBeForwardUserId(String beForwardUserId){
        this.beForwardUserId = beForwardUserId;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public String getForwardUserName(){
        return this.forwardUserName;
    }

    public void setForwardUserName(String forwardUserName){
        this.forwardUserName = forwardUserName;
    }
    public String getBeForwardEntrySummary(){
        return this.beForwardEntrySummary;
    }

    public void setBeForwardEntrySummary(String beForwardEntrySummary){
        this.beForwardEntrySummary = beForwardEntrySummary;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public Integer getCommentCount(){
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
    }
    public String getBeForwardUserName(){
        return this.beForwardUserName;
    }

    public void setBeForwardUserName(String beForwardUserName){
        this.beForwardUserName = beForwardUserName;
    }
    public String getForwardFromId(){
        return this.forwardFromId;
    }

    public void setForwardFromId(String forwardFromId){
        this.forwardFromId = forwardFromId;
    }
    public String getForwardDescription(){
        return this.forwardDescription;
    }

    public void setForwardDescription(String forwardDescription){
        this.forwardDescription = forwardDescription;
    }
    public String getBeForwardScrambleId(){
        return this.beForwardScrambleId;
    }

    public void setBeForwardScrambleId(String beForwardScrambleId){
        this.beForwardScrambleId = beForwardScrambleId;
    }
    public List<UserDynamicSourceInfoBean> getUserDynamicSourceInfoBeanList(){
        return this.userDynamicSourceInfoBeanList;
    }

    public void setUserDynamicSourceInfoBeanList(List<UserDynamicSourceInfoBean> userDynamicSourceInfoBeanList){
        this.userDynamicSourceInfoBeanList = userDynamicSourceInfoBeanList;
    }
    public Date getForwardTime(){
        return this.forwardTime;
    }

    public void setForwardTime(Date forwardTime){
        this.forwardTime = forwardTime;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}