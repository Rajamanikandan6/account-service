package com.maveric.accountservice.feignconsumer;

import com.maveric.accountservice.dto.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@FeignClient(value = "feignUser",url = "http://localhost:3015/api/v1")
public interface BalanceServiceConsumer {

    @GetMapping("/accounts/{accountId}/accountBalance")
    ResponseEntity<List<Balance>> getBalanceDetails(@PathVariable String accountId, @RequestHeader(value = "userId") String userId);
    //delete balances for perticular account
    @DeleteMapping("api/v1/accounts/{accountId}/balances")
    public ResponseEntity<String> deleteBalanceByAccountId(@PathVariable String accountId);


    @GetMapping("/accounts/{accountId}/balances/accountBalance")
    ResponseEntity<Balance> getBalanceAccountDetails(@PathVariable String accountId, @RequestHeader(value = "userId") String userId);

    @PostMapping("/accounts/{accountId}/balances")
    ResponseEntity<Balance> createBalanceForAccount(@RequestBody Balance balance,@PathVariable String accountId, @RequestHeader(value = "userId") String userId);

}
