package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class TxtWordPositionBean implements Serializable{

    /**
    *单词起始位置
    */
    private Integer startPosition;
    /**
    *单词终止位置
    */
    private Integer endPosition;

    public Integer getStartPosition(){
        return this.startPosition;
    }

    public void setStartPosition(Integer startPosition){
        this.startPosition = startPosition;
    }
    public Integer getEndPosition(){
        return this.endPosition;
    }

    public void setEndPosition(Integer endPosition){
        this.endPosition = endPosition;
    }

}