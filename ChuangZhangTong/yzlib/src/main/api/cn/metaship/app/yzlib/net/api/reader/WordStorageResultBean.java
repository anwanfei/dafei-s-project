package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class WordStorageResultBean implements Serializable{

    private List<WordStorageBean> wordStorageBeanList;
    private Integer count;

    public List<WordStorageBean> getWordStorageBeanList(){
        return this.wordStorageBeanList;
    }

    public void setWordStorageBeanList(List<WordStorageBean> wordStorageBeanList){
        this.wordStorageBeanList = wordStorageBeanList;
    }
    public Integer getCount(){
        return this.count;
    }

    public void setCount(Integer count){
        this.count = count;
    }

}