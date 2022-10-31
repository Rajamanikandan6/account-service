package com.maveric.accountservice.feignconsumer;

import com.maveric.accountservice.dto.Balance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "feignUser",url = "http://localhost:3020/api/v1")
public interface TransactionServiceConsumer {
    @DeleteMapping("accounts/{accountId}/transactions")
    public String deleteAllTransaction(@PathVariable String accountId);

}
