package com.maveric.accountservice.model;

import com.maveric.accountservice.constants.Type;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
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

}
