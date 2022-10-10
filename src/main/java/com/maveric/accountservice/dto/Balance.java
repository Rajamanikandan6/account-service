package com.maveric.accountservice.dto;

import com.maveric.accountservice.constants.Currency;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    private String id;
    private String amount;
    private Currency currency;
    private String accountId;
}
