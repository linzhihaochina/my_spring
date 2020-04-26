package com.youngforcoding.factory;

import com.youngforcoding.pojo.LazyObj;

/**
 *    
 *  *  
 *  * @Description:  工厂类  
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-18 20:43   
 *  *    
 *  
 */
public class FactoryBean {

    public static LazyObj getLazyObjByStaticMethod() {
        return new LazyObj();
    }


    public LazyObj getLazyObjByCommonMethod() {
        return new LazyObj();
    }
}
