package com.youngforcoding.pojo;

import com.youngforcoding.factory.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-19 02:31   
 *  *    
 *  
 */
@Component
public class ResourceTest {

    //    @Resource(name = "proxyFactory")
//    @Resource(type = ProxyFactory.class)
//    @Resource(name = "proxyFactory",type = ProxyFactory.class)
//    @Resource
//    @Autowired
//    @Autowired
//    @Qualifier("proxyFactory")
    private ProxyFactory proxyFactory;

    @Autowired
    @Qualifier("java.lang.Object")
//    @Resource(name = "aa2", type = String.class)
    private Object aa2;


    public void setAa2(Object aa2) {
        this.aa2 = aa2;
    }

    public Object getAa2() {
        return aa2;
    }

    public ProxyFactory getProxyFactory() {
        return proxyFactory;
    }
}
