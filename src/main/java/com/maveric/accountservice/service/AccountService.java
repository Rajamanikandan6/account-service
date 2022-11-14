package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public List<AccountDto> getAccounts(String customerId, Integer page, Integer pageSize);
    public AccountDto createAccount(AccountDto account);
    public AccountDto getAccountDetailsById(String customerId, String accountId);
    public AccountDto updateAccountDetails(String accountId,AccountDto accountDto);
    public String deleteAccount(String accountId);

    public List<AccountDto> getAccountsById(String customerId);
}
