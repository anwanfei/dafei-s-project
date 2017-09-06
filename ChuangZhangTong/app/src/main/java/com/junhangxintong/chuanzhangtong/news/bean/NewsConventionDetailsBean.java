package com.junhangxintong.chuanzhangtong.news.bean;

/**
 * Created by anwanfei on 2017/9/6.
 */

public class NewsConventionDetailsBean {

    private String message;
    private String status;
    private DataBean data;
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class DataBean {

        private ObjectBean object;

        public ObjectBean getObject() {
            return object;
        }

        public void setObject(ObjectBean object) {
            this.object = object;
        }

        public static class ObjectBean {

            private String id;
            private String oilName;
            private String title;
            private String oilPrice;
            private String createAuthor;
            private String state;
            private String context;
            private String modifyDate;
            private String createDate;
            private String oilChg;
            private String newsType;
            private String oilFluctuation;

            public String getId() {
                return id;
            }

            public void setId(String id) {
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

            public String getOilPrice() {
                return oilPrice;
            }

            public void setOilPrice(String oilPrice) {
                this.oilPrice = oilPrice;
            }

            public String getCreateAuthor() {
                return createAuthor;
            }

            public void setCreateAuthor(String createAuthor) {
                this.createAuthor = createAuthor;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
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

            public String getOilChg() {
                return oilChg;
            }

            public void setOilChg(String oilChg) {
                this.oilChg = oilChg;
            }

            public String getNewsType() {
                return newsType;
            }

            public void setNewsType(String newsType) {
                this.newsType = newsType;
            }

            public String getOilFluctuation() {
                return oilFluctuation;
            }

            public void setOilFluctuation(String oilFluctuation) {
                this.oilFluctuation = oilFluctuation;
            }
        }
    }
}
