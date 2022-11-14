package com.maveric.accountservice.repository;


import com.maveric.accountservice.model.AccountModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<AccountModel, String> {

    List<AccountModel> findByCustomerId(String customerId);
    @Query("{customerId:?0}")
    Page<AccountModel> findAllByCustomerId(Pageable pageable, String customerId);

    @Query("{'customerId':?0, '_id':?1}")
    AccountModel findAccountByCustomerId(String customerId, String accountId);
}
