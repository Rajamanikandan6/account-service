package com.maveric.accountservice.feignconsumer;

import com.maveric.accountservice.dto.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "feignUser",url = "http://localhost:3015/api/v1")
public interface BalanceServiceConsumer {
    @GetMapping("/accounts/{accountId}/accountBalance")
    ResponseEntity<List<Balance>> getBalanceDetails(@PathVariable String accountId, @RequestHeader(value = "userId") String userId);
    
}
