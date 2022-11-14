package com.maveric.accountservice.service;

import com.maveric.accountservice.constants.Type;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.mapper.AccountMapperImpl;
import com.maveric.accountservice.model.AccountModel;
import com.maveric.accountservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static com.maveric.accountservice.constants.Constants.ACCOUNT_DELETED_SUCCESS;
import static com.maveric.accountservice.constants.Constants.ACCOUNT_NOT_FOUND_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;
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
        //when(mapper.map(any(AccountDto.class))).thenReturn(getAccount());
        when(mapper.map(any(AccountModel.class))).thenReturn(getAccountDto());
        when(repository.save(any())).thenReturn(getAccount());
        AccountDto accountDto= service.createAccount(getAccountDto());
        assertSame(accountDto.getCustomerId(), getAccountDto().getCustomerId());
    }

    @Test
    public void testGetAccounts() {
        Page<AccountModel> pagedResponse = new PageImpl(Arrays.asList(getAccount(),getAccount()));
        when(repository.findAllByCustomerId(any(Pageable.class),any())).thenReturn(pagedResponse);
        when(mapper.mapToDto(any())).thenReturn(Arrays.asList(getAccountDto(),getAccountDto()));
        List<AccountDto> accountDtos= service.getAccounts("1234",1,10);
        assertTrue(accountDtos.size()==2);
    }

    @Test
    public void testGetAccountById() {
        when(repository.findAccountByCustomerId("1234","1234")).thenReturn(getAccount());
        when(mapper.map(any(AccountModel.class))).thenReturn(getAccountDto());
        AccountDto accountDto= service.getAccountDetailsById("1234","1234");
        assertSame(accountDto.getCustomerId(), getAccountDto().getCustomerId());
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
       String returnStr = service.deleteAccount("123");
        assertTrue(returnStr.equals(ACCOUNT_DELETED_SUCCESS));
    }

    @Test(expected = AccountNotFoundException.class)
   // @ExpectedException(AccountNotFoundException.class)
    public void testDeleteInvalidAccountById() {
        when(repository.findById("000")).thenReturn(Optional.of(getAccount()));
        willDoNothing().given(repository).deleteById("000");
        String returnStr = service.deleteAccount("123");
    }
}
