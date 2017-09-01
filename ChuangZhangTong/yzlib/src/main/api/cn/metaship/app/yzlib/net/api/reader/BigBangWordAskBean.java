package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;

public final class BigBangWordAskBean implements Serializable{

    /**
    *提问发布时间
    */
    private Date releaseTime;
    /**
    *回答数量
    */
    private Integer answerCount;
    /**
    *章节ID
    */
    private String chapterId;
    /**
    *单词ID
    */
    private String wordId;
    private String id;
    /**
    *书籍ID
    */
    private String bookId;
    /**
    *提问用户ID
    */
    private String askUserId;
    /**
    *提问内容
    */
    private String askContent;

    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public Integer getAnswerCount(){
        return this.answerCount;
    }

    public void setAnswerCount(Integer answerCount){
        this.answerCount = answerCount;
    }
    public String getChapterId(){
        return this.chapterId;
    }

    public void setChapterId(String chapterId){
        this.chapterId = chapterId;
    }
    public String getWordId(){
        return this.wordId;
    }

    public void setWordId(String wordId){
        this.wordId = wordId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public String getAskUserId(){
        return this.askUserId;
    }

    public void setAskUserId(String askUserId){
        this.askUserId = askUserId;
    }
    public String getAskContent(){
        return this.askContent;
    }

    public void setAskContent(String askContent){
        this.askContent = askContent;
    }

}