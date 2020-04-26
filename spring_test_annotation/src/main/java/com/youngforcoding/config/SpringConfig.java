package com.youngforcoding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.youngforcoding.factory.AccountFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 *    
 *  *  
 *  * @Description:  纯注解启动的配置类   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-19 10:22   
 *  *    
 *  
 */
@Configuration
@ComponentScan("com.youngforcoding")
@PropertySource({"classpath:jdbc.properties"})
@EnableAspectJAutoProxy
@EnableTransactionManagement

public class SpringConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean("dataSource")
    @Scope("singleton")
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Bean("transactionManager")
    @Scope("singleton")
    public DataSourceTransactionManager getTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("jdbcTemplate")
    @Scope("singleton")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    不能提前配置加载BeanFactoryPostProcessor，如果提前加载的话会导致SpringConfig这个类被提前加载
//    ，不能够经过BeanPostProcessor的增强处理导致，Value注解指定的属性之获取不到
//    @Bean("accountBean")
//    public AccountFactoryBean getAccountBean() {
//        return new AccountFactoryBean();
//    }

//    @Bean("accountBean")
//    public static AccountFactoryBean getAccountBean() {
//        return new AccountFactoryBean();
//    }
}
