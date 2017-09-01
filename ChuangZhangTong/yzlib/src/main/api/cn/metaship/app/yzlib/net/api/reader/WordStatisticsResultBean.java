package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class WordStatisticsResultBean implements Serializable{

    /**
    *单词总数
    */
    private Integer wordsCount;
    /**
    *当前单词序号
    */
    private Integer currentWordCode;
    /**
    *单词统计结果
    */
    private List<WordStatisticsModel> wordStatisticsModels;

    public Integer getWordsCount(){
        return this.wordsCount;
    }

    public void setWordsCount(Integer wordsCount){
        this.wordsCount = wordsCount;
    }
    public Integer getCurrentWordCode(){
        return this.currentWordCode;
    }

    public void setCurrentWordCode(Integer currentWordCode){
        this.currentWordCode = currentWordCode;
    }
    public List<WordStatisticsModel> getWordStatisticsModels(){
        return this.wordStatisticsModels;
    }

    public void setWordStatisticsModels(List<WordStatisticsModel> wordStatisticsModels){
        this.wordStatisticsModels = wordStatisticsModels;
    }

}