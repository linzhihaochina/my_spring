package com.youngforcoding.listener;


import com.youngforcoding.context.ContextLoader;
import com.youngforcoding.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *    
 *  *  
 *  * @Description:  Web形式启动IOC容器   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-22 23:31   
 *  *    
 *  
 */
@WebListener
public class ContextLoaderListener extends ContextLoader implements ServletContextListener {

    private final static String CONTEXT_CLASS_PARAM = "contextClass";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String configClassParam = servletContext.getInitParameter(CONTEXT_CLASS_PARAM);

        if (StringUtil.isNotEmpty(configClassParam)) {
            //  配置了这两个context的变量才能启动Spring容器
            loadIocContainer(servletContext, configClassParam);
        }


    }

    private void loadIocContainer(ServletContext servletContext, String configClassParam) {
        servletContext.setAttribute("context", init(configClassParam));
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        destroy();
    }


}
