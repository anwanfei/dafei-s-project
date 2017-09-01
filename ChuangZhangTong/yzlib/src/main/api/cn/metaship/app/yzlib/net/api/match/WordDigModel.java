package cn.metaship.app.yzlib.net.api.match;

import java.io.Serializable;

public final class WordDigModel implements Serializable{

    private Integer endPosition;
    private Integer feature;
    private String bookId;
    private Integer startPosition;
    private Integer paragraphId;

    public Integer getEndPosition(){
        return this.endPosition;
    }

    public void setEndPosition(Integer endPosition){
        this.endPosition = endPosition;
    }
    public Integer getFeature(){
        return this.feature;
    }

    public void setFeature(Integer feature){
        this.feature = feature;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public Integer getStartPosition(){
        return this.startPosition;
    }

    public void setStartPosition(Integer startPosition){
        this.startPosition = startPosition;
    }
    public Integer getParagraphId(){
        return this.paragraphId;
    }

    public void setParagraphId(Integer paragraphId){
        this.paragraphId = paragraphId;
    }

}