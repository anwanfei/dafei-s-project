package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class BookShelfBean implements Serializable{

    /**
    *书籍ID集合
    */
    private List<String> bookIdList;
    /**
    *用户ID
    */
    private String userId;
    private String id;

    public List<String> getBookIdList(){
        return this.bookIdList;
    }

    public void setBookIdList(List<String> bookIdList){
        this.bookIdList = bookIdList;
    }
    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}