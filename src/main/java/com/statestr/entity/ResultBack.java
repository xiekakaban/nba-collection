package com.statestr.entity;

/**
 * Created by ruantianbo on 2017/4/9.
 * 格式化每一个返回值
 */
public class ResultBack<T> {
    private String code;
    private String msg;
    private T data;

    public ResultBack() {
    }

    public ResultBack(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
