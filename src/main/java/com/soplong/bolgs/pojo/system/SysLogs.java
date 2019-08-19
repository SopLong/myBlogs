package com.soplong.bolgs.pojo.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysLogs implements Serializable {

    private Integer id;

    /**
     * 描述
     */
    private String desc;

    /**
     * 异常详情
     */
    private String exceptionDetail;

    /**
     * 类型
     */
    private String type;

    /**
     * 方法
     */
    private String method;

    /**
     * 参数
     */
    private String params;

    /**
     * 访问IP
     */
    private String requestIp;

    /**
     * 请求时间
     */
    private long time;

    public SysLogs(String type, String requestIp, long time) {
        this.type = type;
        this.requestIp = requestIp;
        this.time = time;
    }
}
