package com.youngforcoding.exception;

/**
 *    
 *  *  
 *  * @Description:  没有找到配置文件   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-24 13:28   
 *  *    
 *  
 */
public class ConfigNotFindException extends RuntimeException {

    public ConfigNotFindException(String message) {
        super(message);
    }
}
