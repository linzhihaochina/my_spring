package com.youngforcoding.service.impl;


import com.youngforcoding.dao.AccountDao;
import com.youngforcoding.pojo.Account;
import com.youngforcoding.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 应癫
 */
@Service("transferService")
@Transactional
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountDao accountDao;


    @Override
//    @Transactional(rollbackFor = Exception.class, timeout = 1000, isolation = Isolation.REPEATABLE_READ
//            , propagation = Propagation.REQUIRED)
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

        /*try{
            // 开启事务(关闭事务的自动提交)
            TransactionManager.getInstance().beginTransaction();*/

            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(to);
            int c = 1/0;
            accountDao.updateAccountByCardNo(from);

        /*    // 提交事务

            TransactionManager.getInstance().commit();
        }catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            TransactionManager.getInstance().rollback();

            // 抛出异常便于上层servlet捕获
            throw e;

        }*/




    }
}
