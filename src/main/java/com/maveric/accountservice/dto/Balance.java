package com.maveric.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Currency;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    private String _id;
    private String amount;
    private Currency currency;
    private String accountId;
    private String createdAt;
    private String updatedAt;
}
