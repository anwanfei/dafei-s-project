package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BookChapterBean implements Serializable{

    private String bookId;
    private String chapterName;
    private String id;
    private Double price;
    private Integer chapterCode;

    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public String getChapterName(){
        return this.chapterName;
    }

    public void setChapterName(String chapterName){
        this.chapterName = chapterName;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public Double getPrice(){
        return this.price;
    }

    public void setPrice(Double price){
        this.price = price;
    }
    public Integer getChapterCode(){
        return this.chapterCode;
    }

    public void setChapterCode(Integer chapterCode){
        this.chapterCode = chapterCode;
    }

}