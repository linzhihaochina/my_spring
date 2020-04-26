package com.youngforcoding.annotation;

import com.youngforcoding.constants.ScopeType;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao  
 *  * @CreateDate:   2020-04-25 23:04   
 *  *    
 *  
 */
public @interface Scope {
    ScopeType value() default ScopeType.SINGLE;
}
