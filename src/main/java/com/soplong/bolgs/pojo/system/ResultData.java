package com.soplong.bolgs.pojo.system;

import com.soplong.bolgs.constant.ResultCode;

/**
 * Created by SopLong on 2019/7/29.
 */
public class ResultData {
    private Integer code = ResultCode.SUCCESS.code();

    private String msg = ResultCode.SUCCESS.msg();

    private Object data;

    public ResultData() {
    }

    public ResultData(Object data) {
        this.data = data;
    }

    public ResultData(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public ResultData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultData(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
