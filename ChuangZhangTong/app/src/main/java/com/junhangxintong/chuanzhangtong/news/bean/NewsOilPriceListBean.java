package com.junhangxintong.chuanzhangtong.news.bean;

import java.util.List;

/**
 * Created by anwanfei on 2017/9/6.
 */

public class NewsOilPriceListBean {

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
        private List<ArrayBean> array;

        public List<ArrayBean> getArray() {
            return array;
        }

        public void setArray(List<ArrayBean> array) {
            this.array = array;
        }

        public static class ArrayBean {

            private int id;
            private String oilName;
            private int oilPrice;
            private int newsId;
            private String modifyDate;
            private String createDate;
            private int oilChg;
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

            public int getOilPrice() {
                return oilPrice;
            }

            public void setOilPrice(int oilPrice) {
                this.oilPrice = oilPrice;
            }

            public int getNewsId() {
                return newsId;
            }

            public void setNewsId(int newsId) {
                this.newsId = newsId;
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

            public int getOilFluctuation() {
                return oilFluctuation;
            }

            public void setOilFluctuation(int oilFluctuation) {
                this.oilFluctuation = oilFluctuation;
            }
        }
    }
}
