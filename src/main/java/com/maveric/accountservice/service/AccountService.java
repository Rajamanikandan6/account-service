package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
   List<Account> getAccountById(String customerId);
}
