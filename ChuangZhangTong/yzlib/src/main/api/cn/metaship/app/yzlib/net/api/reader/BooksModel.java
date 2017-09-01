package cn.metaship.app.yzlib.net.api.reader;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

public final class BooksModel implements Serializable{

    /**
    *分类集合
    */
    private List<String> categoryNameList;
    /**
    *书籍简介
    */
    private String bookDetailPage;
    /**
    *加入顺序
    */
    private Long number;
    private String id;
    /**
    *阅读人数
    */
    private List<String> readNumber;
    /**
    *锁定章节均价
    */
    private Double lockChapterAveragePrice;
    /**
    *付费章节
    */
    private Integer chargeChapters;
    /**
    *章数
    */
    private Integer totalChapter;
    /**
    *书籍存放地址
    */
    private String bookStoreUrl;
    /**
    *总价
    */
    private Double totalPrice;
    /**
    *发布时间
    */
    private Date releaseTime;
    /**
    *市场价格
    */
    private Double marketPrice;
    /**
    *发布状态
    */
    private Byte publishState;
    /**
    *当前分类
    */
    private String categoryId;
    /**
    *书籍封面地址
    */
    private String bookCoverUrl;
    /**
    *出版社名称
    */
    private String publisherName;
    /**
    *阅读人次(热度)
    */
    private Integer readHot;
    /**
    *星级评价
    */
    private Integer starCount;
    /**
    *书籍名称
    */
    private String bookName;
    /**
    *锁定价格
    */
    private Double lockTotalPrice;
    /**
    *平台价格
    */
    private Double platformPrice;
    /**
    *作者
    */
    private String author;
    /**
    *免费还是付费(1代表免费，2代表付费)
    */
    private Byte freeStatus;
    /**
    *免费章节
    */
    private Integer freeChapters;

    public List<String> getCategoryNameList(){
        return this.categoryNameList;
    }

    public void setCategoryNameList(List<String> categoryNameList){
        this.categoryNameList = categoryNameList;
    }
    public String getBookDetailPage(){
        return this.bookDetailPage;
    }

    public void setBookDetailPage(String bookDetailPage){
        this.bookDetailPage = bookDetailPage;
    }
    public Long getNumber(){
        return this.number;
    }

    public void setNumber(Long number){
        this.number = number;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public List<String> getReadNumber(){
        return this.readNumber;
    }

    public void setReadNumber(List<String> readNumber){
        this.readNumber = readNumber;
    }
    public Double getLockChapterAveragePrice(){
        return this.lockChapterAveragePrice;
    }

    public void setLockChapterAveragePrice(Double lockChapterAveragePrice){
        this.lockChapterAveragePrice = lockChapterAveragePrice;
    }
    public Integer getChargeChapters(){
        return this.chargeChapters;
    }

    public void setChargeChapters(Integer chargeChapters){
        this.chargeChapters = chargeChapters;
    }
    public Integer getTotalChapter(){
        return this.totalChapter;
    }

    public void setTotalChapter(Integer totalChapter){
        this.totalChapter = totalChapter;
    }
    public String getBookStoreUrl(){
        return this.bookStoreUrl;
    }

    public void setBookStoreUrl(String bookStoreUrl){
        this.bookStoreUrl = bookStoreUrl;
    }
    public Double getTotalPrice(){
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice){
        this.totalPrice = totalPrice;
    }
    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public void setReleaseTime(Date releaseTime){
        this.releaseTime = releaseTime;
    }
    public Double getMarketPrice(){
        return this.marketPrice;
    }

    public void setMarketPrice(Double marketPrice){
        this.marketPrice = marketPrice;
    }
    public Byte getPublishState(){
        return this.publishState;
    }

    public void setPublishState(Byte publishState){
        this.publishState = publishState;
    }
    public String getCategoryId(){
        return this.categoryId;
    }

    public void setCategoryId(String categoryId){
        this.categoryId = categoryId;
    }
    public String getBookCoverUrl(){
        return this.bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl){
        this.bookCoverUrl = bookCoverUrl;
    }
    public String getPublisherName(){
        return this.publisherName;
    }

    public void setPublisherName(String publisherName){
        this.publisherName = publisherName;
    }
    public Integer getReadHot(){
        return this.readHot;
    }

    public void setReadHot(Integer readHot){
        this.readHot = readHot;
    }
    public Integer getStarCount(){
        return this.starCount;
    }

    public void setStarCount(Integer starCount){
        this.starCount = starCount;
    }
    public String getBookName(){
        return this.bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }
    public Double getLockTotalPrice(){
        return this.lockTotalPrice;
    }

    public void setLockTotalPrice(Double lockTotalPrice){
        this.lockTotalPrice = lockTotalPrice;
    }
    public Double getPlatformPrice(){
        return this.platformPrice;
    }

    public void setPlatformPrice(Double platformPrice){
        this.platformPrice = platformPrice;
    }
    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }
    public Byte getFreeStatus(){
        return this.freeStatus;
    }

    public void setFreeStatus(Byte freeStatus){
        this.freeStatus = freeStatus;
    }
    public Integer getFreeChapters(){
        return this.freeChapters;
    }

    public void setFreeChapters(Integer freeChapters){
        this.freeChapters = freeChapters;
    }

}