package com.youngforcoding.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 *    
 *  *  
 *  * @Description:  模拟打印日志   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-21 00:57   
 *  *    
 *  
 */
@Component
@Aspect
public class LogUtil {

    @Pointcut("execution(* com.youngforcoding.pojo.*.*())")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before");
        //System.out.println(1 / 0);
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after");
        //throw new RuntimeException("test");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("after returning!");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("after throwing!");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        //System.out.println("joinPoint args :" + Arrays.toString(args));
        try {
            System.out.println("调用前拦截");
            joinPoint.proceed(args);
            System.out.println("调用之后拦截");
        } catch (Throwable throwable) {
            System.out.println("跑异常之后拦截");
        }
    }
}
