package com.youngforcoding.bean;

import com.youngforcoding.constants.EnhanceType;
import com.youngforcoding.constants.ScopeType;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-24 13:50   
 *  *    
 *  
 */
public class BeanDefinition {

    private Class<?> type;

    private String name;

    private ScopeType scopeType;

    private EnhanceType enhanceType;

    private Map<String, Class<?>> autowiredProperties;

    private Set<String> enhanceMethodList;

    public Set<String> getEnhanceMethodList() {
        return enhanceMethodList;
    }

    public void setEnhanceMethodList(Set<String> enhanceMethodList) {
        this.enhanceMethodList = enhanceMethodList;
    }

    public EnhanceType getEnhanceType() {
        return enhanceType;
    }

    public void setEnhanceType(EnhanceType enhanceType) {
        this.enhanceType = enhanceType;
    }

    public Map<String, Class<?>> getAutowiredProperties() {
        return autowiredProperties;
    }

    public void setAutowiredProperties(Map<String, Class<?>> autowiredProperties) {
        this.autowiredProperties = autowiredProperties;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ScopeType getScopeType() {
        return scopeType;
    }

    public void setScopeType(ScopeType scopeType) {
        this.scopeType = scopeType;
    }
}
