package com.maveric.accountservice.repository;


import com.maveric.accountservice.model.AccountModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<AccountModel, String> {

    List<AccountModel> findByCustomerId(String customerId);
}
