package com.maveric.accountservice.dto;

import lombok.Data;

import java.util.Currency;

@Data
public class Balance {
    private String _id;
    private String amount;
    private Currency currency;
    private String accountId;
    private String createdAt;
    private String updatedAt;
}
