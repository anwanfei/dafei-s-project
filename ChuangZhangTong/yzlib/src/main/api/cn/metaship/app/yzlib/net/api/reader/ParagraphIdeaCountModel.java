package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class ParagraphIdeaCountModel implements Serializable{

    /**
    *该段落的感想数量
    */
    private Integer ideaCount;
    /**
    *书籍ID
    */
    private String bookId;
    /**
    *章节ID
    */
    private Integer chapterId;
    private String id;
    /**
    *段落编号
    */
    private Integer paragraph;

    public Integer getIdeaCount(){
        return this.ideaCount;
    }

    public void setIdeaCount(Integer ideaCount){
        this.ideaCount = ideaCount;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public Integer getChapterId(){
        return this.chapterId;
    }

    public void setChapterId(Integer chapterId){
        this.chapterId = chapterId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public Integer getParagraph(){
        return this.paragraph;
    }

    public void setParagraph(Integer paragraph){
        this.paragraph = paragraph;
    }

}