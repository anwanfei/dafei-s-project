package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;

public final class KnowledgePageBean implements Serializable{

    /**
    *当前书籍的分类信息及所有的祖宗分类名称信息
    */
    private List<String> commodityCategoryNameList;
    /**
    *bigbang单词领养条目排行
    */
    private List<String> bigBangWordAdopt;
    /**
    *有多少词汇被领养
    */
    private Long adoptWordCount;
    /**
    *书籍对象
    */
    private BooksModel booksModel;

    public List<String> getCommodityCategoryNameList(){
        return this.commodityCategoryNameList;
    }

    public void setCommodityCategoryNameList(List<String> commodityCategoryNameList){
        this.commodityCategoryNameList = commodityCategoryNameList;
    }
    public List<String> getBigBangWordAdopt(){
        return this.bigBangWordAdopt;
    }

    public void setBigBangWordAdopt(List<String> bigBangWordAdopt){
        this.bigBangWordAdopt = bigBangWordAdopt;
    }
    public Long getAdoptWordCount(){
        return this.adoptWordCount;
    }

    public void setAdoptWordCount(Long adoptWordCount){
        this.adoptWordCount = adoptWordCount;
    }
    public BooksModel getBooksModel(){
        return this.booksModel;
    }

    public void setBooksModel(BooksModel booksModel){
        this.booksModel = booksModel;
    }

}