package com.youngforcoding.util;

import com.youngforcoding.exception.DataSourceNotFindException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class JdbcTemplate {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 从当前线程获取连接
     */
    public Connection getCurrentThreadConn() throws SQLException {
        if (dataSource == null) {
            throw new DataSourceNotFindException("没有找到配置的数据源!");
        }

        /**
         * 判断当前线程中是否已经绑定连接，如果没有绑定，需要从连接池获取一个连接绑定到当前线程
         */
        Connection connection = ConnectionUtil.getConnection();
        if (connection == null) {
            // 绑定到当前线程
            ConnectionUtil.setConnection(dataSource.getConnection());
        }
        return connection;
    }
}
