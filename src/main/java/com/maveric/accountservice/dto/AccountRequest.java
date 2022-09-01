package com.maveric.accountservice.dto;

import lombok.Data;

enum Type{
    CURRENT,SAVINGS
}

@Data
public class AccountRequest {
    private Type type;
    private String customerId;
}
