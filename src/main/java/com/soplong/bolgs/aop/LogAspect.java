package com.soplong.bolgs.aop;

import com.soplong.bolgs.pojo.system.SysLogs;
import com.soplong.bolgs.service.system.SysLogsService;
import com.soplong.bolgs.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired
    private SysLogsService logsService;

    private long currentTime = 0L;

    @Pointcut("@annotation(com.soplong.bolgs.annotation.Log)")
    private void cut() {
    }

    /**
     * 定制一个环绕通知
     *
     * @param joinPoint
     */
    @Around("cut()")
    public void advice(ProceedingJoinPoint joinPoint) {
        log.debug("环绕通知之开始");
        try {
            currentTime = System.currentTimeMillis();
            joinPoint.proceed();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String ip = IPUtils.getIpAddress(request);
            SysLogs log = new SysLogs("INFO", ip, System.currentTimeMillis() - currentTime);
            logsService.addLogs(joinPoint, System.currentTimeMillis() - currentTime, log);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.debug("环绕通知之结束");
    }

    @Before("cut()")
    public void before() {
        log.debug("before");
    }

    @After("cut()")
    public void after() {
        log.debug("after");
    }
}
