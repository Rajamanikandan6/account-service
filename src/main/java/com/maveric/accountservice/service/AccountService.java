package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
   public List<Account> getAccountById(String customerId);
   public Account createAccount(Account transaction);
   public Account getAccountByAccId(String customerId, String accountId);
   public Account updateAccount(String customerId, String accountId, Account account);
}
