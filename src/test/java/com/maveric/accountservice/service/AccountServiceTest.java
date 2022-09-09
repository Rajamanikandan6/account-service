package com.maveric.accountservice.service;

import com.maveric.accountservice.constants.Type;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.mapper.AccountMapperImpl;
import com.maveric.accountservice.model.AccountModel;
import com.maveric.accountservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class AccountServiceTest {
    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapperImpl mapper;

    @Mock
    private Page pageResult;



    @Test
    public void testCreateAccount() throws Exception{
        when(mapper.map(any(AccountDto.class))).thenReturn(getAccount());
        when(mapper.map(any(AccountModel.class))).thenReturn(getAccountDto());
        when(repository.save(any())).thenReturn(getAccount());
    }

    @Test
    public void testGetAccounts() {
        Page<AccountModel> pagedResponse = new PageImpl(Arrays.asList(getAccount(),getAccount()));
        when(repository.findAll(any(Pageable.class))).thenReturn(pagedResponse);
        when(pageResult.hasContent()).thenReturn(true);
        when(pageResult.getContent()).thenReturn(Arrays.asList(getAccount(),getAccount()));
        when(mapper.mapToDto(any())).thenReturn(Arrays.asList(getAccountDto(),getAccountDto()));
    }

    @Test
    public void testGetAccountById() {
        when(repository.findById("123")).thenReturn(Optional.of(getAccount()));
        when(mapper.map(any(AccountModel.class))).thenReturn(getAccountDto());
    }

    @Test
    public void testUpdateAccountById() {
        when(repository.findById("123")).thenReturn(Optional.of(getAccount()));
        when(repository.save(any())).thenReturn(getAccount());
        when(mapper.map(any(AccountModel.class))).thenReturn(getAccountDto());

        AccountDto accountDto = service.updateAccountDetails("123",getAccountDto());

        assertSame(accountDto.getType(),getAccountDto().getType());
    }

    @Test
    public void testDeleteAccountById() {
        when(repository.findById("123")).thenReturn(Optional.of(getAccount()));
        willDoNothing().given(repository).deleteById("123");
    }
}
