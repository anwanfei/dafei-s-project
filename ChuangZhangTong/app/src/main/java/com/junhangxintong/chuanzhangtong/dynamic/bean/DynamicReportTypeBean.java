package com.junhangxintong.chuanzhangtong.dynamic.bean;

/**
 * Created by anwanfei on 2017/9/11.
 */

public class DynamicReportTypeBean {
    /**
     * message : 查询成功
     * status : success
     * data : {"reportType":1}
     * code : 200
     */

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

        private int reportType;

        public int getReportType() {
            return reportType;
        }

        public void setReportType(int reportType) {
            this.reportType = reportType;
        }
    }
}
