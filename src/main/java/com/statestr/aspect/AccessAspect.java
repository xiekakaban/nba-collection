package com.statestr.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ruantianbo on 2017/4/10.
 */
@Component
@Aspect
public class AccessAspect {
    private static final Logger logger = LoggerFactory.getLogger(AccessAspect.class);



    @Pointcut("execution(public * com.statestr.controller.*.*(..))")
    public void accessLog(){}

    @Before("accessLog()")
    public void logBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuffer sb = new StringBuffer();
        sb.append("url: "+request.getRequestURL()).append("\t");
        sb.append("method: "+request.getMethod()).append("\t");
        sb.append("ip: "+request.getRemoteAddr()).append("\t");
        //类方法
        sb.append("class: "+joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()).append("\t");;
        //参数
        sb.append("args: "+joinPoint.getArgs()).append("\n");
        logger.warn(sb.toString());
    }
}
