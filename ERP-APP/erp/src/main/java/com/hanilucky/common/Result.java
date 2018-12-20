package com.hanilucky.common;

/**
 * Created by Covet on 2018/12/4.
 */
public class Result {
    private Boolean success;
    private String message;
    private Object data;

    public Result() {
        super();
    }

    public Result(Boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
