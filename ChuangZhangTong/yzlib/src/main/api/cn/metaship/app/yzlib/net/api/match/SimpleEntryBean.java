package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;
import java.util.List;

public final class SimpleEntryBean implements Serializable{

    /**
    *指导老师id
    */
    private String teacherId;
    /**
    *参赛组名
    */
    private String groupName;
    /**
    *参赛单元.主题、诗歌、小说、散文、金句、对联、其他
    */
    private String unitCode;
    /**
    *作者名称
    */
    private String authorName;
    /**
    *参赛组编码
    */
    private String groupCode;
    /**
    *参加大赛id
    */
    private String matchId;
    /**
    *比赛阶段编码
    */
    private String matchStage;
    /**
    *作文类型，1-文本2-图片
    */
    private Integer entryType;
    /**
    *指导老师名称
    */
    private String teacherName;
    /**
    *上传渠道.web-1 android-2 ios-3 wap-4
    */
    private Integer channel;
    private String id;
    /**
    *作文内容
    */
    private String content;
    /**
    *作品图片列表
    */
    private List<EntryPictureModel> entryPictures;
    /**
    *作文题目
    */
    private String title;
    /**
    *作者id
    */
    private String authorId;

    public String getTeacherId(){
        return this.teacherId;
    }

    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }
    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }
    public String getUnitCode(){
        return this.unitCode;
    }

    public void setUnitCode(String unitCode){
        this.unitCode = unitCode;
    }
    public String getAuthorName(){
        return this.authorName;
    }

    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }
    public String getGroupCode(){
        return this.groupCode;
    }

    public void setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }
    public String getMatchId(){
        return this.matchId;
    }

    public void setMatchId(String matchId){
        this.matchId = matchId;
    }
    public String getMatchStage(){
        return this.matchStage;
    }

    public void setMatchStage(String matchStage){
        this.matchStage = matchStage;
    }
    public Integer getEntryType(){
        return this.entryType;
    }

    public void setEntryType(Integer entryType){
        this.entryType = entryType;
    }
    public String getTeacherName(){
        return this.teacherName;
    }

    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }
    public Integer getChannel(){
        return this.channel;
    }

    public void setChannel(Integer channel){
        this.channel = channel;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }
    public List<EntryPictureModel> getEntryPictures(){
        return this.entryPictures;
    }

    public void setEntryPictures(List<EntryPictureModel> entryPictures){
        this.entryPictures = entryPictures;
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

}