package com.youngforcoding.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *    
 *  *  
 *  * @Description:  通过Spring容器获取Bean对象   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-18 19:11   
 *  *    
 *  
 */
public class SpringContextUtil {

    private final static ApplicationContext applicationContext;

    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml");
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

}
