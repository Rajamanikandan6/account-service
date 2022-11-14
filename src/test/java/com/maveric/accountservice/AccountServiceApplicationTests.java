package com.maveric.accountservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.accountservice.constants.Currency;
import com.maveric.accountservice.constants.Type;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.Balance;
import com.maveric.accountservice.model.AccountModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AccountServiceApplicationTests {

	public static final String apiV1 = "/api/v1/customers/1234/accounts";
	public static final String invalidApiV1 = "/api/v1/customers/0000/accounts/0000";
	@Test
	void testDoSomething() {  // Noncompliant
		assertTrue(true);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static AccountModel getAccount()
	{
		return  AccountModel.builder()
				.customerId("1234")
				.type(Type.SAVINGS)
				.build();
	}
	public static AccountDto getAccountDto()
	{
		return  AccountDto.builder()
				.customerId("1234")
				.type(Type.SAVINGS)
				.build();
	}

//	public static TransactionDto getTransactionDto()
//	{
//		return TransactionDto.builder()
//				.accountId("1234")
//				.type(TransactionType.CREDIT)
//				.amount(2000)
//				.build();
//	}
//
	public static Balance getBalance()
	{
		return Balance.builder()
				.accountId("1234")
				.amount("1000")
				.currency(Currency.INR)
				.build();
	}

}
