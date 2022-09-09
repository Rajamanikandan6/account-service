package com.maveric.accountservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    private String _id;
    private Number amount;
    private String currency;
    private String accountId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
