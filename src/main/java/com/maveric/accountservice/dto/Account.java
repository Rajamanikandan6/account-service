package com.maveric.accountservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class Account {
    private String type;
    private String customerId;
    private String createdAt;
    private String updatedAt;
}
