package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class WordStoreResultBean implements Serializable{

    private String wordContent;
    private String wordId;
    private List<WordBaseModel> wordBaseModels;

    public String getWordContent(){
        return this.wordContent;
    }

    public void setWordContent(String wordContent){
        this.wordContent = wordContent;
    }
    public String getWordId(){
        return this.wordId;
    }

    public void setWordId(String wordId){
        this.wordId = wordId;
    }
    public List<WordBaseModel> getWordBaseModels(){
        return this.wordBaseModels;
    }

    public void setWordBaseModels(List<WordBaseModel> wordBaseModels){
        this.wordBaseModels = wordBaseModels;
    }

}