package com.maveric.accountservice.mapper;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.model.AccountModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="AccountDetails")
public interface AccountMapper {
    AccountModel map(AccountDto accountDto);

    AccountDto map(AccountModel account);

    List<AccountModel> mapToModel (List<AccountDto> accounts);

    List<AccountDto> mapToDto (List<AccountModel> accounts);
}
