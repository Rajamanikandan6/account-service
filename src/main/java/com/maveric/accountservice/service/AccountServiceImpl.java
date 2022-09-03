package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static com.maveric.accountservice.constants.Constants.getCurrentDateTime;

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
    @Override
    public AccountDto createAccount(AccountDto account) {

        account.setCreatedAt(getCurrentDateTime());
        account.setUpdatedAt(getCurrentDateTime());
        return repository.save(account);
    }

    @Override
    public AccountDto getAccountByAccId(String customerId, String accountId) {
        AccountDto account = null;
        Iterable<AccountDto> accnt=repository.findAllById(Arrays.asList(customerId,accountId));

        for (AccountDto acc:accnt){
            account=acc;
        }
//        if(account==null){
//            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE);
//        }
        return account;
    }
    @Override
    public AccountDto updateAccount(String customerId, String accountId, AccountDto newAccount) {
        AccountDto acc=getAccountByAccId(customerId, accountId);
        acc.setType(newAccount.getType());
        return repository.save(acc);
    }

    @Override
    public String deleteAccount(String customerId, String accountId) {
        repository.delete(getAccountByAccId(customerId,accountId));
        return "Account deleted successfully.";

    }
}
