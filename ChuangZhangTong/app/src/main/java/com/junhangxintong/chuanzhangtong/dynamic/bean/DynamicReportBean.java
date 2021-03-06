package com.junhangxintong.chuanzhangtong.dynamic.bean;

import java.util.List;

/**
 * Created by anwanfei on 2017/9/11.
 */

public class DynamicReportBean {
    /**
     * message : 查询成功
     * status : success
     * data : {"count":1,"array":[{"shipId":1,"id":2,"title":"君航号的靠泊报","createDate":"2017-09-08 10:29:44","type":2,"reportId":1}]}
     * code : 200
     */

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
        /**
         * count : 1
         * array : [{"shipId":1,"id":2,"title":"君航号的靠泊报","createDate":"2017-09-08 10:29:44","type":2,"reportId":1}]
         */

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
            /**
             * shipId : 1
             * id : 2
             * title : 君航号的靠泊报
             * createDate : 2017-09-08 10:29:44
             * type : 2
             * reportId : 1
             */

            private int shipId;
            private int id;
            private String title;
            private String createDate;
            private int type;
            private int reportId;
            private String shipName;

            public String getShipName() {
                return shipName;
            }

            public void setShipName(String shipName) {
                this.shipName = shipName;
            }

            public int getShipId() {
                return shipId;
            }

            public void setShipId(int shipId) {
                this.shipId = shipId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getReportId() {
                return reportId;
            }

            public void setReportId(int reportId) {
                this.reportId = reportId;
            }
        }
    }
}
