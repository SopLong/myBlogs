package com.soplong.bolgs.constant;

/**
 * Created by SopLong on 2019/7/29.
 */
public enum  ResultCode {
    /** 操作成功 */
    SUCCESS("SUCCESS_CODE", 20000),
    /** 操作失败 */
    FAIL("FAIL_CODE", 50001);

    private ResultCode(String msg, int code){
        this.code = code;
        this.msg = msg;
    }
    public Integer code() {
        return code;
    }
    public String msg() {
        return msg;
    }
    private Integer code;
    private String msg;
}
