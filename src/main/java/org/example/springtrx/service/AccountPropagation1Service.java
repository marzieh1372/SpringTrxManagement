package org.example.springtrx.service;

import org.example.springtrx.model.entity.Account;
import org.example.springtrx.repo.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountPropagation1Service {

    private final AccountRepository accountRepository;

    public AccountPropagation1Service(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account saveAccountWithoutTRX(Account account, boolean hasException) {
        Account savedAccount = accountRepository.save(account);
        if (hasException) {
            throw new RuntimeException("DummyException: this should cause rollback of both inserts!");
        }
        return savedAccount;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account saveAccountWithRequiredNew(Account account, boolean hasException) {
        Account savedAccount = accountRepository.save(account);
        if (hasException) {
            throw new RuntimeException("DummyException: this should cause rollback of both inserts!");
        }
        return savedAccount;
    }




}
