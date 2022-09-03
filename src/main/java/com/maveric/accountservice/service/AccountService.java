package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public List<AccountDto> getAccountById(String customerId);
    public AccountDto createAccount(AccountDto account);
    public AccountDto getAccountByAccId(String customerId, String accountId);
    public AccountDto updateAccount(String customerId, String accountId, AccountDto account);

}
