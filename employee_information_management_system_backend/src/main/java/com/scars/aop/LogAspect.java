package com.scars.aop;

import com.alibaba.fastjson.JSONObject;
import com.scars.mapper.OperateLogMapper;
import com.scars.pojo.OperateLog;
import com.scars.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.scars.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取并解析请求头中的JWT令牌
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);

        // 获取记录日志所需的相关信息
        Integer operateUser = (Integer) claims.get("id");
        LocalDateTime operateTime = LocalDateTime.now();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String methodParams = Arrays.toString(joinPoint.getArgs());
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(result);
        Long costTime = endTime - beginTime;

        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);

        log.info("AOP操作日志：{}", operateLog);

        return result;
    }
}
