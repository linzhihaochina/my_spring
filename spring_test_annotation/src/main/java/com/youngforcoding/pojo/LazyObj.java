package com.youngforcoding.pojo;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-18 20:36   
 *  *    
 *  
 */
public class LazyObj {


    public LazyObj() {
        System.out.println("lazyObj生成");
    }

    private void initMethod() {
        System.out.println("初始化方法被调用");
    }


    private void destroyMethod() {
        System.out.println("销毁方法被调用");
    }
}
