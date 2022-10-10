package com.maveric.accountservice.controller;

import com.maveric.accountservice.constants.Currency;
import com.maveric.accountservice.dto.Balance;
import com.maveric.accountservice.feignconsumer.BalanceServiceConsumer;
import com.maveric.accountservice.service.AccountService;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//import static org.springframework.http.RequestEntity.post;

import java.util.ArrayList;
import java.util.List;

import static com.maveric.accountservice.AccountServiceApplicationTests.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes=AccountController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
@Tag("Integration Tests")
public class AccountControllerTest {

    @Mock
    ResponseEntity<List<Balance>> balance;

    @Autowired
    private MockMvc mock;

    @MockBean
    private AccountService accountService;

    @MockBean
    BalanceServiceConsumer balanceServiceConsumer;

    @Test
    public void shouldGetStatus200WhenRequestMadeTogetAccounts() throws Exception
    {
        mock.perform(get(apiV1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus201WhenRequestMadeToCreateAccounts() throws Exception
    {
        mock.perform(post(apiV1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToGetAccountDetails() throws Exception
    {
        when(accountService.getAccountDetailsById(any(String.class))).thenReturn(getAccountDto());
        when(balanceServiceConsumer.getBalanceAccountDetails(any(),any())).thenReturn(getSampleBalance());
//        when(balance.getBody()).thenReturn(getBalance());
//        when(transactionServiceConsumer.getTransactionsByAccountId(any(String.class))).thenReturn(transactionDto);
//        when(transactionDto.getBody()).thenReturn(Arrays.asList(getTransactionDto(),getTransactionDto()));

        mock.perform(get(apiV1+"/accountId1").header("userId","434325325"))
                .andExpect(status().isOk())
                .andReturn();
    }

    public ResponseEntity<Balance> getSampleBalance(){

        Balance balance = new Balance();
        balance.setCurrency(Currency.INR);
        balance.setAccountId("4");
        balance.setAmount("200");
        return ResponseEntity.status(HttpStatus.OK).body(balance);
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToUpdateAccount() throws Exception
    {
        mock.perform(put(apiV1+"/accountId1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldGetStatus200WhenRequestMadeToDeleteAccount() throws Exception
    {
        mock.perform(delete(apiV1+"/accountId1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
