package cn.metaship.app.yzlib.net.api.user;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class PersonalDynamicBean implements Serializable{

    private String nickname;
    private String forwardDescription;
    private List<UserDynamicSourceInfoBean> userDynamicSourceInfoBeanList;
    private String originalDynamicContent;
    private String originalDynamicTitle;
    private String originalDynamicReaderName;
    private Integer isJoinMatch;
    private String originalDynamicAuthorName;
    private String userId;
    private String originalDynamicPerformerName;
    private Byte dynamicType;
    private String forwardId;
    private String originalDynamicProposition;
    private String avatarUrl;
    private String originalDynamicId;
    /**
    *用户点赞状态:1-已点赞2-未点赞
    */
    private Integer isLike;
    private Date releaseTime;
    private String originalDynamicAuthorId;
    private String originalDynamicMediaUrl;

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
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

}