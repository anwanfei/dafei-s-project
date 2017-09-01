package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class LinesIdeaRequestBean implements Serializable{

    private LinesIdeaBean linesIdeaBean;
    private ParagraphLineBean paragraphLineBean;
    private LinesContentBean linesContentBean;

    public LinesIdeaBean getLinesIdeaBean(){
        return this.linesIdeaBean;
    }

    public void setLinesIdeaBean(LinesIdeaBean linesIdeaBean){
        this.linesIdeaBean = linesIdeaBean;
    }
    public ParagraphLineBean getParagraphLineBean(){
        return this.paragraphLineBean;
    }

    public void setParagraphLineBean(ParagraphLineBean paragraphLineBean){
        this.paragraphLineBean = paragraphLineBean;
    }
    public LinesContentBean getLinesContentBean(){
        return this.linesContentBean;
    }

    public void setLinesContentBean(LinesContentBean linesContentBean){
        this.linesContentBean = linesContentBean;
    }

}