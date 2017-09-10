package com.junhangxintong.chuanzhangtong.mine.bean;

import java.util.List;

/**
 * Created by anwanfei on 2017/9/10.
 */

public class FollowShipListBean {
    /**
     * message : 查询成功
     * status : success
     * data : {"count":2,"array":[{"shipId":2,"id":2,"isDynamicWarn":1,"userId":17,"shipName":"中国好","createDate":"2017-09-10 11:28:07"},{"shipId":1,"id":1,"isDynamicWarn":1,"userId":17,"shipName":"君航号","createDate":"2017-09-09 11:28:07"}]}
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
         * count : 2
         * array : [{"shipId":2,"id":2,"isDynamicWarn":1,"userId":17,"shipName":"中国好","createDate":"2017-09-10 11:28:07"},{"shipId":1,"id":1,"isDynamicWarn":1,"userId":17,"shipName":"君航号","createDate":"2017-09-09 11:28:07"}]
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
             * shipId : 2
             * id : 2
             * isDynamicWarn : 1
             * userId : 17
             * shipName : 中国好
             * createDate : 2017-09-10 11:28:07
             */

            private int shipId;
            private int id;
            private int isDynamicWarn;
            private int userId;
            private String shipName;
            private String createDate;
            private boolean isCheckbox;

            public boolean isCheckbox() {
                return isCheckbox;
            }

            public void setCheckbox(boolean checkbox) {
                isCheckbox = checkbox;
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

            public int getIsDynamicWarn() {
                return isDynamicWarn;
            }

            public void setIsDynamicWarn(int isDynamicWarn) {
                this.isDynamicWarn = isDynamicWarn;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getShipName() {
                return shipName;
            }

            public void setShipName(String shipName) {
                this.shipName = shipName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }
        }
    }
}
