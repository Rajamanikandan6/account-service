package com.maveric.accountservice.controller;

import com.maveric.accountservice.constants.Constants;
import com.maveric.accountservice.constants.Currency;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.Balance;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.exception.CustomerIdMissmatch;
import com.maveric.accountservice.feignconsumer.BalanceServiceConsumer;
import com.maveric.accountservice.feignconsumer.TransactionServiceConsumer;
import com.maveric.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.maveric.accountservice.constants.Constants.BALANCE_NOT_FOUND_MESSAGE;
import static com.maveric.accountservice.constants.Constants.CUSTOMER_ID_ERROR;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    AccountService accountService;

    @Autowired
    BalanceServiceConsumer balanceServiceConsumer;

    @Autowired
    TransactionServiceConsumer transactionServiceConsumer;

    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getAccounts(@PathVariable String customerId,
      @RequestParam(defaultValue = "0") Integer page, @RequestHeader(value = "userId") String userId,
      @RequestParam(defaultValue = "10") Integer pageSize){
        if(customerId.equals(userId)) {
            List<AccountDto> accountDtoResponse = accountService.getAccounts(customerId, page, pageSize);
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
        }else
            throw new CustomerIdMissmatch(CUSTOMER_ID_ERROR);
    }
    @GetMapping("customers/{customerId}/customerAccounts")
    public ResponseEntity<List<AccountDto>> getAccountsbyId(@PathVariable String customerId){
        List<AccountDto> accountDtoResponse = accountService.getAccountsById(customerId);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
    }
    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<AccountDto> createAccount(@PathVariable String customerId,
                                                    @Valid @RequestBody AccountDto accountDto,
                                                    @RequestHeader(value = "userId") String userId) {
        if(customerId.equals(accountDto.getCustomerId()) && customerId.equals(userId)){
            //System.out.println("thd");
            AccountDto accountDtoResponse = accountService.createAccount(accountDto);
            Balance balance=new Balance();
            balance.setAmount("0");
            balance.setCurrency(Currency.INR);
            balance.setAccountId(accountDtoResponse.get_id());
            balanceServiceConsumer.createBalanceForAccount(balance,accountDtoResponse.get_id(),userId);
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);
        }
        else{
            throw new CustomerIdMissmatch(CUSTOMER_ID_ERROR);
        }

    }

    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable String customerId,@PathVariable String accountId,@RequestHeader(value = "userId") String userId) {
        if(customerId.equals(userId)) {
            AccountDto accountDtoResponse = accountService.getAccountDetailsById(customerId, accountId);
            ResponseEntity<Balance> balanceDto = balanceServiceConsumer.getBalanceAccountDetails(accountId, userId);
            accountDtoResponse.setBalance(balanceDto.getBody());
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
        }else
        throw new CustomerIdMissmatch(CUSTOMER_ID_ERROR);
    }

    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String customerId,@PathVariable String accountId,@RequestBody AccountDto accountDto,@RequestHeader(value = "userId") String userId) {
        if(customerId.equals(accountDto.getCustomerId()) && customerId.equals(userId)) {
            AccountDto accountDtoResponse = accountService.updateAccountDetails(accountId, accountDto);
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
        }else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
           // throw new CustomerIdMissmatch(CUSTOMER_ID_ERROR);
    }

    @DeleteMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String customerId,
                                                @PathVariable String accountId) {
        transactionServiceConsumer.deleteAllTransaction(accountId);
        String result = accountService.deleteAccount(accountId);
        //delete balance and transaction details
        if(result!=null) {
            balanceServiceConsumer.deleteBalanceByAccountId(accountId);
            transactionServiceConsumer.deleteAllTransaction(accountId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(accountId+"AccountIdNotFound", HttpStatus.NOT_FOUND);
           // throw new AccountNotFoundException(accountId);
        }
    }

}
