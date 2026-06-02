package com.daowen.util;

public class JsonResult {

    private int stateCode;
    private String message;
    private Object data;

    public JsonResult() {
    }

    public JsonResult(int stateCode, String message) {
        this.stateCode = stateCode;
        this.message = message;
    }

    public JsonResult(int stateCode, String message, Object data) {
        this.stateCode = stateCode;
        this.message = message;
        this.data = data;
    }

    public static JsonResult success(int stateCode, String message) {
        return new JsonResult(stateCode, message);
    }

    public static JsonResult success(int stateCode, String message, Object data) {
        return new JsonResult(stateCode, message, data);
    }

    public static JsonResult error(int stateCode, String message) {
        return new JsonResult(stateCode, message);
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
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