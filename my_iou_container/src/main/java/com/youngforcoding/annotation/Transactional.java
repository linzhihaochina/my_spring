package com.youngforcoding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *    
 *  *  
 *  * @Description:  用来给Service层方法添加事务增强   
 *  * @Author:       linZhiHao  
 *  * @CreateDate:   2020-04-22 23:06   
 *  *    
 *  
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {
    Class<? super Exception> rollbackFor() default Exception.class;
}
