package org.example.springtrx.service;

import org.example.springtrx.model.entity.Account;
import org.example.springtrx.repo.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account saveAccountSimple(Account account,boolean hasException){
        Account savedAccount = accountRepository.save(account);
        if (hasException) {
            throw new RuntimeException("DummyException: this should cause rollback of both inserts!");
        }
        return savedAccount;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Account saveAccount(Account account,boolean hasException){
        Account savedAccount = accountRepository.save(account);
        if (hasException) {
            throw new RuntimeException("DummyException: this should cause rollback of both inserts!");
        }
        return savedAccount;
    }

}
