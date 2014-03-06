package com.cgtest.bigbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class BankServiceImpl implements BankService {
    private BankDao bankDao;

    @Autowired
    public BankServiceImpl(BankDao bankDao) {
        Assert.notNull(bankDao);
        this.bankDao = bankDao;
    }

    public Account[] findAccounts() {
        return this.bankDao.findAccounts();
    }

    public Account post(Account account, double amount) {
        Assert.notNull(account);

        // We read account back from DAO so it reflects the latest balance
        Account a = bankDao.readAccount(account.getId());
        if (a == null) {
            throw new IllegalArgumentException("Couldn't find requested account");
        }

        a.setBalance(a.getBalance() + amount);
        bankDao.createOrUpdateAccount(a);
        return a;
    }

    public Account readAccount(Long id) {
        return bankDao.readAccount(id);
    }
}
