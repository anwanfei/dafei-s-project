package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class SimpleBookDetailsBean implements Serializable{

    private String author;
    private String bookCoverUrl;
    private String bookId;
    private String bookName;

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public String getBookCoverUrl(){
        return this.bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl){
        this.bookCoverUrl = bookCoverUrl;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public String getBookName(){
        return this.bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

}