package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class ParagraphLineBean implements Serializable{

    /**
    *书籍ID
    */
    private String bookId;
    private String id;
    /**
    *章节ID
    */
    private Integer chapterId;
    private List<LinePositionIdeaBean> linePositionIdeaBeen;

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
    public Integer getChapterId(){
        return this.chapterId;
    }

    public void setChapterId(Integer chapterId){
        this.chapterId = chapterId;
    }
    public List<LinePositionIdeaBean> getLinePositionIdeaBeen(){
        return this.linePositionIdeaBeen;
    }

    public void setLinePositionIdeaBeen(List<LinePositionIdeaBean> linePositionIdeaBeen){
        this.linePositionIdeaBeen = linePositionIdeaBeen;
    }

}