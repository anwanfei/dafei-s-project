package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class TxtLinesBean implements Serializable{

    private Integer endPositionNo;
    private Integer startPositionNo;

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