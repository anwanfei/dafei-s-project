package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BookProgressBean implements Serializable{

    /**
    *阅读进度
    */
    private Double progress;
    /**
    *书籍ID
    */
    private String bookId;
    /**
    *用户ID
    */
    private String userId;
    /**
    *书架ID
    */
    private String bookShelfId;
    private String id;

    public Double getProgress(){
        return this.progress;
    }

    public void setProgress(Double progress){
        this.progress = progress;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getBookShelfId(){
        return this.bookShelfId;
    }

    public void setBookShelfId(String bookShelfId){
        this.bookShelfId = bookShelfId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}