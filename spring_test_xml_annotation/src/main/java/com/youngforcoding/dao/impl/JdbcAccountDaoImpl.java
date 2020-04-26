package com.youngforcoding.dao.impl;


import com.youngforcoding.dao.AccountDao;
import com.youngforcoding.pojo.Account;
import com.youngforcoding.util.ConnectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 应癫
 */
@Repository("accountDao")
@Scope("singleton")
public class JdbcAccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        return jdbcTemplate.update(sql,account.getMoney(),account.getCardNo());
    }
}
