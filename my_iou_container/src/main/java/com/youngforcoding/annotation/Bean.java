package com.youngforcoding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *    
 *  *  
 *  * @Description:     
 *  * @Author:       linZhiHao  
 *  * @CreateDate:   2020-04-25 18:19   
 *  *    
 *  
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {
    String name() default "";
}
