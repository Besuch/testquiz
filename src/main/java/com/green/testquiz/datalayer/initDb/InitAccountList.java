package com.green.testquiz.datalayer.initDb;

import com.green.testquiz.datalayer.mocks.MockAccountList;
import com.green.testquiz.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitAccountList {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    private void init() {
        if (accountRepository.findAll().isEmpty()){
            MockAccountList.ACCOUNTS.forEach(account -> accountRepository.save(account));
        }
    }
}
