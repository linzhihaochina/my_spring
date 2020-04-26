package com.youngforcoding.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;

/**
 *    
 *  *  
 *  * @Description:  模拟打印日志   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-21 00:57   
 *  *    
 *  
 */

public class LogUtil {

    public LogUtil() {
    }


    public void before() {
        System.out.println("before");
        throw new RuntimeException("test");
    }

    public void after() {
        System.out.println("after");
        System.out.println(1 / 0);
    }

    public void afterReturning() {
        System.out.println("after returning!");
    }

    public void afterThrowing() {
        System.out.println("after throwing!");
    }


    /**
     * before
     * transfer
     * after
     * after throwing!
     * <p>
     * <p>
     * around before
     * transfer
     * around throwing!
     * around after
     *
     * @param joinPoint
     */
    public void around(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        //System.out.println("joinPoint args :" + Arrays.toString(args));
        boolean hasException = false;
        try {
            System.out.println("around before");
            joinPoint.proceed(args);
        } catch (Throwable throwable) {
            hasException = true;
        }

        if (hasException) {
            System.out.println("around afterReturning");
            System.out.println("around throwing!");
        } else {
            System.out.println("around after");
            System.out.println("around afterReturning");
        }
    }

}
