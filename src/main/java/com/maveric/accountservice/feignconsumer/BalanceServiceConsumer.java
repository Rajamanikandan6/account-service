package com.maveric.accountservice.feignconsumer;

import com.maveric.accountservice.dto.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="balance-service")
@Service
public interface BalanceServiceConsumer {
    @GetMapping("api/v1/accounts/{accountId}/balance")
    public ResponseEntity<Balance> getBalance(@PathVariable String accountId);
}
