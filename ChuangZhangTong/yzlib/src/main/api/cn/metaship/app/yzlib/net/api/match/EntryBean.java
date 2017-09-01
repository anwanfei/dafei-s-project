package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class EntryBean implements Serializable{

    /**
    *参赛时间
    */
    private Date joinMatchTime;
    /**
    *提交到作品库时间
    */
    private Date submitToLibTime;
    /**
    *作品标题
    */
    private String title;
    /**
    *作品图片
    */
    private List<EntryPictureModel> entryPictures;
    /**
    *作文内容
    */
    private String content;
    /**
    *是否草稿.1-是2-否
    */
    private Integer isDraft;
    /**
    *参赛作品编号
    */
    private String matchEntryCode;
    /**
    *踩量
    */
    private Integer notlikedCount;
    /**
    *上传渠道.web-1 android-2 ios-3 wap-4
    */
    private Integer channel;
    /**
    *专家评语
    */
    private ExpertCommentModel expertComment;
    /**
    *参赛单元.主题、诗歌、小说、散文、金句、对联、其他
    */
    private String unitCode;
    /**
    *转发量
    */
    private Integer forwardCount;
    /**
    *提交作品的状态，未提交-1提交中-2提交成功-3
    */
    private Integer submitState;
    /**
    *参加大赛id
    */
    private String matchId;
    /**
    *参赛组：小学、初中等
    */
    private String groupCode;
    private String id;
    /**
    *指导老师名称
    */
    private String teacherName;
    /**
    *用户头像URL
    */
    private String avatarUrl;
    /**
    *文本类型的作文，作文摘要，从文本中截取一部分
    */
    private String summary;
    /**
    *作文类型，1-文本2-图片
    */
    private Integer entryType;
    /**
    *图片类型的作文，首图地址
    */
    private String firstImageUrl;
    /**
    *作者ID
    */
    private String authorId;
    /**
    *浏览量
    */
    private Integer browserCount;
    /**
    *评论量
    */
    private Integer commentCount;
    /**
    *点赞量
    */
    private Integer likedCount;
    /**
    *书写时间
    */
    private Date createTime;
    /**
    *作者名称
    */
    private String authorName;
    /**
    *最后修改时间
    */
    private Date lastModifyTime;
    /**
    *是否参赛
    */
    private Integer isJoinMatch;
    /**
    *参赛组名
    */
    private String groupName;
    /**
    *指导老师id
    */
    private String teacherId;
    /**
    *比赛阶段
    */
    private String matchStage;
    /**
    *作品参赛赛区
    */
    private String zoneCode;
    /**
    *投票量
    */
    private Integer voteCount;

    public Date getJoinMatchTime(){
        return this.joinMatchTime;
    }

    public void setJoinMatchTime(Date joinMatchTime){
        this.joinMatchTime = joinMatchTime;
    }
    public Date getSubmitToLibTime(){
        return this.submitToLibTime;
    }

    public void setSubmitToLibTime(Date submitToLibTime){
        this.submitToLibTime = submitToLibTime;
    }
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public List<EntryPictureModel> getEntryPictures(){
        return this.entryPictures;
    }

    public void setEntryPictures(List<EntryPictureModel> entryPictures){
        this.entryPictures = entryPictures;
    }
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }
    public Integer getIsDraft(){
        return this.isDraft;
    }

    public void setIsDraft(Integer isDraft){
        this.isDraft = isDraft;
    }
    public String getMatchEntryCode(){
        return this.matchEntryCode;
    }

    public void setMatchEntryCode(String matchEntryCode){
        this.matchEntryCode = matchEntryCode;
    }
    public Integer getNotlikedCount(){
        return this.notlikedCount;
    }

    public void setNotlikedCount(Integer notlikedCount){
        this.notlikedCount = notlikedCount;
    }
    public Integer getChannel(){
        return this.channel;
    }

    public void setChannel(Integer channel){
        this.channel = channel;
    }
    public ExpertCommentModel getExpertComment(){
        return this.expertComment;
    }

    public void setExpertComment(ExpertCommentModel expertComment){
        this.expertComment = expertComment;
    }
    public String getUnitCode(){
        return this.unitCode;
    }

    public void setUnitCode(String unitCode){
        this.unitCode = unitCode;
    }
    public Integer getForwardCount(){
        return this.forwardCount;
    }

    public void setForwardCount(Integer forwardCount){
        this.forwardCount = forwardCount;
    }
    public Integer getSubmitState(){
        return this.submitState;
    }

    public void setSubmitState(Integer submitState){
        this.submitState = submitState;
    }
    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public String getGroupCode(){
        return this.groupCode;
    }

    public void setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getTeacherName(){
        return this.teacherName;
    }

    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }
    public String getAvatarUrl(){
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }
    public String getSummary(){
        return this.summary;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }
    public Integer getEntryType(){
        return this.entryType;
    }

    public void setEntryType(Integer entryType){
        this.entryType = entryType;
    }
    public String getFirstImageUrl(){
        return this.firstImageUrl;
    }

    public void setFirstImageUrl(String firstImageUrl){
        this.firstImageUrl = firstImageUrl;
    }
    public String getAuthorId(){
        return this.authorId;
    }

    public void setAuthorId(String authorId){
        this.authorId = authorId;
    }
    public Integer getBrowserCount(){
        return this.browserCount;
    }

    public void setBrowserCount(Integer browserCount){
        this.browserCount = browserCount;
    }
    public Integer getCommentCount(){
        return this.commentCount;
    }

    public void setCommentCount(Integer commentCount){
        this.commentCount = commentCount;
    }
    public Integer getLikedCount(){
        return this.likedCount;
    }

    public void setLikedCount(Integer likedCount){
        this.likedCount = likedCount;
    }
    public Date getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public String getAuthorName(){
        return this.authorName;
    }

    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }
    public Date getLastModifyTime(){
        return this.lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime){
        this.lastModifyTime = lastModifyTime;
    }
    public Integer getIsJoinMatch(){
        return this.isJoinMatch;
    }

    public void setIsJoinMatch(Integer isJoinMatch){
        this.isJoinMatch = isJoinMatch;
    }
    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public String getTeacherId(){
        return this.teacherId;
    }

    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }
    public String getMatchStage(){
        return this.matchStage;
    }

    public void setMatchStage(String matchStage){
        this.matchStage = matchStage;
    }
    public String getZoneCode(){
        return this.zoneCode;
    }

    public void setZoneCode(String zoneCode){
        this.zoneCode = zoneCode;
    }
    public Integer getVoteCount(){
        return this.voteCount;
    }

    public void setVoteCount(Integer voteCount){
        this.voteCount = voteCount;
    }

}