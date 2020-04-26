package com.youngforcoding.pojo;


/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-18 21:46   
 *  *    
 *  
 */
public class DITarget {

    private int intVal;

    private String strVal;

    private float floatVal;

    private LazyObj lazyObj;

    public void setLazyObj(LazyObj lazyObj) {
        this.lazyObj = lazyObj;
    }

    public LazyObj getLazyObj() {
        return lazyObj;
    }

    public DITarget(int intVal, String strVal, float floatVal, LazyObj lazyObj) {
        this.intVal = intVal;
        this.strVal = strVal;
        this.floatVal = floatVal;
    }

    @Override
    public String toString() {
        return "DITarget{" +
                "intVal=" + intVal +
                ", strVal='" + strVal + '\'' +
                ", floatVal=" + floatVal +
                '}';
    }
}
