package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class LinePositionIdeaBean implements Serializable{

    /**
    *划线的开始段落
    */
    private Integer startParagraph;
    /**
    *划线部分的感想ID
    */
    private String ideaId;
    /**
    *划线的结束段落
    */
    private Integer endParagraph;
    /**
    *划线的开始段落的开始位置
    */
    private Integer startOffset;
    /**
    *划线的开始段落的结束位置
    */
    private Integer endOffset;

    public Integer getStartParagraph(){
        return this.startParagraph;
    }

    public void setStartParagraph(Integer startParagraph){
        this.startParagraph = startParagraph;
    }
    public String getIdeaId(){
        return this.ideaId;
    }

    public void setIdeaId(String ideaId){
        this.ideaId = ideaId;
    }
    public Integer getEndParagraph(){
        return this.endParagraph;
    }

    public void setEndParagraph(Integer endParagraph){
        this.endParagraph = endParagraph;
    }
    public Integer getStartOffset(){
        return this.startOffset;
    }

    public void setStartOffset(Integer startOffset){
        this.startOffset = startOffset;
    }
    public Integer getEndOffset(){
        return this.endOffset;
    }

    public void setEndOffset(Integer endOffset){
        this.endOffset = endOffset;
    }

}