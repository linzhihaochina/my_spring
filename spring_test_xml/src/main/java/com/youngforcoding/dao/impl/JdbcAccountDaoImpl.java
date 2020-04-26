package com.youngforcoding.dao.impl;


import com.youngforcoding.dao.AccountDao;
import com.youngforcoding.pojo.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 应癫
 */
public class JdbcAccountDaoImpl implements AccountDao {

//    private JdbcTemplate connectionUtils;
//
//    public void setConnectionUtils(JdbcTemplate connectionUtils) {
//        this.connectionUtils = connectionUtils;
//    }


    public void init() {
        System.out.println("初始化方法.....");
    }

    public void destory() {
        System.out.println("销毁方法......");
    }

    //    @Override
//    public Account queryAccountByCardNo(String cardNo) throws Exception {
//        //从连接池获取连接
//        // Connection con = DruidUtils.getInstance().getConnection();
//        Connection con = connectionUtils.getCurrentThreadConn();
//        String sql = "select * from account where cardNo=?";
//        PreparedStatement preparedStatement = con.prepareStatement(sql);
//        preparedStatement.setString(1,cardNo);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        Account account = new Account();
//        while(resultSet.next()) {
//            account.setCardNo(resultSet.getString("cardNo"));
//            account.setName(resultSet.getString("name"));
//            account.setMoney(resultSet.getInt("money"));
//        }
//
//        resultSet.close();
//        preparedStatement.close();
//        //con.close();
//
//        return account;
//    }
//
//    @Override
//    public int updateAccountByCardNo(Account account) throws Exception {
//
//        // 从连接池获取连接
//        // 改造为：从当前线程当中获取绑定的connection连接
//        //Connection con = DruidUtils.getInstance().getConnection();
//        Connection con = connectionUtils.getCurrentThreadConn();
//        String sql = "update account set money=? where cardNo=?";
//        PreparedStatement preparedStatement = con.prepareStatement(sql);
//        preparedStatement.setInt(1,account.getMoney());
//        preparedStatement.setString(2,account.getCardNo());
//        int i = preparedStatement.executeUpdate();
//
//        preparedStatement.close();
//        //con.close();
//        return i;
//    }
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        String sql = "select * from account where cardNo=?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account account = new Account();
                account.setName(resultSet.getString("name"));
                account.setCardNo(resultSet.getString("cardNo"));
                account.setMoney(resultSet.getInt("money"));
                return account;
            }
        }, cardNo);
    }

    @Override
    public int updateAccountByCardNo(Account account) throws Exception {
        String sql = "update account set money=? where cardNo=?";
        return jdbcTemplate.update(sql, account.getMoney(), account.getCardNo());
    }
}
