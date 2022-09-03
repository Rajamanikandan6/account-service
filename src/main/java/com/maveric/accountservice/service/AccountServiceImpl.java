package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public List<AccountDto> getAccountById(String customerId) {
        List<AccountDto> accountList= repository.findAll(customerId);
//        if(accountList.size()==0)
//        {
//            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE);
//        }
        return accountList;
    }
}
