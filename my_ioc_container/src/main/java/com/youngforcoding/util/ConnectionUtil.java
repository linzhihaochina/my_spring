package com.youngforcoding.util;

import java.sql.Connection;

/**
 *    
 *  *  
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:       linZhiHao   
 *  * @CreateDate:   2020-04-25 19:18   
 *  *    
 *  
 */
public class ConnectionUtil {

    /**
     * 存储当前线程的连接
     */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


    public static void setConnection(Connection connection) {
        threadLocal.set(connection);
    }

    public static Connection getConnection() {
        return threadLocal.get();
    }

}
