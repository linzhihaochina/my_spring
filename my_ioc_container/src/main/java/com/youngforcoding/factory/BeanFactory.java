package com.youngforcoding.factory;

/**
 *    
 *  *  
 *  * @Description:  IOC容器的基本功能接口
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-22 23:28   
 *  *    
 *  
 */
public interface BeanFactory {

    Object getBean(String name);

    Object getBean(Class<?> clazz);

    void destroy();

}
