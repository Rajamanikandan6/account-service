package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
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
    public ResponseEntity<List<AccountDto>> getAccountsById(@PathVariable String customerId, @RequestParam(defaultValue = "0") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer pageSize){
        List<AccountDto> accountResponse=accountService.getAccountById(customerId);
        return new ResponseEntity<List<AccountDto>>(accountResponse, HttpStatus.OK);
    }

    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<AccountDto> createAccount (@PathVariable("customerId") String customerId, @Valid
    @RequestBody AccountDto account){
//        if(customerId==null)
//        {
//            throw new CustomerNotFoundException();
//        }
        AccountDto accounts=accountService.createAccount(account);

        return new ResponseEntity<AccountDto>(accounts,HttpStatus.OK);
    }

    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("customerId") String customerId,
                                              @PathVariable("accountId") String accountId){
        AccountDto accounts=accountService.getAccountByAccId(customerId, accountId);
        return new ResponseEntity<AccountDto>(accounts, HttpStatus.OK);
    }
}
