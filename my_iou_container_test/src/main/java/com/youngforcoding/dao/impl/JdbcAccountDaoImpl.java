package com.youngforcoding.dao.impl;


import com.youngforcoding.annotation.Autowired;
import com.youngforcoding.annotation.Component;
import com.youngforcoding.dao.AccountDao;
import com.youngforcoding.pojo.Account;
import com.youngforcoding.util.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 应癫
 */
@Component
public class JdbcAccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account queryAccountByCardNo(String cardNo) throws Exception {
        Connection con = jdbcTemplate.getCurrentThreadConn();
        String sql = "select * from account where cardNo=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, cardNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        Account account = new Account();
        while (resultSet.next()) {
            account.setCardNo(resultSet.getString("cardNo"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getInt("money"));
        }

        resultSet.close();
        preparedStatement.close();
        return account;
    }

    @Override
    public int updateAccountByCardNo(Account account) throws Exception {
        Connection con = jdbcTemplate.getCurrentThreadConn();
        PreparedStatement preparedStatement = con.prepareStatement("update account set money=? where cardNo=?");
        preparedStatement.setInt(1, account.getMoney());
        preparedStatement.setString(2, account.getCardNo());
        int i = preparedStatement.executeUpdate();

        preparedStatement.close();
        return i;
    }

    public static void main(String[] args) {
        System.out.println(AccountDao.class.isAssignableFrom(new JdbcAccountDaoImpl().getClass()));
    }
}
