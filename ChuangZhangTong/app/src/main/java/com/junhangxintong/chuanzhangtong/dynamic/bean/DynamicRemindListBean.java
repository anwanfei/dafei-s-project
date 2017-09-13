package com.junhangxintong.chuanzhangtong.dynamic.bean;

import java.util.List;

/**
 * Created by anwanfei on 2017/9/11.
 */

public class DynamicRemindListBean {
    /**
     * message : 查询成功
     * status : success
     * data : {"count":6,"array":[{"shipId":1,"id":3,"title":"水手大飞海员证到期提醒","userId":19,"superAdminUserId":1,"fkId":2,"remindDate":"2017-09-12 11:29:44","remindType":3},{"shipId":1,"id":2,"title":"HUAHAI的靠泊报文","userId":15,"superAdminUserId":1,"fkId":2,"remindDate":"2017-09-11 10:29:44","remindType":1},{"shipId":1,"id":5,"title":"HUAHAI的抵港报文","userId":15,"superAdminUserId":1,"fkId":3,"remindDate":"2017-09-11 10:29:44","remindType":1},{"shipId":1,"id":1,"title":"HUAHAI的正午报文","userId":15,"superAdminUserId":1,"fkId":1,"remindDate":"2017-09-10 10:29:44","remindType":1},{"shipId":1,"id":4,"title":"HUAHAI船舶到期提醒","userId":19,"superAdminUserId":1,"fkId":7,"remindDate":"2017-09-09 10:29:44","remindType":4},{"shipId":1,"id":6,"title":"HUAHAI的离港报文","userId":15,"superAdminUserId":1,"fkId":4,"remindDate":"2017-09-09 10:29:44","remindType":1}]}
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
         * count : 6
         * array : [{"shipId":1,"id":3,"title":"水手大飞海员证到期提醒","userId":19,"superAdminUserId":1,"fkId":2,"remindDate":"2017-09-12 11:29:44","remindType":3},{"shipId":1,"id":2,"title":"HUAHAI的靠泊报文","userId":15,"superAdminUserId":1,"fkId":2,"remindDate":"2017-09-11 10:29:44","remindType":1},{"shipId":1,"id":5,"title":"HUAHAI的抵港报文","userId":15,"superAdminUserId":1,"fkId":3,"remindDate":"2017-09-11 10:29:44","remindType":1},{"shipId":1,"id":1,"title":"HUAHAI的正午报文","userId":15,"superAdminUserId":1,"fkId":1,"remindDate":"2017-09-10 10:29:44","remindType":1},{"shipId":1,"id":4,"title":"HUAHAI船舶到期提醒","userId":19,"superAdminUserId":1,"fkId":7,"remindDate":"2017-09-09 10:29:44","remindType":4},{"shipId":1,"id":6,"title":"HUAHAI的离港报文","userId":15,"superAdminUserId":1,"fkId":4,"remindDate":"2017-09-09 10:29:44","remindType":1}]
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
             * id : 3
             * title : 水手大飞海员证到期提醒
             * userId : 19
             * superAdminUserId : 1
             * fkId : 2
             * remindDate : 2017-09-12 11:29:44
             * remindType : 3
             */

            private int shipId;
            private int id;
            private String title;
            private int userId;
            private int superAdminUserId;
            private int fkId;
            private String remindDate;
            private int remindType;

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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getSuperAdminUserId() {
                return superAdminUserId;
            }

            public void setSuperAdminUserId(int superAdminUserId) {
                this.superAdminUserId = superAdminUserId;
            }

            public int getFkId() {
                return fkId;
            }

            public void setFkId(int fkId) {
                this.fkId = fkId;
            }

            public String getRemindDate() {
                return remindDate;
            }

            public void setRemindDate(String remindDate) {
                this.remindDate = remindDate;
            }

            public int getRemindType() {
                return remindType;
            }

            public void setRemindType(int remindType) {
                this.remindType = remindType;
            }
        }
    }
}
