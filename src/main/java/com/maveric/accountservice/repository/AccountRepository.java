package com.maveric.accountservice.repository;


import com.maveric.accountservice.model.AccountModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountModel, String> {
}
