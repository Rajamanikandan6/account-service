package com.maveric.accountservice.model;

import com.maveric.accountservice.dto.Balance;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import com.maveric.accountservice.constants.Type;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "AccountDetails")
public class AccountModel {
    @Id
    private String _id;
    private Type type;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Balance balance;
}
