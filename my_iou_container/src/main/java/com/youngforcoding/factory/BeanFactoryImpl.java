package com.youngforcoding.factory;

import com.youngforcoding.bean.BeanDefinition;
import com.youngforcoding.constants.EnhanceType;
import com.youngforcoding.constants.ScopeType;
import com.youngforcoding.context.Context;
import com.youngforcoding.exception.DuplicateBeanDefinitionException;
import com.youngforcoding.util.EnhanceUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 *
 */
public class BeanFactoryImpl implements BeanFactory {


    private Set<String> inCreationCheckExclusions = new HashSet<>();
    /**
     * 单例池，一级缓存
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    /**
     * 二级缓存用来解决循环依赖
     */
    private Map<String, Object> earlySingletonObjects = new HashMap<>();

    @Override
    public Object getBean(String name) {
        return getBean(name, true);
    }


    private Object getBean(String name, boolean allowEarlyReference) {
        Object bean = getSingleton(name, allowEarlyReference);
        if (bean != null) {
            return bean;
        }
        return doGetBean(name);
    }

    private Object getSingleton(String name, boolean allowEarlyReference) {
        Object bean = singletonObjects.get(name);
        if (bean == null && allowEarlyReference) {
            return earlySingletonObjects.get(name);
        }

        return bean;
    }


    private Object doGetBean(String name) {
        BeanDefinition beanDefinition = Context.CONTEXT_CONFIGURATION.getBeanDefinitionMap().get(name);
        if (beanDefinition == null) {
            return null;
        }

        Object bean = null;

        if (inCreationCheckExclusions.contains(name)) {
            return null;
        }
        inCreationCheckExclusions.add(name);
        if (beanDefinition.getEnhanceType().equals(EnhanceType.BEAN)) {
            Object config = Context.CONTEXT_CONFIGURATION.getConfig();
            Class<?> configClass = config.getClass();
            try {
                Method getBean = configClass.getDeclaredMethod(beanDefinition.getName());
                bean = getBean.invoke(config);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else if (beanDefinition.getEnhanceType().equals(EnhanceType.TRANSACTION)) {
            //  需要对事务进行增强
            try {
                Constructor<?> declaredConstructor = beanDefinition.getType().getDeclaredConstructor();
                bean = declaredConstructor.newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

        } else {
            //  普通创建
            try {
                Constructor<?> declaredConstructor = beanDefinition.getType().getDeclaredConstructor();
                bean = declaredConstructor.newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (bean == null) {
            return null;
        }
        /**
         * 解决循环依赖，提前暴露在二级缓存中
         */
        earlySingletonObjects.put(name, bean);
        population(bean, beanDefinition);
        bean = enhance(bean, beanDefinition);
        earlySingletonObjects.remove(name);
        singletonObjects.put(name, bean);
        inCreationCheckExclusions.remove(name);
        if (beanDefinition.getScopeType() == ScopeType.SINGLE) {
            singletonObjects.put(beanDefinition.getName(), bean);
        }

        return bean;

    }

    private Object enhance(Object bean, BeanDefinition beanDefinition) {
        if (beanDefinition.getEnhanceType().equals(EnhanceType.TRANSACTION)) {
            return EnhanceUtil.enhanceTransactional(bean, beanDefinition.getEnhanceMethodList());
        }
        return bean;
    }

    private void population(Object bean, BeanDefinition beanDefinition) {
        if (beanDefinition.getAutowiredProperties() != null) {
            beanDefinition.getAutowiredProperties().forEach((property, type) -> {
                try {
                    Field declaredField = beanDefinition.getType().getDeclaredField(property);
                    declaredField.setAccessible(true);
                    declaredField.set(bean, getBean(type));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    @Override
    public Object getBean(Class<?> type) {
        List<BeanDefinition> beanDefinitions = getBeanDefinitions(type);
        if (beanDefinitions.size() > 1) {
            throw new DuplicateBeanDefinitionException("重复的BeanDefinition被发现");
        }
        BeanDefinition beanDefinition = beanDefinitions.get(0);
        if (beanDefinition == null) {
            return null;
        }
        return getBean(beanDefinition.getName());
    }

    private List<BeanDefinition> getBeanDefinitions(Class<?> type) {
        Map<String, BeanDefinition> beanDefinitionMap = Context.CONTEXT_CONFIGURATION.getBeanDefinitionMap();
        List<BeanDefinition> beanDefinitionList = new ArrayList<>();
        beanDefinitionMap.forEach((name, beanDefinition) -> {
            //  type必须是beanDefinition.getType()的父类
            if (type.isAssignableFrom(beanDefinition.getType())) {
                beanDefinitionList.add(beanDefinition);
            }
        });

        return beanDefinitionList;
    }

    @Override
    public void destroy() {
        singletonObjects.clear();
        earlySingletonObjects.clear();
        singletonObjects = null;
        earlySingletonObjects = null;
    }

}
