package com.youngforcoding.pojo;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PreDestroy;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-19 17:02   
 *  *    
 *  
 */
public class DebugObj implements InitializingBean {

    public DebugObj() {
        System.out.println("调用DebugObj构造方法");
    }


    private void init(){
        System.out.println("调用bean标签配置的DebugObj的init-method方法");
    }

    private void destroy(){
        System.out.println("调用bean标签配置的DebugObj的destroy方法");
    }


    @PreDestroy
    public void preDestroy(){
        System.out.println("调用注解PreDestroy标注的DebugObj的方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用DebugObj的afterPropertiesSet方法");
    }
}
