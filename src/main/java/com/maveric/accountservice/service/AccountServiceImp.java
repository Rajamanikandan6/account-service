package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.Account;
import com.maveric.accountservice.model.AccountModel;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static com.maveric.accountservice.constants.Constants.*;


@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    AccountRepository repository;

    @Override
    public List<Account> getAccountById(String customerId) {
        return repository.findAll(customerId);
    }

    @Override
    public Account createAccount(Account account) {

        account.setCreatedAt(getCurrentDateTime());
        account.setUpdatedAt(getCurrentDateTime());
        return repository.save(account);
    }
}
