package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class EpubWordPositionBean implements Serializable{

    /**
    *章节ID
    */
    private Integer chapterNo;
    /**
    *单词终止位置
    */
    private Integer endPosition;
    /**
    *单词起始位置
    */
    private Integer startPosition;
    /**
    *段落ID
    */
    private Integer paragraphNo;

    public Integer getChapterNo(){
        return this.chapterNo;
    }

    public void setChapterNo(Integer chapterNo){
        this.chapterNo = chapterNo;
    }
    public Integer getEndPosition(){
        return this.endPosition;
    }

    public void setEndPosition(Integer endPosition){
        this.endPosition = endPosition;
    }
    public Integer getStartPosition(){
        return this.startPosition;
    }

    public void setStartPosition(Integer startPosition){
        this.startPosition = startPosition;
    }
    public Integer getParagraphNo(){
        return this.paragraphNo;
    }

    public void setParagraphNo(Integer paragraphNo){
        this.paragraphNo = paragraphNo;
    }

}