package cn.metaship.app.yzlib.net;

/**
 * Created by live106 on 2017/6/3 11:41
 */

public class NetworkException {
    private int status;
    // json化之后的AjaxResult
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NetworkException{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}

