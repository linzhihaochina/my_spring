package com.youngforcoding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *    
 *  *  
 *  * @Description:  用来标注当前类是一个配置类的注解   
 *  * @Author:       linZhiHao  
 *  * @CreateDate:   2020-04-24 12:14   
 *  *    
 *  
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
}
