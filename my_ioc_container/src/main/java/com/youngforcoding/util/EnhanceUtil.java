package com.youngforcoding.util;

import com.youngforcoding.annotation.Transactional;
import com.youngforcoding.factory.BeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Set;

/**
 *    
 *  *  
 *  * @Description:  用动态代理增强需要的类   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-25 23:21   
 *  *    
 *  
 */
public class EnhanceUtil {


    public static Object enhanceGetBean(Object config, BeanFactory beanFactory) {
        return Enhancer.create(config.getClass(), (MethodInterceptor) (o, method, objects, methodProxy) -> {
            Object bean = beanFactory.getBean(method.getName());
            if (bean != null) {
                return bean;
            }
            return methodProxy.invokeSuper(o, objects);
        });
    }

    public static Object enhanceTransactional(Object target, Set<String> methods) {
        return Enhancer.create(target.getClass(), (MethodInterceptor) (o, method, objects, methodProxy) -> {
            Object result = null;
            if (methods.contains(method.getName())) {
                Transactional transactional = method.getDeclaredAnnotation(Transactional.class);
                Class<? super Exception> exceptionClass = transactional.rollbackFor();
                //  事务增强
                TransactionManager.beginTransaction();
                try {
                    result = method.invoke(target, objects);
                    TransactionManager.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e.getClass() == exceptionClass) {
                        TransactionManager.rollback();
                    }
                }
            } else {
                result = method.invoke(target, objects);
            }
            return result;
        });
    }

}
