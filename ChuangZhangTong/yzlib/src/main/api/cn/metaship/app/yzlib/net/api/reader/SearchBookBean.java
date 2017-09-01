package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class SearchBookBean implements Serializable{

    private String authorName;
    private String coverUrl;
    private String summary;
    private Integer readHot;
    private String bookName;
    private String bookId;

    public String getAuthorName(){
        return this.authorName;
    }

    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }
    public String getCoverUrl(){
        return this.coverUrl;
    }

    public void setCoverUrl(String coverUrl){
        this.coverUrl = coverUrl;
    }
    public String getSummary(){
        return this.summary;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }
    public Integer getReadHot(){
        return this.readHot;
    }

    public void setReadHot(Integer readHot){
        this.readHot = readHot;
    }
    public String getBookName(){
        return this.bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }

}