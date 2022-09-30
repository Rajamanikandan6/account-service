package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.Balance;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.feignconsumer.BalanceServiceConsumer;
import com.maveric.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.maveric.accountservice.constants.Constants.BALANCE_NOT_FOUND_MESSAGE;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    BalanceServiceConsumer balanceServiceConsumer;

    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts(@PathVariable String customerId, @RequestParam(defaultValue = "0") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer pageSize){
        List<AccountDto> accountDtoResponse = accountService.getAccounts(page, pageSize);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }
    @GetMapping("customers/{customerId}/customerAccounts")
    public ResponseEntity<List<AccountDto>> getAccountsbyId(@PathVariable String customerId){
        List<AccountDto> accountDtoResponse = accountService.getAccountsById(customerId);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }
    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<AccountDto> createAccount(@PathVariable String customerId, @Valid @RequestBody AccountDto accountDto) {
        AccountDto accountDtoResponse = accountService.createAccount(accountDto);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable String customerId,@PathVariable String accountId,@RequestHeader(value = "userId") String userId) {
        AccountDto accountDtoResponse = accountService.getAccountDetailsById(accountId);
        ResponseEntity<Balance> balanceDto = balanceServiceConsumer.getBalanceAccountDetails(accountId,userId);
        accountDtoResponse.setBalance(balanceDto.getBody());
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String customerId,@PathVariable String accountId,@RequestBody AccountDto accountDto) {
        AccountDto accountDtoResponse = accountService.updateAccountDetails(accountId,accountDto);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String customerId,@PathVariable String accountId) {
        String result = accountService.deleteAccount(accountId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
