package com.maveric.accountservice.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)
@Document(collection = "Accounts")
public class AccountDto {
    @Id
    private String id;
    private Type type;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Balance balance;
}
