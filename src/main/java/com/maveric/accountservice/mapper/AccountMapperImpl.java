package com.maveric.accountservice.mapper;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.AccountModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapperImpl implements AccountMapper{
    @Override
    public AccountModel map(AccountDto accountDto) {
        return AccountModel.builder()
                ._id(accountDto.get_id())
                .customerId(accountDto.getCustomerId())
                .type(accountDto.getType())
                .createdAt(accountDto.getCreatedAt())
                .updatedAt(accountDto.getUpdatedAt())
                .build();
    }

    @Override
    public AccountDto map(AccountModel account) {
        return AccountDto.builder()
                ._id(account.get_id())
                .customerId(account.getCustomerId())
                .type(account.getType())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build();
    }

    @Override
    public List<AccountModel> mapToModel(List<AccountDto> accounts) {
        return accounts.stream().map(accountDto ->  AccountModel.builder()
                ._id(accountDto.get_id())
                .customerId(accountDto.getCustomerId())
                .type(accountDto.getType())
                .createdAt(accountDto.getCreatedAt())
                .updatedAt(accountDto.getUpdatedAt())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> mapToDto(List<AccountModel> accounts) {
        return accounts.stream().map(account ->  AccountDto.builder()
                ._id(account.get_id())
                .customerId(account.getCustomerId())
                .type(account.getType())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .build()
        ).collect(Collectors.toList());
    }
}
