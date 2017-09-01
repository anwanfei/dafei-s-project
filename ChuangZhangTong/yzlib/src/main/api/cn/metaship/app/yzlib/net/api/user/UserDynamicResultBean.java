package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class UserDynamicResultBean implements Serializable{

    /**
    *原动态标题
    */
    private String originalDynamicTitle;
    /**
    *原动态朗读者名称
    */
    private String originalDynamicReaderName;
    /**
    *作品是否参赛（如果是作品才设置）
    */
    private Integer isJoinMatch;
    /**
    *原动态作者名称
    */
    private String originalDynamicAuthorName;
    /**
    *转发描述
    */
    private String forwardDescription;
    /**
    *转发自：转发id, 转发人id， 转发人名称，转发描述内容
    */
    private List<UserDynamicSourceInfoBean> userDynamicSourceInfoBeanList;
    /**
    *原动态发表内容
    */
    private String originalDynamicContent;
    /**
    *转发ID
    */
    private String forwardId;
    /**
    *原动态命题
    */
    private String originalDynamicProposition;
    /**
    *用户ID
    */
    private String userId;
    /**
    *原动态表演者名称
    */
    private String originalDynamicPerformerName;
    /**
    *动态类型（1新作、2新作转发、3抢票赛作品、4接力赛作品、5朗读赛作品、6表演赛作品7抢票赛转发）
    */
    private Byte dynamicType;
    /**
    *发布时间
    */
    private Date releaseTime;
    /**
    *原动态作者ID
    */
    private String originalDynamicAuthorId;
    /**
    *原动态音视频URL
    */
    private String originalDynamicMediaUrl;
    /**
    *用户昵称
    */
    private String nickName;
    /**
    *用户头像URL
    */
    private String avatarUrl;
    /**
    *原动态ID
    */
    private String originalDynamicId;
    /**
    *用户点赞状态:1-已点赞2-未点赞
    */
    private Integer isLike;

    public String getOriginalDynamicTitle(){
        return this.originalDynamicTitle;
    }

    public void setOriginalDynamicTitle(String originalDynamicTitle){
        this.originalDynamicTitle = originalDynamicTitle;
    }
    public String getOriginalDynamicReaderName(){
        return this.originalDynamicReaderName;
    }

    public void setOriginalDynamicReaderName(String originalDynamicReaderName){
        this.originalDynamicReaderName = originalDynamicReaderName;
    }
    public Integer getIsJoinMatch(){
        return this.isJoinMatch;
    }

    public void setIsJoinMatch(Integer isJoinMatch){
        this.isJoinMatch = isJoinMatch;
    }
    public String getOriginalDynamicAuthorName(){
        return this.originalDynamicAuthorName;
    }

    public void setOriginalDynamicAuthorName(String originalDynamicAuthorName){
        this.originalDynamicAuthorName = originalDynamicAuthorName;
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
    public String getOriginalDynamicContent(){
        return this.originalDynamicContent;
    }

    public void setOriginalDynamicContent(String originalDynamicContent){
        this.originalDynamicContent = originalDynamicContent;
    }
    public String getForwardId(){
        return this.forwardId;
    }

    public void setForwardId(String forwardId){
        this.forwardId = forwardId;
    }
    public String getOriginalDynamicProposition(){
        return this.originalDynamicProposition;
    }

    public void setOriginalDynamicProposition(String originalDynamicProposition){
        this.originalDynamicProposition = originalDynamicProposition;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getOriginalDynamicPerformerName(){
        return this.originalDynamicPerformerName;
    }

    public void setOriginalDynamicPerformerName(String originalDynamicPerformerName){
        this.originalDynamicPerformerName = originalDynamicPerformerName;
    }
    public Byte getDynamicType(){
        return this.dynamicType;
    }

    public void setDynamicType(Byte dynamicType){
        this.dynamicType = dynamicType;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public String getOriginalDynamicAuthorId(){
        return this.originalDynamicAuthorId;
    }

    public void setOriginalDynamicAuthorId(String originalDynamicAuthorId){
        this.originalDynamicAuthorId = originalDynamicAuthorId;
    }
    public String getOriginalDynamicMediaUrl(){
        return this.originalDynamicMediaUrl;
    }

    public void setOriginalDynamicMediaUrl(String originalDynamicMediaUrl){
        this.originalDynamicMediaUrl = originalDynamicMediaUrl;
    }
    public String getNickName(){
        return this.nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public String getOriginalDynamicId(){
        return this.originalDynamicId;
    }

    public void setOriginalDynamicId(String originalDynamicId){
        this.originalDynamicId = originalDynamicId;
    }
    public Integer getIsLike(){
        return this.isLike;
    }

    public void setIsLike(Integer isLike){
        this.isLike = isLike;
    }

}