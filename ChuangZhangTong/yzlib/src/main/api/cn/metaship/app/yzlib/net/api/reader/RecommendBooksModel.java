package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;

public final class RecommendBooksModel implements Serializable{

    /**
    *书籍ID
    */
    private String bookId;
    /**
    *教育等级
    */
    private String educationLevelName;
    /**
    *加入必读书单时间
    */
    private Date joinTime;
    private String id;

    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public String getEducationLevelName(){
        return this.educationLevelName;
    }

    public void setEducationLevelName(String educationLevelName){
        this.educationLevelName = educationLevelName;
    }
    public Date getJoinTime(){
        return this.joinTime;
    }

    public void setJoinTime(Date joinTime){
        this.joinTime = joinTime;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}