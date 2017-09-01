package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class BigBangWordModel implements Serializable{

    /**
    *章节ID
    */
    private String chapterId;
    /**
    *回答数量
    */
    private Integer answerCount;
    /**
    *txt格式的单词的位置
    */
    private TxtWordPositionBean txtWordPositionBean;
    /**
    *epub格式的单词的位置
    */
    private EpubWordPositionBean epubWordPositionBean;
    /**
    *单词基础信息列表
    */
    private List<WordBaseModel> wordBaseModels;
    /**
    *书籍ID
    */
    private String bookId;
    /**
    *提问数量
    */
    private Integer askCount;
    /**
    *领养数量
    */
    private Integer adoptCount;
    /**
    *单词内容
    */
    private String wordContent;
    private String id;

    public String getChapterId(){
        return this.chapterId;
    }

    public void setChapterId(String chapterId){
        this.chapterId = chapterId;
    }
    public Integer getAnswerCount(){
        return this.answerCount;
    }

    public void setAnswerCount(Integer answerCount){
        this.answerCount = answerCount;
    }
    public TxtWordPositionBean getTxtWordPositionBean(){
        return this.txtWordPositionBean;
    }

    public void setTxtWordPositionBean(TxtWordPositionBean txtWordPositionBean){
        this.txtWordPositionBean = txtWordPositionBean;
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
    public String getBookId(){
        return this.bookId;
    }

    public void setBookId(String bookId){
        this.bookId = bookId;
    }
    public Integer getAskCount(){
        return this.askCount;
    }

    public void setAskCount(Integer askCount){
        this.askCount = askCount;
    }
    public Integer getAdoptCount(){
        return this.adoptCount;
    }

    public void setAdoptCount(Integer adoptCount){
        this.adoptCount = adoptCount;
    }
    public String getWordContent(){
        return this.wordContent;
    }

    public void setWordContent(String wordContent){
        this.wordContent = wordContent;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}