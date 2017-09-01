package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class EpubLinesBean implements Serializable{

    private Integer startChapterNo;
    private Integer endChapterNo;
    private Integer startParagraphNo;
    private Integer endParagraphNo;
    private Integer endPositionNo;
    private Integer startPositionNo;

    public Integer getStartChapterNo(){
        return this.startChapterNo;
    }

    public void setStartChapterNo(Integer startChapterNo){
        this.startChapterNo = startChapterNo;
    }
    public Integer getEndChapterNo(){
        return this.endChapterNo;
    }

    public void setEndChapterNo(Integer endChapterNo){
        this.endChapterNo = endChapterNo;
    }
    public Integer getStartParagraphNo(){
        return this.startParagraphNo;
    }

    public void setStartParagraphNo(Integer startParagraphNo){
        this.startParagraphNo = startParagraphNo;
    }
    public Integer getEndParagraphNo(){
        return this.endParagraphNo;
    }

    public void setEndParagraphNo(Integer endParagraphNo){
        this.endParagraphNo = endParagraphNo;
    }
    public Integer getEndPositionNo(){
        return this.endPositionNo;
    }

    public void setEndPositionNo(Integer endPositionNo){
        this.endPositionNo = endPositionNo;
    }
    public Integer getStartPositionNo(){
        return this.startPositionNo;
    }

    public void setStartPositionNo(Integer startPositionNo){
        this.startPositionNo = startPositionNo;
    }

}