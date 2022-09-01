package com.maveric.accountservice.repository;

import com.maveric.accountservice.dto.Account;
import com.maveric.accountservice.model.AccountModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {
    @Query(value = "{customerId:'?0'}")
        List<Account> findAll(String customerId);
}
