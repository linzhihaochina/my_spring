package com.youngforcoding.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-25 22:19   
 *  *    
 *  
 */
public class ContextConfiguration {

    private Object config;

    private Properties properties;

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    public Object getConfig() {
        return config;
    }

    public void setConfig(Object config) {
        this.config = config;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Map<String, BeanDefinition> getBeanDefinitionMap() {
        return beanDefinitionMap;
    }

    public void setBeanDefinitionMap(Map<String, BeanDefinition> beanDefinitionMap) {
        this.beanDefinitionMap = beanDefinitionMap;
    }
}
