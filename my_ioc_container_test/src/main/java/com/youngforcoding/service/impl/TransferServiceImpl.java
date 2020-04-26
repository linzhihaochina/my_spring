package com.youngforcoding.service.impl;


import com.youngforcoding.annotation.Autowired;
import com.youngforcoding.annotation.Service;
import com.youngforcoding.annotation.Transactional;
import com.youngforcoding.dao.AccountDao;
import com.youngforcoding.pojo.Account;
import com.youngforcoding.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {

    //private AccountDao accountDao = new JdbcAccountDaoImpl();

    // private AccountDao accountDao = (AccountDao) BeanFactoryImpl.getBean("accountDao");

    // 最佳状态
    @Autowired
    private AccountDao accountDao;


    @Override
    @Transactional
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountDao.updateAccountByCardNo(to);
//        int c = 1/0;
        accountDao.updateAccountByCardNo(from);
    }
}
