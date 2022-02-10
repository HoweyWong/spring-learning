package com.lagou.aopdemo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author huanghao
 * @Date 2021-11-16
 * @Version 1.0
 */
@Aspect
@Component
public class TestAdviceAnn {
    @Pointcut("@annotation(com.lagou.aopdemo.annotation.GlobalErrorCatch)")
    private void globalCatch(){}
    
    @Around("globalCatch()")
    public void handler(ProceedingJoinPoint point) throws Throwable {
        try{
            point.proceed();
        } catch (Exception e) {
            System.out.println("错误："+e);
        }
    }
}
