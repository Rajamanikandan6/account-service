package com.maveric.accountservice.feignconsumer;

import com.maveric.accountservice.dto.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "feignUser",url = "http://localhost:3015/api/v1")
public interface BalanceServiceConsumer {
    @GetMapping("/accounts/{accountId}/balances/accountBalance")
    ResponseEntity<Balance> getBalanceAccountDetails(@PathVariable String accountId, @RequestHeader(value = "userId") String userId);

    @PostMapping("/accounts/{accountId}/balances")
    ResponseEntity<Balance> createBalanceForAccount(@RequestBody Balance balance,@PathVariable String accountId, @RequestHeader(value = "userId") String userId);
}
