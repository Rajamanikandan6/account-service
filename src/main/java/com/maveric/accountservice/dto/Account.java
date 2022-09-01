package com.maveric.accountservice.dto;

import lombok.*;

import com.maveric.accountservice.constants.Type;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)
public class Account {
    @Id
    private String id;
    private Type type;
    private String customerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Balance balance;
}
