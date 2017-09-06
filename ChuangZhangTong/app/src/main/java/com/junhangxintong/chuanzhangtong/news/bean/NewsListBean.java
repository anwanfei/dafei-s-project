package com.junhangxintong.chuanzhangtong.news.bean;

import java.util.List;

/**
 * Created by anwanfei on 2017/9/5.
 */

public class NewsListBean {

    private String message;
    private String status;
    private DataBean data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {

        private int count;
        private List<ArrayBean> array;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ArrayBean> getArray() {
            return array;
        }

        public void setArray(List<ArrayBean> array) {
            this.array = array;
        }

        public static class ArrayBean {

            private int id;
            private String oilName;
            private String title;
            private int oilPrice;
            private String createAuthor;
            private int state;
            private String context;
            private String modifyDate;
            private String createDate;
            private int oilChg;
            private int newsType;
            private int oilFluctuation;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOilName() {
                return oilName;
            }

            public void setOilName(String oilName) {
                this.oilName = oilName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getOilPrice() {
                return oilPrice;
            }

            public void setOilPrice(int oilPrice) {
                this.oilPrice = oilPrice;
            }

            public String getCreateAuthor() {
                return createAuthor;
            }

            public void setCreateAuthor(String createAuthor) {
                this.createAuthor = createAuthor;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getModifyDate() {
                return modifyDate;
            }

            public void setModifyDate(String modifyDate) {
                this.modifyDate = modifyDate;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getOilChg() {
                return oilChg;
            }

            public void setOilChg(int oilChg) {
                this.oilChg = oilChg;
            }

            public int getNewsType() {
                return newsType;
            }

            public void setNewsType(int newsType) {
                this.newsType = newsType;
            }

            public int getOilFluctuation() {
                return oilFluctuation;
            }

            public void setOilFluctuation(int oilFluctuation) {
                this.oilFluctuation = oilFluctuation;
            }
        }
    }
}
