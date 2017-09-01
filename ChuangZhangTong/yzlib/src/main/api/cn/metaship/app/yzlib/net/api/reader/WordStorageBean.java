package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class WordStorageBean implements Serializable{

    private BigBangWordAdoptBean bigBangWordAdoptBean;
    private String bookName;

    public BigBangWordAdoptBean getBigBangWordAdoptBean(){
        return this.bigBangWordAdoptBean;
    }

    public void setBigBangWordAdoptBean(BigBangWordAdoptBean bigBangWordAdoptBean){
        this.bigBangWordAdoptBean = bigBangWordAdoptBean;
    }
    public String getBookName(){
        return this.bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

}