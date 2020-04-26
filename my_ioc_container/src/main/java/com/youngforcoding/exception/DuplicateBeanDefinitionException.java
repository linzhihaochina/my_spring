package com.youngforcoding.exception;

/**
 *    
 *  *  
 *  * @Description:  在配置中找到两个一样的BeanDefinition   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-26 12:38   
 *  *    
 *  
 */
public class DuplicateBeanDefinitionException extends RuntimeException{

    public DuplicateBeanDefinitionException(String message) {
        super(message);
    }
}
