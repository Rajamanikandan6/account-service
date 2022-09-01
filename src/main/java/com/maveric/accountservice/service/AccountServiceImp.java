package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    AccountRepository repository;

    @Override
    public List<Account> getAccountById(String customerId) {
        return repository.findAll(customerId);
    }
}
