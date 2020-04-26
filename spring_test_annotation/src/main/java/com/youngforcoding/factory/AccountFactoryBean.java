package com.youngforcoding.factory;

import com.youngforcoding.pojo.Account;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-19 11:43   
 *  *    
 *  
 */
//@Component
public class AccountFactoryBean implements FactoryBean<Account>, BeanFactoryPostProcessor {

    public AccountFactoryBean() {
        System.out.println("AccountFactoryBean");
    }

    @Override
    public Account getObject() throws Exception {
        return new Account();
    }

    @Override
    public Class<?> getObjectType() {
        return Account.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory invoke");
    }
}
