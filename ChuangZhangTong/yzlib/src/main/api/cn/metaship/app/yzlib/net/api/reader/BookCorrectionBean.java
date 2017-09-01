package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BookCorrectionBean implements Serializable{

    /**
    *纠错类型
    */
    private Byte correctionType;
    /**
    *纠错内容
    */
    private String correctionContent;
    /**
    *书籍ID
    */
    private String bookId;
    private String id;

    public Byte getCorrectionType(){
        return this.correctionType;
    }

    public void setCorrectionType(Byte correctionType){
        this.correctionType = correctionType;
    }
    public String getCorrectionContent(){
        return this.correctionContent;
    }

    public void setCorrectionContent(String correctionContent){
        this.correctionContent = correctionContent;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}