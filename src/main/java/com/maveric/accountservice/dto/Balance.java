package com.maveric.accountservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

enum Currency{
    INR, DOLLAR, EURO
}

@Data
public class Balance {
    private String _id;
    private String amount;
    private Currency currency;
    private String accountId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
