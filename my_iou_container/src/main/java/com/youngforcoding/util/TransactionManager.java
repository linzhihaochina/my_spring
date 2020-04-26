package com.youngforcoding.util;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 事务管理器类：负责手动事务的开启、提交、回滚
 */
public class TransactionManager {

    public static DataSource dataSource;

    // 开启手动事务控制
    public static void beginTransaction() throws SQLException {
        ConnectionUtil.setConnection(dataSource.getConnection());
        ConnectionUtil.getConnection().setAutoCommit(false);
    }


    // 提交事务
    public static void commit() throws SQLException {
        ConnectionUtil.getConnection().commit();
    }


    // 回滚事务
    public static void rollback() throws SQLException {
        ConnectionUtil.getConnection().rollback();
    }
}
