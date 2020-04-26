package com.youngforcoding.context;

import com.youngforcoding.annotation.*;
import com.youngforcoding.bean.BeanDefinition;
import com.youngforcoding.bean.ContextConfiguration;
import com.youngforcoding.constants.EnhanceType;
import com.youngforcoding.constants.ScopeType;
import com.youngforcoding.exception.ConfigNotFindException;
import com.youngforcoding.factory.BeanFactory;
import com.youngforcoding.factory.BeanFactoryImpl;
import com.youngforcoding.util.EnhanceUtil;
import com.youngforcoding.util.ResourceUtil;
import com.youngforcoding.util.StringUtil;
import com.youngforcoding.util.TransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 *    
 *  *  
 *  * @Description:     
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-24 13:07   
 *  *    
 *  
 */
public class ContextLoader {

    public BeanFactory init(String configClassParam) {
        try {
            //  1、obtainBeanFactory
            BeanFactory beanFactory = obtainBeanFactory(Class.forName(configClassParam));
            //  2、finishBeanFactoryInitialization
            finishBeanFactoryInitialization(beanFactory);
            return beanFactory;
        } catch (Exception e) {
            System.out.println("加载配置文件类失败!");
            e.printStackTrace();
        }
        return null;
    }

    private void finishBeanFactoryInitialization(BeanFactory beanFactory) {
        //  对于单例的对象都进行缓存
        Map<String, BeanDefinition> beanDefinitions = Context.CONTEXT_CONFIGURATION.getBeanDefinitionMap();
        beanDefinitions.forEach((name, beanDefinition) -> {
            if (beanDefinition.getScopeType() == ScopeType.SINGLE) {
                //  在getBean的过程中会进行缓存
                beanFactory.getBean(name);
            }
        });
        //  开启自动事务管理
        TransactionManager.dataSource = (DataSource) beanFactory.getBean(DataSource.class);
    }


    private ContextConfiguration getConfig(BeanFactory beanFactory, Class<?> configClass
            , Properties properties) throws NoSuchMethodException
            , IllegalAccessException, InvocationTargetException, InstantiationException {
        ContextConfiguration contextConfiguration = new ContextConfiguration();
        for (Method declaredMethod : configClass.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Bean.class)) {
                //Bean beanAnnotation = declaredMethod.getDeclaredAnnotation(Bean.class);
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setType(declaredMethod.getReturnType());
                //  如果是配置类中Bean标签标注的方法，那么name就是方法的名称
                beanDefinition.setName(declaredMethod.getName());
                beanDefinition.setEnhanceType(EnhanceType.BEAN);
                Scope scope = declaredMethod.getDeclaredAnnotation(Scope.class);
                if (scope != null) {
                    beanDefinition.setScopeType(scope.value());
                } else {
                    beanDefinition.setScopeType(ScopeType.SINGLE);
                }
                contextConfiguration.getBeanDefinitionMap().put(declaredMethod.getName(), beanDefinition);
            }
        }
        Constructor<?> constructor = configClass.getConstructor();
        constructor.setAccessible(true);
        Object config = EnhanceUtil.enhanceGetBean(constructor.newInstance(), beanFactory);
        injectProperty(config, configClass, properties);
        contextConfiguration.setConfig(config);
        contextConfiguration.setProperties(properties);
        return contextConfiguration;
    }


    /**
     * 注入属性值
     *
     * @param config      config对象
     * @param configClass config对象的类类型
     * @param properties  静态资源
     * @throws IllegalAccessException 无法访问该属性
     */
    private void injectProperty(Object config, Class<?> configClass, Properties properties) throws IllegalAccessException {
        for (Field declaredField : configClass.getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(Value.class)) {
                Value value = declaredField.getDeclaredAnnotation(Value.class);
                String propertyName = value.value();
                declaredField.setAccessible(true);
                declaredField.set(config, properties.get(propertyName));
            }
        }
    }

    private void doCheckConfigClass(Class<?> annotations) {
        boolean isConfig = false;
        for (Annotation annotation : annotations.getAnnotations()) {
            if (annotation instanceof Configuration) {
                isConfig = true;
                break;
            }
        }
        if (!isConfig) {
            throw new ConfigNotFindException("没有找到配置文件！");
        }
    }

    private Properties loadProperties(PropertySource propertySource) throws IOException {
        Properties properties = new Properties();
        if (propertySource == null) {
            return properties;
        }
        String propertiesLocation = propertySource.value();
        properties.load(ResourceUtil.getResources(propertiesLocation));
        return properties;
    }

    private Map<String, BeanDefinition> lookUpComponents(ComponentScan componentScan) throws ClassNotFoundException {
        if (componentScan == null || StringUtil.isEmpty(componentScan.value())) {
            return new HashMap<>(0);
        }
        String basePackageName = componentScan.value().replaceAll("[.]", "/");
        String path = componentScan.getClass().getResource("/").getPath() + basePackageName;
        File file = new File(path);
        List<Class<?>> classList = new ArrayList<>();
        load(file, basePackageName, Thread.currentThread().getContextClassLoader(), classList);
        return buildBeanDefinitionMap(classList);
    }

    private Map<String, BeanDefinition> buildBeanDefinitionMap(List<Class<?>> classList) {
        Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>(classList.size());
        for (Class<?> beanClass : classList) {
            if (beanClass.isAnnotationPresent(Service.class) || beanClass.isAnnotationPresent(Component.class)) {
                String name = getName(beanClass);
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setName(name);
                Scope scope = beanClass.getDeclaredAnnotation(Scope.class);
                if (scope != null) {
                    beanDefinition.setScopeType(scope.value());
                } else {
                    beanDefinition.setScopeType(ScopeType.SINGLE);
                }
                beanDefinition.setType(beanClass);
                injectAutowiredProperties(beanDefinition, beanClass.getDeclaredFields());
                Set<String> transactionalMethods = lookUpTransactional(beanClass);
                if (transactionalMethods.size() > 0) {
                    beanDefinition.setEnhanceType(EnhanceType.TRANSACTION);
                } else {
                    beanDefinition.setEnhanceType(EnhanceType.DEFAULT);
                }
                beanDefinition.setEnhanceMethodList(transactionalMethods);
                beanDefinitionMap.put(name, beanDefinition);
            }
        }
        return beanDefinitionMap;
    }

    private Set<String> lookUpTransactional(Class<?> beanClass) {
        Set<String> enhanceMethods = new HashSet<>();
        for (Method declaredMethod : beanClass.getDeclaredMethods()) {
            if (declaredMethod.isAnnotationPresent(Transactional.class)) {
                enhanceMethods.add(declaredMethod.getName());
            }
        }
        return enhanceMethods;
    }

    private void injectAutowiredProperties(BeanDefinition beanDefinition, Field[] declaredFields) {
        Map<String, Class<?>> autowiredProperties = new HashMap<>();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Autowired.class)) {
                autowiredProperties.put(declaredField.getName(), declaredField.getType());
            }
        }
        beanDefinition.setAutowiredProperties(autowiredProperties);
    }

    private String getName(Class<?> beanClass) {
        Service service = beanClass.getDeclaredAnnotation(Service.class);
        if (service != null && StringUtil.isNotEmpty(service.value())) {
            return service.value();
        }
        Component component = beanClass.getDeclaredAnnotation(Component.class);
        if (component != null && StringUtil.isNotEmpty(component.value())) {
            return component.value();
        }
        return getClassName(beanClass);
    }

    private String getClassName(Class<?> clazz) {
        String name = clazz.getName();
        name = name.substring(name.lastIndexOf(".") + 1);
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    private void load(File file, String packageName, ClassLoader classLoader, List<Class<?>> classList)
            throws ClassNotFoundException {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File curFile : Objects.requireNonNull(file.listFiles())) {
                if (curFile.exists() && curFile.isDirectory()) {
                    load(curFile, packageName, classLoader, classList);
                } else {
                    loadBeanClass(file, packageName, classLoader, classList, curFile);
                }
            }
        } else {
            loadBeanClass(file, packageName, classLoader, classList, file);
        }
    }

    private void loadBeanClass(File file, String packageName, ClassLoader classLoader, List<Class<?>> classList
            , File curFile) throws ClassNotFoundException {
        String newPackageName = getPackageName(file, packageName);
        String name = newPackageName + "." + curFile.getName().substring(0, curFile.getName().indexOf(".class"));
        Class<?> beanClass = classLoader.loadClass(name);
        //  加载Service注解标记的类
        if (beanClass.isAnnotationPresent(Service.class) || beanClass.isAnnotationPresent(Component.class)) {
            classList.add(beanClass);
        }
    }

    private String getPackageName(File file, String packageName) {
        return file.getPath().substring(file.getPath().indexOf(packageName)).replaceAll("/", ".");
    }


    private BeanFactory obtainBeanFactory(Class<?> configClass) throws IOException, InvocationTargetException
            , NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        BeanFactory beanFactory = new BeanFactoryImpl();
        //  1、校验是否为配置类
        doCheckConfigClass(configClass);
        for (Annotation declaredAnnotation : configClass.getDeclaredAnnotations()) {
            System.out.println(declaredAnnotation);
        }
        //  2、loadProperties
        Properties properties = loadProperties(configClass.getDeclaredAnnotation(PropertySource.class));
        //  3、loadBean,扫描代Bean注解的方法
        ContextConfiguration contextConfiguration = getConfig(beanFactory, configClass, properties);
        //  4、scan
        contextConfiguration.getBeanDefinitionMap().putAll(lookUpComponents(configClass
                .getDeclaredAnnotation(ComponentScan.class)));

        Context.CONTEXT_CONFIGURATION = contextConfiguration;
        Context.BEAN_FACTORY = beanFactory;
        return beanFactory;
    }


    public void destroy() {
        if (Context.BEAN_FACTORY != null) {
            Context.BEAN_FACTORY.destroy();
            Context.BEAN_FACTORY = null;
        }

        if (Context.CONTEXT_CONFIGURATION != null) {
            Context.CONTEXT_CONFIGURATION.setConfig(null);
            Context.CONTEXT_CONFIGURATION.setProperties(null);
            Context.CONTEXT_CONFIGURATION.setBeanDefinitionMap(null);
        }
    }


}
