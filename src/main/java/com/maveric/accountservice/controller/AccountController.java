package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.Account;
import com.maveric.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    AccountService accountService;


    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<Account>> getAccountsById(@PathVariable String customerId, @RequestParam(defaultValue = "0") Integer page,
                                                        @RequestParam(defaultValue = "10") Integer pageSize){
    List<Account> accountResponse=accountService.getAccountById(customerId);
    return new ResponseEntity<List<Account>>(accountResponse, HttpStatus.OK);
    }

    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<Account> createAccount (@PathVariable("customerId") String customerId, @Valid
                                                  @RequestBody Account account){
        Account accounts=accountService.createAccount(account);
        return new ResponseEntity<Account>(accounts,HttpStatus.OK);
    }

}
