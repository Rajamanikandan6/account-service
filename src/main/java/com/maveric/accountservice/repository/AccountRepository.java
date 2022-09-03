package com.maveric.accountservice.repository;

import com.maveric.accountservice.dto.AccountDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<AccountDto, String> {
    @Query(value = "{customerId:'?0'}")
    List<AccountDto> findAll(String customerId);
}
