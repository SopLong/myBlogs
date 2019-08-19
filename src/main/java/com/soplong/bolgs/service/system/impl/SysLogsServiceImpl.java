package com.soplong.bolgs.service.system.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.soplong.bolgs.annotation.Log;
import com.soplong.bolgs.mapper.system.SysLogsMapper;
import com.soplong.bolgs.pojo.system.SysLogs;
import com.soplong.bolgs.service.system.SysLogsService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class SysLogsServiceImpl extends ServiceImpl<SysLogsMapper, SysLogs> implements SysLogsService {
    @Override
    public void addLogs(ProceedingJoinPoint joinPoint, long time,SysLogs log) {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        Method method = ms.getMethod();
        Log logAnnontation = method.getAnnotation(Log.class);

        String params = "{";
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        if (argValues != null) {
            for (int i = 0; i < argValues.length; i++) {
                params += " " + argNames[i] + ": " + argValues[i];
            }
        }
        log.setDesc(logAnnontation.value());
        log.setMethod(method.getDeclaringClass().getName());
        log.setParams(params + " }");
        this.insert(log);
    }
}
