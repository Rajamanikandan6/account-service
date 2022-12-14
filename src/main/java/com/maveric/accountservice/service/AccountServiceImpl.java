package com.maveric.accountservice.service;

import com.maveric.accountservice.constants.Type;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.mapper.AccountMapper;
import com.maveric.accountservice.model.AccountModel;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.maveric.accountservice.constants.Constants.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Autowired
    private AccountMapper mapper;
    @Override
    public List<AccountDto> getAccounts(String customerId,Integer page, Integer pageSize) {
//        Pageable paging = PageRequest.of(page, pageSize);
//        Page<AccountModel> pageResult = repository.findAll(paging);
        Page<AccountModel> pageResult = repository.findAllByCustomerId(PageRequest.of(page, pageSize),customerId);
        if(pageResult.hasContent()) {
            List<AccountModel> account = pageResult.getContent();
            return mapper.mapToDto(account);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        accountDto.setCreatedAt(getCurrentDateTime());
        accountDto.setUpdatedAt(getCurrentDateTime());

        AccountModel account = mapper.map(accountDto);
        AccountModel accountResult = repository.save(account);
        return  mapper.map(accountResult);
    }

    @Override
    public AccountDto getAccountDetailsById(String customerId, String accountId) {
        AccountModel accountResult=repository.findAccountByCustomerId(customerId,accountId);
        if(accountResult==null){
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE+accountId);
        }
        return mapper.map(accountResult);
    }
    @Override
    public AccountDto updateAccountDetails(String accountId, AccountDto accountDto) {
        AccountModel accountResult=repository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE+accountId));
        accountResult.set_id(accountResult.get_id());
        accountResult.setCustomerId(accountDto.getCustomerId());
        accountResult.setType(accountDto.getType());
        accountResult.setCreatedAt(accountResult.getCreatedAt());
        accountResult.setUpdatedAt(getCurrentDateTime());
        AccountModel accountUpdated = repository.save(accountResult);
        return mapper.map(accountUpdated);
    }

    @Override
    public String deleteAccount(String accountId) {
        if(!repository.findById(accountId).isPresent())
        {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE+accountId);
        }
        repository.deleteById(accountId);
        return ACCOUNT_DELETED_SUCCESS;
    }

    @Override
    public List<AccountDto> getAccountsById(String customerId) {
        List<AccountModel> account=repository.findByCustomerId(customerId);
        return mapper.mapToDto(account);
    }
}
