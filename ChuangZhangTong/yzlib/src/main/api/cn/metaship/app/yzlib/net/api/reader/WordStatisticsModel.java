package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class WordStatisticsModel implements Serializable{

    /**
    *领养次数
    */
    private Integer adoptCount;
    /**
    *单词内容
    */
    private String wordContent;
    private String id;

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