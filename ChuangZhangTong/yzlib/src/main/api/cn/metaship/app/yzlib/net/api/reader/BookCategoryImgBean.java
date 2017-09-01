package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;

public final class BookCategoryImgBean implements Serializable{

    /**
    *当前分类ID
    */
    private String categoryId;
    /**
    *当前分类名称
    */
    private String categoryName;
    /**
    *当前分类的封面图片下载地址
    */
    private String categoryCoverUrl;
    private String id;

    public String getCategoryId(){
        return this.categoryId;
    }

    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }
    public String getCategoryName(){
        return this.categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    public String getCategoryCoverUrl(){
        return this.categoryCoverUrl;
    }

    public void setCategoryCoverUrl(String categoryCoverUrl){
        this.categoryCoverUrl = categoryCoverUrl;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}