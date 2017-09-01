package cn.metaship.app.yzlib.net.api.search;

import java.io.Serializable;

public final class SearchBean implements Serializable{

    /**
    *用户头像url
    */
    private String authorAvatarurl;
    /**
    *用户名称
    */
    private String authorName;
    /**
    *书籍图片url、课程video的url
    */
    private String entityUrl;
    /**
    *搜索出的标题，文章为文章标题，书籍为书籍名称，用户为空，课程为课程标题
    */
    private String title;
    /**
    *用户id
    */
    private String authorId;
    /**
    *搜索出的类型。1-书籍2-课程3-作文4-用户
    */
    private Integer type;
    /**
    *描述，书籍描述，课程简介，作文摘要，用户签名
    */
    private String desc;
    /**
    *搜索出的对象id
    */
    private String id;

    public String getAuthorAvatarurl(){
        return this.authorAvatarurl;
    }

    public void setAuthorAvatarurl(String authorAvatarurl){
        this.authorAvatarurl = authorAvatarurl;
    }
    public String getAuthorName(){
        return this.authorName;
    }

    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }
    public String getEntityUrl(){
        return this.entityUrl;
    }

    public void setEntityUrl(String entityUrl){
        this.entityUrl = entityUrl;
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
    public Integer getType(){
        return this.type;
    }

    public void setType(Integer type){
        this.type = type;
    }
    public String getDesc(){
        return this.desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}