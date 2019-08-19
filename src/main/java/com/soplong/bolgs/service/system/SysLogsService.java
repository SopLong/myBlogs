package com.soplong.bolgs.service.system;

import com.baomidou.mybatisplus.service.IService;
import com.soplong.bolgs.pojo.system.SysLogs;
import org.aspectj.lang.ProceedingJoinPoint;

public interface SysLogsService extends IService<SysLogs> {
    /**
     * 添加日志信息
     * @param joinPoint
     * @param time
     * @param log
     */
    void addLogs(ProceedingJoinPoint joinPoint, long time,SysLogs log);
}
