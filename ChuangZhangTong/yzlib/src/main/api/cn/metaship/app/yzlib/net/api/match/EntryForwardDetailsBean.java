package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class EntryForwardDetailsBean implements Serializable{

    /**
    *发表时间
    */
    private Date releaseTime;
    /**
    *用户头像
    */
    private String avatarUrl;
    /**
    *点赞量
    */
    private Integer likeCount;
    /**
    *原作品发表内容
    */
    private String content;
    /**
    *原作品ID
    */
    private String entryId;
    /**
    *评论量
    */
    private Integer commentCount;
    /**
    *作品标题
    */
    private String title;
    /**
    *原作品作者ID
    */
    private String authorId;
    /**
    *用户ID
    */
    private String userId;
    /**
    *原作品是否参赛（如果是作品才设置）
    */
    private Integer isJoinMatch;
    /**
    *原作品作者名称
    */
    private String authorName;
    /**
    *转发描述
    */
    private String forwardDescription;
    /**
    *转发自
    */
    private List<UserDynamicSourceInfoBean> userDynamicSourceInfoBeanList;
    /**
    *昵称
    */
    private String nickname;
    /**
    *动态记录ID
    */
    private String forwardEntryId;

    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public Integer getLikeCount(){
        return this.likeCount;
    }

    public void setLikeCount(Integer likeCount){
        this.likeCount = likeCount;
    }
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }
    public String getEntryId(){
        return this.entryId;
    }

    public void setEntryId(String entryId){
        this.entryId = entryId;
    }
    public Integer getCommentCount(){
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
    }
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getAuthorId(){
        return this.authorId;
    }

    public void setAuthorId(String authorId){
        this.authorId = authorId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public Integer getIsJoinMatch(){
        return this.isJoinMatch;
    }

    public void setIsJoinMatch(Integer isJoinMatch){
        this.isJoinMatch = isJoinMatch;
    }
    public String getAuthorName(){
        return this.authorName;
    }

    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }
    public String getForwardDescription(){
        return this.forwardDescription;
    }

    public void setForwardDescription(String forwardDescription){
        this.forwardDescription = forwardDescription;
    }
    public List<UserDynamicSourceInfoBean> getUserDynamicSourceInfoBeanList(){
        return this.userDynamicSourceInfoBeanList;
    }

    public void setUserDynamicSourceInfoBeanList(List<UserDynamicSourceInfoBean> userDynamicSourceInfoBeanList){
        this.userDynamicSourceInfoBeanList = userDynamicSourceInfoBeanList;
    }
    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public String getForwardEntryId(){
        return this.forwardEntryId;
    }

    public void setForwardEntryId(String forwardEntryId){
        this.forwardEntryId = forwardEntryId;
    }

}