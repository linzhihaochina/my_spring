package com.youngforcoding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *    
 *  *  
 *  * @Description:  任意组件   
 *  * @Author:       linZhiHao  
 *  * @CreateDate:   2020-04-25 18:05   
 *  *    
 *  
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String value() default "";
}
