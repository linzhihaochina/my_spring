package com.youngforcoding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *    
 *  *  
 *  * @Description:  用来标注静态资源文件properties的路径   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-24 13:38   
 *  *    
 *  
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertySource {
    String value();
}
