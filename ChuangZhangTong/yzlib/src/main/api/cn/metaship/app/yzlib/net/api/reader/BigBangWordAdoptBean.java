package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class BigBangWordAdoptBean implements Serializable{

    /**
    *领养用户ID
    */
    private String adopterId;
    /**
    *epub单词位置
    */
    private EpubWordPositionBean epubWordPositionBean;
    /**
    *单词基本信息
    */
    private List<WordBaseModel> wordBaseModels;
    /**
    *txt单词位置
    */
    private TxtWordPositionBean txtWordPositionBean;
    /**
    *单词ID
    */
    private String wordId;
    private String id;
    /**
    *领养时间
    */
    private Date adoptTime;
    /**
    *单词内容
    */
    private String content;
    /**
    *书籍ID
    */
    private String bookId;

    public String getAdopterId(){
        return this.adopterId;
    }

    public void setAdopterId(String adopterId){
        this.adopterId = adopterId;
    }
    public EpubWordPositionBean getEpubWordPositionBean(){
        return this.epubWordPositionBean;
    }

    public void setEpubWordPositionBean(EpubWordPositionBean epubWordPositionBean){
        this.epubWordPositionBean = epubWordPositionBean;
    }
    public List<WordBaseModel> getWordBaseModels(){
        return this.wordBaseModels;
    }

    public void setWordBaseModels(List<WordBaseModel> wordBaseModels){
        this.wordBaseModels = wordBaseModels;
    }
    public TxtWordPositionBean getTxtWordPositionBean(){
        return this.txtWordPositionBean;
    }

    public void setTxtWordPositionBean(TxtWordPositionBean txtWordPositionBean){
        this.txtWordPositionBean = txtWordPositionBean;
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
    public Date getAdoptTime(){
        return this.adoptTime;
    }

    public void setAdoptTime(Date adoptTime){
        this.adoptTime = adoptTime;
    }
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }

}