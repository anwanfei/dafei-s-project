package cn.metaship.app.yzlib.net.api.base;

public final class AjaxResult {
    private int code;
    private String message;

    public AjaxResult(int code) {
        this(code, "");
    }

    public AjaxResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
