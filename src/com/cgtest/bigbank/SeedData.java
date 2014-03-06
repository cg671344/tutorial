package com.cgtest.bigbank;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class SeedData implements InitializingBean{
	
	@Autowired
    private BankDao bankDao;

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(bankDao);
        bankDao.createOrUpdateAccount(new Account("rod"));
        bankDao.createOrUpdateAccount(new Account("dianne"));
        bankDao.createOrUpdateAccount(new Account("scott"));
        bankDao.createOrUpdateAccount(new Account("peter"));
    }

    public void setBankDao(BankDao bankDao) {
        this.bankDao = bankDao;
    }

}
