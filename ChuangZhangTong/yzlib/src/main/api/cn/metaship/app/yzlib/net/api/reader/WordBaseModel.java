package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class WordBaseModel implements Serializable{

    /**
    *词性,分名词，动词等
    */
    private Integer feature;
    /**
    *拼音
    */
    private String pinYin;
    /**
    *解释
    */
    private String explain;

    public Integer getFeature(){
        return this.feature;
    }

    public void setFeature(Integer feature){
        this.feature = feature;
    }
    public String getPinYin(){
        return this.pinYin;
    }

    public void setPinYin(String pinYin){
        this.pinYin = pinYin;
    }
    public String getExplain(){
        return this.explain;
    }

    public void setExplain(String explain){
        this.explain = explain;
    }

}