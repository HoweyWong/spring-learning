package com.lagou.aopdemo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description
 * @Author huanghao
 * @Date 2021-11-16
 * @Version 1.0
 */
@Aspect
@Component
public class TestAdvice {
    /**
     * 1、定义pointcut
     */ 
    @Pointcut("execution(* com.lagou.aopdemo.service.impl.TestServiceImpl.eatCarrot())")
    private void eatCarrot(){}
    
    @Around("eatCarrot()")
    public void handlerResult(ProceedingJoinPoint point) throws Throwable {
        System.out.println("吃萝卜前洗手");
        point.proceed();
        System.out.println("吃萝卜后买单");
    }
    
    @Before("eatCarrot()")
    public void handlerBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("Before eatCarrot we need to know:"+ Arrays.toString(args));
    }
    
    @AfterReturning(value = "eatCarrot()",returning = "rtValue")
    public void after(Object rtValue){
        System.out.println(rtValue);
    }
    
    @AfterThrowing(value = "eatCarrot()",throwing = "e")
    public void afterThrow(Throwable e){
        System.out.println("异常："+e);
    }
}
