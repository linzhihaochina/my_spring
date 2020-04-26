package com.youngforcoding;

import com.alibaba.druid.pool.DruidDataSource;
import com.youngforcoding.annotation.*;
import com.youngforcoding.util.JdbcTemplate;

import javax.sql.DataSource;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-25 17:52   
 *  *    
 *  
 */
@PropertySource("jdbc.properties")
@ComponentScan("com.youngforcoding")
@Configuration
public class SpringConfig {

    @Value("jdbc.driver")
    private String driver;

    @Value("jdbc.url")
    private String url;

    @Value("jdbc.username")
    private String username;

    @Value("jdbc.password")
    private String password;

    @Bean
    public DataSource getDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }



}
