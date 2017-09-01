package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.Date;

public final class LinesContentBean implements Serializable{

    /**
    *书籍ID
    */
    private String bookId;
    /**
    *txt格式的划线对象
    */
    private TxtLinesBean txtLinesBean;
    /**
    *当前划线对象的感想数量
    */
    private Integer ideaCount;
    /**
    *用户ID
    */
    private String userId;
    /**
    *epub格式的划线对象
    */
    private EpubLinesBean epubLinesBean;
    private String id;
    /**
    *划线时间
    */
    private Date drawTime;

    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public TxtLinesBean getTxtLinesBean(){
        return this.txtLinesBean;
    }

    public void setTxtLinesBean(TxtLinesBean txtLinesBean){
        this.txtLinesBean = txtLinesBean;
    }
    public Integer getIdeaCount(){
        return this.ideaCount;
    }

    public void setIdeaCount(Integer ideaCount){
        this.ideaCount = ideaCount;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public EpubLinesBean getEpubLinesBean(){
        return this.epubLinesBean;
    }

    public void setEpubLinesBean(EpubLinesBean epubLinesBean){
        this.epubLinesBean = epubLinesBean;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public Date getDrawTime(){
        return this.drawTime;
    }

    public void setDrawTime(Date drawTime){
        this.drawTime = drawTime;
    }

}